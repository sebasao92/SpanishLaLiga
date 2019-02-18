package com.sealor.spanishlaliga.ui.team

import android.support.annotation.StringRes
import com.sealor.spanishlaliga.base.BaseView
import com.sealor.spanishlaliga.model.TeamsResponse

/**
 * Interface that provides methods for displaying teams
 */
interface TeamView : BaseView {

    /**
     * Updates list of teams
     */
    fun updateTeams(teams : TeamsResponse)

    fun showError(error : String)

    /**
     * Shows the loading indicator (ProgressBar) of the view
     */
    fun showLoading()

    /**
     * Hides the loading indicator (ProgressBar) of the view
     */
    fun hideLoading()

    fun showError(@StringRes errorResId: Int) {
        this.showError(getContext().getString(errorResId))
    }
}