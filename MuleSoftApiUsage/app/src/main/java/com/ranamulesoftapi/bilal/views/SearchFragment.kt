package com.ranamulesoftapi.bilal.views

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ranamulesoftapi.bilal.R
import com.ranamulesoftapi.bilal.adapters.MyListAdapter

import com.ranamulesoftapi.bilal.databinding.FragmentSearchBinding
import com.ranamulesoftapi.bilal.models.MyDataListItem
import com.ranamulesoftapi.bilal.util.FragmentBinding
import com.ranamulesoftapi.bilal.viewmodels.SearchViewModel

class SearchFragment : Fragment() {
    private var viewModel: SearchViewModel? = null
    private var adapter: MyListAdapter? = null
    var list: ArrayList<MyDataListItem> = ArrayList()
    val binding by FragmentBinding<FragmentSearchBinding>(R.layout.fragment_search)
    var loadingDialog: CustomProgressBar = CustomProgressBar()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = MyListAdapter(context!!, list)
        viewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)
        viewModel!!.init()
        fetchDataFromApi()
        viewModel!!.getApiResponseLiveData().observe(this, Observer { apiResponse ->
            loadingDialog.dialog?.dismiss()
            if (apiResponse != null) {
                list.addAll(apiResponse)
                adapter!!.notifyDataSetChanged()
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fragmentBooksearchSearchResultsRecyclerView.layoutManager = LinearLayoutManager(context) as RecyclerView.LayoutManager?
        binding.fragmentBooksearchSearchResultsRecyclerView.adapter = adapter.also {
            it?.setCallBack {
                if (activity is SearchActivity) {
                    ((activity) as SearchActivity).replaceFragment(
                            DetilsFragment(it),
                            true,
                            false
                    )
                }
            }
        }
        initSearchView()
    }

    private fun initSearchView() {

        val editText = binding.searchView.findViewById<EditText>(androidx.appcompat.R.id.search_src_text)
        editText.setTextColor(Color.WHITE)
        editText.setHintTextColor(Color.WHITE)

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(s: String): Boolean {
                performSearch(s)
                return false
            }

            override fun onQueryTextChange(s: String): Boolean {
                performSearch(s)
                return false
            }
        })
        binding.searchView.setOnCloseListener {
            adapter?.filter?.filter("")
            return@setOnCloseListener false
        }
    }

    fun performSearch(s: String) {
        adapter?.filter?.filter(s)
    }

    fun fetchDataFromApi() {
        loadingDialog.show(context!!, "Please Wait...")
        //add you client id and client secret here in this function to run code
        viewModel!!.callApi("", "")
    }
}