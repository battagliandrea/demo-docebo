package com.demodocebo.test.ui.view.catalog

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.demodocebo.test.ui.utils.observe
import com.demodocebo.test.ui.view.base.BaseActivity
import com.demodocebo.test.R
import com.demodocebo.test.data.api.models.Item
import com.demodocebo.test.ui.utils.getViewModel
import com.demodocebo.test.ui.utils.setupNavigationToolbar
import com.demodocebo.test.ui.viewmodel.CatalogViewModel
import kotlinx.android.synthetic.main.activity_catalog.*
import kotlinx.android.synthetic.main.view_toolbar.*
import javax.inject.Inject

class CatalogActivity(override val layoutResourceId: Int = R.layout.activity_catalog) : BaseActivity(){

    @Inject
    lateinit var mCatalogAdapter: CatalogAdapter

    lateinit var mCatalogViewModel: CatalogViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupNavigationToolbar(toolbar, getString(R.string.catalog_title))

        rv.adapter = mCatalogAdapter

        mCatalogViewModel = getViewModel<CatalogViewModel>(viewModelFactory)
        setUpViewModelStateObservers()
        mCatalogViewModel.fetchRoot()
    }

    private fun setUpViewModelStateObservers() {
        observe(mCatalogViewModel.getState()) { onStateChanged(it) }
    }

    private fun onStateChanged(state: CatalogViewModel.State) = when (state) {
        is CatalogViewModel.State.ListLoaded -> populateList(state.items)
        is CatalogViewModel.State.CountLoaded -> populateCount(state.count)
        CatalogViewModel.State.ShowLoading -> view_flipper.displayedChild = LOADER
        CatalogViewModel.State.ShowEmptyStare -> view_flipper.displayedChild = EMPTY_STATE
        CatalogViewModel.State.ShowError -> view_flipper.displayedChild = ERROR
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //          UI METHOD
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private fun populateList(items: List<Item>){
        view_flipper.displayedChild = LIST
        mCatalogAdapter.data = items
    }

    private fun populateCount(count: Int){
        tv_count.text = "${count} ${resources.getQuantityString(R.plurals.catalog_count, count)}"
    }

    companion object {
        private const val LIST : Int = 0
        private const val LOADER : Int = 1
        private const val EMPTY_STATE : Int = 2
        private const val ERROR : Int = 3

        fun getCallingIntent(context: Context) = Intent(context, CatalogActivity::class.java)
    }

}