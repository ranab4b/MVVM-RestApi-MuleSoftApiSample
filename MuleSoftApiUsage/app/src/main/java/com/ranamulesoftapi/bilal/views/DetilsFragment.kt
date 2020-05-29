package com.ranamulesoftapi.bilal.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ranamulesoftapi.bilal.R
import com.ranamulesoftapi.bilal.databinding.FragmentDetilsBinding
import com.ranamulesoftapi.bilal.models.MyDataListItem
import com.ranamulesoftapi.bilal.util.FragmentBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetilsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetilsFragment(val data: MyDataListItem) : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    val binding by FragmentBinding<FragmentDetilsBinding>(R.layout.fragment_detils)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.close.setOnClickListener { activity!!.onBackPressed() }

        binding.ProductNumber.text="ProductNumber: ${data.ProductNumber}"
        binding.name.text="Name: ${data.Name}"
        binding.code.text="Code: ${data.Code}"
        binding.cat.text="Category: ${data.Category}"
        binding.country.text="CountryCode: ${data.CountryCode}"
        binding.price.text="AdvicedPrice: ${data.AdvicedPrice}"
    }

}
