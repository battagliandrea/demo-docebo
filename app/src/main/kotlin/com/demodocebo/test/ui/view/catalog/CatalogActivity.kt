package com.demodocebo.test.ui.view.catalog

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.RecyclerView
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
import android.nfc.tech.MifareUltralight.PAGE_SIZE
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast




class CatalogActivity(override val layoutResourceId: Int = R.layout.activity_catalog) : BaseActivity(){

    @Inject
    lateinit var mCatalogAdapter: CatalogAdapter
    lateinit var mLinearLayoutManager: LinearLayoutManager

    lateinit var mCatalogViewModel: CatalogViewModel

    private  var isLoading: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupNavigationToolbar(toolbar, getString(R.string.catalog_title))

        setupRecyclerView()

        mCatalogViewModel = getViewModel<CatalogViewModel>(viewModelFactory)
        setUpViewModelStateObservers()
        mCatalogViewModel.fetchCatalog(loadMore = false)
    }

    private fun setUpViewModelStateObservers() {
        observe(mCatalogViewModel.getState()) { onStateChanged(it) }
    }

    private fun onStateChanged(state: CatalogViewModel.State) = when (state) {
        is CatalogViewModel.State.ListLoaded -> populateList(state.items)
        is CatalogViewModel.State.CountLoaded -> populateCount(state.count)
        CatalogViewModel.State.ShowLoading -> view_flipper.displayedChild = LOADER
        CatalogViewModel.State.ShowLoadingMore -> isLoading = true
        CatalogViewModel.State.ShowEmptyState -> {
            isLoading = false
            view_flipper.displayedChild = EMPTY_STATE
        }
        CatalogViewModel.State.ShowError -> {
            isLoading = false
            view_flipper.displayedChild = ERROR
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //          UI METHOD
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private fun setupRecyclerView(){
        mLinearLayoutManager = LinearLayoutManager(this);
        rv.layoutManager = mLinearLayoutManager;

        rv.adapter = mCatalogAdapter
        rv.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val visibleItemCount = mLinearLayoutManager.childCount
                val totalItemCount = mLinearLayoutManager.itemCount
                val firstVisibleItemPosition = mLinearLayoutManager.findFirstVisibleItemPosition()

                if(!isLoading){
                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
                            && firstVisibleItemPosition >= 0
                            && totalItemCount >= PAGE_SIZE) {
                        mCatalogViewModel.fetchCatalog(loadMore = true)
                    }
                }
            }
        });
    }

    private fun populateList(items: List<Item>){
        isLoading = false
        view_flipper.displayedChild = LIST
        mCatalogAdapter.data = items as MutableList<Item>
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.catalog_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        when (id) {
            R.id.filters -> {
                Toast.makeText(applicationContext, "FILTERS", Toast.LENGTH_LONG).show()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}