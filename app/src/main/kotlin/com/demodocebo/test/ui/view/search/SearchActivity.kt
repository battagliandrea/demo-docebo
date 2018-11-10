package com.demodocebo.test.ui.view.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.demodocebo.test.ui.view.base.BaseActivity
import com.demodocebo.test.R
import kotlinx.android.synthetic.main.activity_search.*
import android.support.v7.app.AlertDialog
import android.widget.ArrayAdapter

class SearchActivity(override val layoutResourceId: Int = R.layout.activity_search) : BaseActivity(){

    companion object {
        fun getCallingIntent(context: Context) = Intent(context, SearchActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        //setUpViewModelStateObservers()
        //getViewModel<SearchViewModel>(viewModelFactory).fetchRoot()
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //          VIEW MODEL METHOD
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//    private fun setUpViewModelStateObservers() {
//        observe(getViewModel<CatalogViewModel>(viewModelFactory).getState()) { onStateChanged(it) }
//    }

//    private fun onStateChanged(state: CatalogViewModel.State) = when (state) {
//        is CatalogViewModel.State.RootListLoaded -> Toast.makeText(this, "LOADED", Toast.LENGTH_SHORT).show()
//        CatalogViewModel.State.ShowLoading -> Toast.makeText(this, "LOADING", Toast.LENGTH_SHORT).show()
//        CatalogViewModel.State.ShowContent -> Toast.makeText(this, "CONTENT", Toast.LENGTH_SHORT).show()
//        CatalogViewModel.State.ShowError -> Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show()
//    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //          UI METHODS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private fun initView(){
        et_course.setText(resources.getStringArray(R.array.courses)[0])
        et_course.setOnClickListener { openCourseDialog() }

        btn_search.setOnClickListener { routeManager.launchCatalog(this) }
    }

    private fun setCourseValue(value: String?){
        et_course.setText(value ?: "")
    }

    private fun openCourseDialog(){
        val builder = AlertDialog.Builder(this)
        builder.setIcon(R.mipmap.ic_launcher)
        builder.setTitle(getString(R.string.search_select_title))

        builder.setNegativeButton(
                getString(android.R.string.cancel)
        ) { dialog, which -> dialog.dismiss() }

        var array = resources.getStringArray(R.array.courses)
        val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.select_dialog_item)
        arrayAdapter.addAll(array.toList())

        builder.setAdapter(arrayAdapter) { dialog, which ->
            setCourseValue(arrayAdapter.getItem(which))
        }
        builder.show()
    }
}