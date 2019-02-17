package com.sealor.spanishlaliga.ui.team

import android.widget.Toast
import com.sealor.spanishlaliga.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import com.sealor.spanishlaliga.base.BasePresenter
import com.sealor.spanishlaliga.network.TeamApi
import javax.inject.Inject

class TeamPresenter(teamView : TeamView) : BasePresenter<TeamView>(teamView) {

    @Inject
    lateinit var teamApi : TeamApi

    private var subscription : Disposable? = null

    override fun onViewCreated(id : Int){
        loadTeams(id)
    }

    private fun loadTeams(id : Int){
        try {
            view.showLoading()
            subscription = teamApi
                .getTeams(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnTerminate {view.hideLoading()}
                .subscribe(
                    {teamList -> view.updateTeams(teamList)},
                    {view.showError(R.string.unknown_error)}
                )
        } catch(t : Throwable){
            Toast.makeText(view.getContext(), "An error has occurred getting teams", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onViewDestroyed(){
        subscription?.dispose()
    }
}