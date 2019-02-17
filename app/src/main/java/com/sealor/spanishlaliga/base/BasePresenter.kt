package com.sealor.spanishlaliga.base

import com.sealor.spanishlaliga.injection.component.DaggerPresenterInjector
import com.sealor.spanishlaliga.injection.component.PresenterInjector
import com.sealor.spanishlaliga.injection.module.ContextModule
import com.sealor.spanishlaliga.injection.module.NetworkModule
import com.sealor.spanishlaliga.ui.detail.DetailPresenter
import com.sealor.spanishlaliga.ui.team.TeamPresenter


abstract class BasePresenter<out V : BaseView>(protected val view : V) {

    private val injector : PresenterInjector = DaggerPresenterInjector
        .builder()
        .baseView(view)
        .contextModule(ContextModule)
        .networkModule(NetworkModule)
        .build()

    init{
        inject()
    }

    open fun onViewCreated(){}

    open fun onViewDestroyed(){}

    private fun inject(){
        when(this){
            is TeamPresenter -> injector.inject(this)
        }
        when(this){
            is DetailPresenter -> injector.inject(this)
        }
    }

}