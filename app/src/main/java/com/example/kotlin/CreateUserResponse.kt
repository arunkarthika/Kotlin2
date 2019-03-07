package com.example.kotlin

import com.google.gson.annotations.SerializedName

/**
 * Created by anupamchugh on 09/01/17.
 */

class CreateUserResponse {

    @SerializedName("name")
    var name: String? = null
    @SerializedName("job")
    var job: String? = null
    @SerializedName("id")
    var id: String? = null
    @SerializedName("createdAt")
    var createdAt: String? = null
}
