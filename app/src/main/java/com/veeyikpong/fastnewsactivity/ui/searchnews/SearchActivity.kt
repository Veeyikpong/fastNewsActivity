package com.veeyikpong.fastnewsactivity.ui.searchnews

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.veeyikpong.fastnewsactivity.api.response.News
import com.veeyikpong.fastnewsactivity.AppConstants
import com.veeyikpong.fastnewsactivity.R
import com.veeyikpong.fastnewsactivity.ui.searchnews.details.NewsDetailsActivity
import com.veeyikpong.fastnewsactivity.ui.searchnews.search.SearchContract
import com.veeyikpong.fastnewsactivity.ui.searchnews.search.SearchPresenter
import com.veeyikpong.fastnewsactivity.ui.searchnews.search.adapter.NewsAdapter

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity(), SearchContract.View {

    private lateinit var presenter: SearchContract.Presenter
    private lateinit var newsAdapter: NewsAdapter

    override fun setPresenter(presenter: SearchContract.Presenter) {
        this.presenter = presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setTitle(getString(R.string.search_news))

        setPresenter(SearchPresenter(this))
        newsAdapter = NewsAdapter(this,ArrayList())
        newsAdapter.setOnItemClickListener(object: NewsAdapter.OnItemClickListener{
            override fun onItemClick(news: News) {
                val bundle = Bundle()
                bundle.putSerializable(AppConstants.BUNDLE_KEY_NEWS,news)
                val intent = Intent(this@SearchActivity,NewsDetailsActivity::class.java)
                intent.putExtras(bundle)
                startActivity(intent)
            }
        })

        btn_search.setOnClickListener {
            search()
        }

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                search()

                return false
            }
        })

        with(rv_news){
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(this@SearchActivity)
            addItemDecoration(DividerItemDecoration(this@SearchActivity,DividerItemDecoration.VERTICAL))
        }
    }

    fun search(){
        if(validate()){
            searchView.clearFocus()
            presenter.searchNews(searchView.query.toString())
        }
    }


    fun validate(): Boolean{
        var validated = true

        if(searchView.query.isBlank()){
            validated = false
            val id = searchView.context.resources.getIdentifier("android:id/search_src_text", null, null)
            val editText = searchView.findViewById(id) as EditText
            editText.error = getString(R.string.err_search_text_empty)
        }

        return validated
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun displayTotalResult(totalResult: String) {
        tv_totalResult.text = getString(R.string.results_found,totalResult)
    }

    override fun showNews(newsList: List<News>) {
        if(::newsAdapter.isInitialized) {
            newsAdapter.updateList(newsList)
        }
        ll_result.visibility = View.VISIBLE
    }

    override fun showError(errorMessage: String) {
        if(errorMessage.isBlank()) {
            Toast.makeText(this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}
