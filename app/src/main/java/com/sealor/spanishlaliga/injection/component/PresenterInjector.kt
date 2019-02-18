package com.sealor.spanishlaliga.injection.component

import dagger.BindsInstance
import dagger.Component
import com.sealor.spanishlaliga.base.BaseView
import com.sealor.spanishlaliga.injection.module.ContextModule
import com.sealor.spanishlaliga.injection.module.NetworkModule
import com.sealor.spanishlaliga.ui.detail.DetailPresenter
import com.sealor.spanishlaliga.ui.team.TeamPresenter
import javax.inject.Singleton


/**
 * Component that provides inject() method for presenters
 */
@Singleton
@Component(modules = [(ContextModule::class), (NetworkModule::class)])
@Suppress("unused")
interface PresenterInjector {

    /**
     * Injects required dependencies into TeamPresenter
     */
    fun inject(teamPresenter: TeamPresenter)

    /**
     * Injects required dependencies into DetailPresenter
     */
    fun inject(detailPresenter: DetailPresenter)

    @Component.Builder
    interface Builder{
        fun build() : PresenterInjector
        fun networkModule(networkModule : NetworkModule) : Builder
        fun contextModule(contextModule : ContextModule) : Builder

        @BindsInstance
        fun baseView(baseView : BaseView) : Builder
    }
}