package com.veeyikpong.fastnewsactivity.ui.main


import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.veeyikpong.easyfragmentcontainer.FragmentContainer
import com.veeyikpong.fastnewsactivity.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragmentContainer.replaceFragment(MainFragment())
        fragmentContainer.addFragmentListener(object: FragmentContainer.FragmentListener{
            override fun onFragmentShow(f: Fragment) {
                updateTitle(f)
            }

            override fun onFragmentDestroyed(f: Fragment) {

            }
        })
    }

    private fun updateTitle(f: Fragment){
        when(f){
            is MainFragment ->{
                supportActionBar!!.title = getString(R.string.app_name)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            android.R.id.home->{onBackPressed()}
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if(fragmentContainer.back())
            return

        finish()
    }
}
