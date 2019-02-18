package com.sealor.spanishlaliga.injection.module

import android.app.Application
import android.content.Context
import com.sealor.spanishlaliga.base.BaseView
import dagger.Module
import dagger.Provides

/**
 * Module that provides all required dependecies about Context
 */

@Module
@Suppress("unused")
object ContextModule {

    @Provides
    @JvmStatic
    internal fun provideContext(baseView : BaseView) : Context {
        return baseView.getContext()
    }

    @Provides
    @JvmStatic
    internal fun provideApplication(context: Context) : Application {
        return context.applicationContext as Application
    }
}