package com.ranamulesoftapi.bilal.adapters;


import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Filter
import android.widget.Filterable
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.ranamulesoftapi.bilal.R
import com.ranamulesoftapi.bilal.models.MyDataListItem


class MyListAdapter(context: Context, val list: ArrayList<MyDataListItem>) :
        BaseQuickAdapter<MyDataListItem, BaseViewHolder>(R.layout.book_item,list),
        Filterable {
    private lateinit var callBack: (MyDataListItem) -> Unit
    fun setCallBack(callback: (MyDataListItem) -> Unit) {
        this.callBack = callback

    }
    var searchableList: ArrayList<MyDataListItem> = list


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): Filter.FilterResults {

                val queryString = charSequence.toString().toLowerCase()


                if (queryString.isEmpty())
                {
                    searchableList=list
                }
                else{
                    var filteredList: ArrayList<MyDataListItem> =ArrayList()
                    for (n in list)
                    {
                        if (n.Name!!.contains(queryString,true)||n.Code!!.contains(queryString,true))
                        {
                            filteredList.add(n)
                        }
                    }
                    searchableList=filteredList
                }

                val filterResults = Filter.FilterResults()
                filterResults.values=searchableList
                return filterResults
//                filterResults.values = if (queryString == null || queryString.isEmpty())
//                    list
//                else {
//                    list.filter {
//
//                        if (it.Code == null || it.Name == null) {
//                            false
//                        } else {
//                            it.Code.toString().contains(queryString, true) || it.Name!!.contains(
//                                    queryString, true
//                            )
//                        }
//                    } as ArrayList<MyDataListItem>
//                }
//                return filterResults


            }

            override fun publishResults(charSequence: CharSequence, filterResults: Filter.FilterResults) {
               // searchableList.clear()
                searchableList = filterResults.values as ArrayList<MyDataListItem>
                notifyDataSetChanged()
            }
        }
    }

    override fun getItemCount(): Int {
        Log.d("testsss0", "" + searchableList.size)
        return searchableList.size
    }


    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.setText(R.id.name,"Name: ${searchableList.get(position).Name}")
        holder.setText(R.id.code,"Code:${searchableList.get(position).Code}")
        holder.setText(R.id.price,"Price: ${searchableList.get(position).AdvicedPrice}")

        holder.setOnClickListener(R.id.rootViewItem, View.OnClickListener {
            callBack(searchableList.get(position))
        })
    }
    override fun convert(helper: BaseViewHolder, item: MyDataListItem?) {

    }


}
