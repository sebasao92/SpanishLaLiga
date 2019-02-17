package com.sealor.spanishlaliga.ui.team

import android.support.annotation.StringRes
import com.sealor.spanishlaliga.base.BaseView
import com.sealor.spanishlaliga.model.EventsResponse
import com.sealor.spanishlaliga.model.TeamsResponse

interface TeamView : BaseView {

    fun updateTeams(teams : TeamsResponse)

    fun showError(error : String)

    fun showLoading()

    fun hideLoading()

    fun showError(@StringRes errorResId: Int) {
        this.showError(getContext().getString(errorResId))
    }
}