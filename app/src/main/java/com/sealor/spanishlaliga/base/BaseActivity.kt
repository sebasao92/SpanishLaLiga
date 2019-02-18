package com.sealor.spanishlaliga.base

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity


/**
 * Activity that all Activity classes must extend.
 * Provides methods for presenter instantiation.
 */
abstract class BaseActivity<P : BasePresenter<BaseView>> : BaseView, AppCompatActivity() {

    protected lateinit var presenter: P

    protected abstract fun instantiatePresenter() : P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = instantiatePresenter()
    }

    override fun getContext(): Context {
        return this
    }
}