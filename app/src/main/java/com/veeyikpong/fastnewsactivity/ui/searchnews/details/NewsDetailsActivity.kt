package com.veeyikpong.fastnewsactivity.ui.searchnews.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.bumptech.glide.Glide
import com.veeyikpong.fastnewsactivity.R
import kotlinx.android.synthetic.main.activity_news_details.*

class NewsDetailsActivity : AppCompatActivity(), NewsDetailsContract.View {
    private lateinit var presenter: NewsDetailsContract.Presenter

    override fun setPresenter(presenter: NewsDetailsContract.Presenter) {
        this.presenter = presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setTitle(getString(R.string.news_details))

        setPresenter(NewsDetailsPresenter(this))

        presenter.init(intent.extras)
    }



    override fun showError() {
        Toast.makeText(this,getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show()
    }

    override fun close() {
        onBackPressed()
    }

    override fun setImage(imageURL: String) {
        Glide
            .with(this)
            .load(imageURL)
            .into(img_news)
    }

    override fun setTitle(title: String) {
        tv_title.text = title
    }

    override fun setAuthor(author: String) {
        tv_author.text = getString(R.string.author_by, author)
    }

    override fun setDescription(description: String) {
        tv_description.text = description
    }

    override fun setReadMoreURL(url: String) {
        tv_readMore.text = getString(R.string.read_more_at,url)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
