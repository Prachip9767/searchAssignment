package com.app.mock_assignment_search.remote

import com.app.mock_assignment_search.remote.response.AddressDTO
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {
    @GET("compassLocation/rest/address/autocomplete")
    fun getResponse(
        @Query("queryString") queryString: String,
        @Query("city") city: String): Observable<AddressDTO>
}
//Base_Url :- https://digi-api.airtel.in/compassLocation/rest/address/autocomplete?queryString=airtel&city=gurgaon
