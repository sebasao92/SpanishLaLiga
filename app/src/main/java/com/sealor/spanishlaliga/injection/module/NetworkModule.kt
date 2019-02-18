package com.sealor.spanishlaliga.injection.module

import com.sealor.spanishlaliga.network.TeamApi
import com.sealor.spanishlaliga.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Module that provides all required dependencies about network (API Caller)
 */

@Module
@Suppress("unused")
object NetworkModule {

    /**
     * Provides the API Caller
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideTeamApi(retrofit: Retrofit) : TeamApi {
        return retrofit.create(TeamApi::class.java)
    }

    /**
     * Provides the Retrofit object
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }
}