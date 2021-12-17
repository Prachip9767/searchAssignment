package com.app.mock_assignment_search.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.mock_assignment_search.R
import com.app.mock_assignment_search.adapter.SearchAdapter
import com.app.mock_assignment_search.databinding.FragmentHomeBinding
import com.app.mock_assignment_search.remote.response.Address
import com.app.mock_assignment_search.viewModel.SearchModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    lateinit var searchAdapter :SearchAdapter
    lateinit var homeFragmentHomeBinding: FragmentHomeBinding
    private lateinit var searchModel: SearchModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeFragmentHomeBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return homeFragmentHomeBinding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       searchModel = SearchModel()
        setRecyclerView()
        searchAddress()
    }
    private fun setRecyclerView() {
        homeFragmentHomeBinding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            searchAdapter = SearchAdapter()
            adapter = searchAdapter
        }
    }

    private fun searchAddress() {
        homeFragmentHomeBinding.etCityName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                loadData(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    @SuppressLint("NotifyDataSetChanged")
    fun loadData(input: String) {
        searchModel.getSearch().observe(viewLifecycleOwner, {
            if (it != null) {
                searchAdapter.searchList = it.data.addressList as ArrayList<Address>
                searchAdapter.notifyDataSetChanged()
            }
        })
        searchModel.ApiDATA(input)
    }
}