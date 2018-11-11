package com.demodocebo.test.ui.view.catalog

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.demodocebo.test.ui.utils.observe
import com.demodocebo.test.ui.view.base.BaseActivity
import com.demodocebo.test.R
import com.demodocebo.test.ui.utils.getViewModel
import com.demodocebo.test.ui.viewmodel.CatalogViewModel
import kotlinx.android.synthetic.main.activity_catalog.*
import javax.inject.Inject

class CatalogActivity(override val layoutResourceId: Int = R.layout.activity_catalog) : BaseActivity(){

    @Inject
    lateinit var mCatalogAdapter: CatalogAdapter

    lateinit var mCatalogViewModel: CatalogViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        rv.adapter = mCatalogAdapter

        mCatalogViewModel = getViewModel<CatalogViewModel>(viewModelFactory)
        setUpViewModelStateObservers()
        mCatalogViewModel.fetchRoot()
    }

    private fun setUpViewModelStateObservers() {
        observe(mCatalogViewModel.getState()) { onStateChanged(it) }
    }

    private fun onStateChanged(state: CatalogViewModel.State) = when (state) {
        is CatalogViewModel.State.ListLoaded -> mCatalogAdapter.data = state.items
        CatalogViewModel.State.ShowContent -> Toast.makeText(this, "CONTENT", Toast.LENGTH_SHORT).show()
        CatalogViewModel.State.ShowLoading -> Toast.makeText(this, "LOADING", Toast.LENGTH_SHORT).show()
        CatalogViewModel.State.ShowError -> Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun getCallingIntent(context: Context) = Intent(context, CatalogActivity::class.java)
    }

}