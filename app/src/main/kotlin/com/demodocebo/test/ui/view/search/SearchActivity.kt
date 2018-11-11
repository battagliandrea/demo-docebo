package com.demodocebo.test.ui.view.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.demodocebo.test.ui.view.base.BaseActivity
import com.demodocebo.test.R
import kotlinx.android.synthetic.main.activity_search.*
import android.support.v7.app.AlertDialog
import android.widget.ArrayAdapter
import android.widget.Toast
import com.demodocebo.test.ui.utils.getViewModel
import com.demodocebo.test.ui.utils.observe
import com.demodocebo.test.ui.viewmodel.SearchViewModel

class SearchActivity(override val layoutResourceId: Int = R.layout.activity_search) : BaseActivity(){

    companion object {
        fun getCallingIntent(context: Context) = Intent(context, SearchActivity::class.java)
    }

    lateinit var mSearchViewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        setUpViewModelStateObservers()
        mSearchViewModel = getViewModel<SearchViewModel>(viewModelFactory)
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //          VIEW MODEL METHOD
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private fun setUpViewModelStateObservers() {
        observe(getViewModel<SearchViewModel>(viewModelFactory).getState()) { onStateChanged(it) }
    }

    private fun onStateChanged(state: SearchViewModel.State) = when (state) {
        SearchViewModel.State.Success -> {
            routeManager.launchCatalog(this)
        }
        SearchViewModel.State.Error -> {
            Toast.makeText(this, getString(R.string.error_undefined), Toast.LENGTH_SHORT).show()
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //          UI METHODS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private fun initView(){
        et_course.setText(resources.getStringArray(R.array.courses)[0])
        et_course.setOnClickListener { openCourseDialog() }

        btn_search.setOnClickListener {  mSearchViewModel.saveParams(et_name.text.toString(), et_course.text.toString()) }
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