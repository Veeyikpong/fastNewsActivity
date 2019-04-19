package com.veeyikpong.fastnewsactivity.ui.searchnews.search

import com.veeyikpong.fastnewsactivity.api.response.News
import com.veeyikpong.fastnewsactivity.ui.base.BaseView

interface SearchContract{
    interface Presenter{
        fun init()
        fun searchNews(query: String)
        fun onDestroy()
    }

    interface View: BaseView<Presenter> {
        fun showLoading()
        fun hideLoading()
        fun displayTotalResult(totalResult: String)
        fun showNews(newsList: List<News>)
        fun showError(errorMessage: String="")
    }
}