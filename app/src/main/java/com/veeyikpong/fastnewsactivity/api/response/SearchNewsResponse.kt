package com.veeyikpong.fastnewsactivity.api.response

import com.google.gson.annotations.SerializedName

class SearchNewsResponse{
    @SerializedName("status")
    lateinit var status: String

    @SerializedName("totalResults")
    var totalResults: Int = 0

    @SerializedName("articles")
    lateinit var newsList: List<News>
}

