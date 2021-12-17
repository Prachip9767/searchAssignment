package com.app.mock_assignment_search.remote.response


import com.google.gson.annotations.SerializedName

data class AddressDTO(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("requestId")
    val requestId: String
)