package com.example.kotlin

import com.google.gson.annotations.SerializedName

/**
 * Created by anupamchugh on 09/01/17.
 */

class User(
    @field:SerializedName("name")
    var name: String, @field:SerializedName("job")
    var job: String
) {
    @SerializedName("id")
    var id: String? = null
    @SerializedName("createdAt")
    var createdAt: String? = null


}
