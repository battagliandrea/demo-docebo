package com.demodocebo.test.ui.utils

import android.app.Activity
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.view.View

import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.demodocebo.test.R


fun Activity.getContentView(): View = this.findViewById(android.R.id.content)

inline fun <reified T : ViewModel> FragmentActivity.getViewModel(viewModelFactory: ViewModelProvider.Factory): T {
    return ViewModelProviders.of(this, viewModelFactory)[T::class.java]
}

fun AppCompatActivity.setupDefaultToolbar(toolbar: Toolbar, title: String) {
    this.setSupportActionBar(toolbar)
    this.supportActionBar?.title = title
    this.supportActionBar?.setDisplayHomeAsUpEnabled(false)
    this.supportActionBar?.setDisplayShowTitleEnabled(true)
}

fun AppCompatActivity.setupNavigationToolbar(toolbar: Toolbar, title: String, icon: Int = R.drawable.ic_arrow_back_white) {
    this.setSupportActionBar(toolbar)
    this.supportActionBar?.title = title
    this.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    this.supportActionBar?.setDisplayShowHomeEnabled(true)
    this.supportActionBar?.setDisplayShowTitleEnabled(true)
    toolbar.setNavigationIcon(icon)
    toolbar.setNavigationOnClickListener { v -> onBackPressed() }
}