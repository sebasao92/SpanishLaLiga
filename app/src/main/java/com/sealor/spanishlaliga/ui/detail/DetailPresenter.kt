package com.sealor.spanishlaliga.ui.detail

import android.widget.Toast
import com.sealor.spanishlaliga.R
import com.sealor.spanishlaliga.base.BasePresenter
import com.sealor.spanishlaliga.network.TeamApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DetailPresenter(detailView : DetailView) : BasePresenter<DetailView>(detailView) {

    @Inject
    lateinit var teamApi : TeamApi

    private var subscription : Disposable? = null

    override fun onViewCreated(id : Int) {
        loadEvents(id)
    }

    private fun loadEvents(idTeam : Int){
        try {
            view.showLoading()
            subscription = teamApi
                .getEvents(idTeam)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnTerminate { view.hideLoading() }
                .subscribe(
                    { eventList -> view.updateEvents(eventList) },
                    { view.showError(R.string.unknown_error) }
                )
        } catch(t : Throwable){
            Toast.makeText(view.getContext(), "An error has occurred getting events", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onViewDestroyed(){
        subscription?.dispose()
    }
}