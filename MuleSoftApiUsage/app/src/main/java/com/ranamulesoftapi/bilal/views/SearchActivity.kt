package com.ranamulesoftapi.bilal.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ranamulesoftapi.bilal.R
import replaceFragmentAndClearBackstack

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        replaceFragment(SearchFragment(),false,false)
    }

    fun replaceFragment(fragment: Fragment,addToStack:Boolean,clearStack:Boolean){
        replaceFragmentAndClearBackstack(fragment,R.id.fragmentContainer,addToStack,clearStack)
    }
}
