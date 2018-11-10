package com.demodocebo.test.ui.view.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.demodocebo.test.ui.view.base.BaseActivity
import com.demodocebo.test.R

class SearchActivity(override val layoutResourceId: Int = R.layout.activity_search) : BaseActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //setUpViewModelStateObservers()
        //getViewModel<SearchViewModel>(viewModelFactory).fetchRoot()
    }

//    private fun setUpViewModelStateObservers() {
//        observe(getViewModel<CatalogViewModel>(viewModelFactory).getState()) { onStateChanged(it) }
//    }

//    private fun onStateChanged(state: CatalogViewModel.State) = when (state) {
//        is CatalogViewModel.State.RootListLoaded -> Toast.makeText(this, "LOADED", Toast.LENGTH_SHORT).show()
//        CatalogViewModel.State.ShowLoading -> Toast.makeText(this, "LOADING", Toast.LENGTH_SHORT).show()
//        CatalogViewModel.State.ShowContent -> Toast.makeText(this, "CONTENT", Toast.LENGTH_SHORT).show()
//        CatalogViewModel.State.ShowError -> Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show()
//    }

    companion object {
        fun getCallingIntent(context: Context) = Intent(context, SearchActivity::class.java)
    }

}