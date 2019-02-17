package com.sealor.spanishlaliga.ui.detail

import android.support.annotation.StringRes
import com.sealor.spanishlaliga.base.BaseView
import com.sealor.spanishlaliga.model.EventsResponse

interface DetailView : BaseView {

    fun hideLoading()

    fun showLoading()

    fun updateEvents(events: EventsResponse)

    fun showError(@StringRes errorResId : Int) {
        this.showError(getContext().getString(errorResId))
    }

    fun showError(error : String)
}