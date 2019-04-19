package com.veeyikpong.fastnewsactivity.ui.searchnews.details

import android.os.Bundle
import com.veeyikpong.fastnewsactivity.api.response.News
import com.veeyikpong.fastnewsactivity.AppConstants
import com.veeyikpong.fastnewsactivity.ui.base.BasePresenter

class NewsDetailsPresenter(val view:NewsDetailsContract.View): BasePresenter(),NewsDetailsContract.Presenter{
    override fun init(bundle: Bundle) {
        val news = bundle.getSerializable(AppConstants.BUNDLE_KEY_NEWS) as News

        if(news == null){
            view.showError()
            view.close()
            return
        }

        view.setImage(news.imageURL)
        view.setTitle(news.title)
        view.setAuthor(news.author)
        view.setDescription(news.body)
        view.setReadMoreURL(news.url)
    }
}