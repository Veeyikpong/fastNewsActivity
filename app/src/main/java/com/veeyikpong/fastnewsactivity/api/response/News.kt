package com.veeyikpong.fastnewsactivity.api.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class News: Serializable{
    @SerializedName("title")
    lateinit var title: String

    @SerializedName("description")
    lateinit var body: String

    @SerializedName("author")
    lateinit var author: String

    @SerializedName("url")
    lateinit var url: String

    @SerializedName("urlToImage")
    lateinit var imageURL: String

    @SerializedName("publishedAt")
    lateinit var publishedDate: String
}