package com.app.mock_assignment_search.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.mock_assignment_search.di.Module
import com.app.mock_assignment_search.remote.ApiClient
import com.app.mock_assignment_search.remote.response.AddressDTO
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class SearchModel : ViewModel() {
    var addressDTO: MutableLiveData<AddressDTO> = MutableLiveData()
    fun getSearch(): MutableLiveData<AddressDTO> { return addressDTO }
    fun ApiDATA(query: String) {
        val apiResponse = Module.apiNetwork().create(ApiClient::class.java)
        apiResponse.getResponse("airtel", query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getCityListObserverRxJava())
    }
    private fun getCityListObserverRxJava(): Observer<AddressDTO> {
        return object : Observer<AddressDTO> {
            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: AddressDTO) {
                addressDTO.postValue(t)
            }
            override fun onError(e: Throwable) {
                addressDTO.postValue(null)
            }
            override fun onComplete() {
            }
        }
    }
}