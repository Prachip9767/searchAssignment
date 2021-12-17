package com.app.mock_assignment_search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.mock_assignment_search.R
import com.app.mock_assignment_search.databinding.ItemLayoutBinding
import com.app.mock_assignment_search.remote.response.Address

class SearchAdapter : RecyclerView.Adapter<SearchViewHolder>() {
    var searchList = ArrayList<Address>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val itemLayoutBinding: ItemLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_layout, parent, false
        )
        return SearchViewHolder(itemLayoutBinding)
    }
    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(searchList [position])
    }
    override fun getItemCount(): Int {
        return searchList .size
    }
}
class SearchViewHolder(private val itemLayoutBinding: ItemLayoutBinding) :
    RecyclerView.ViewHolder(itemLayoutBinding.root) {

    fun bind(address: Address) {
        itemLayoutBinding.address = address
    }
}