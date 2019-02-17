package com.sealor.spanishlaliga.ui.team

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.sealor.spanishlaliga.R
import com.sealor.spanishlaliga.base.BaseActivity
import com.sealor.spanishlaliga.databinding.ActivityTeamBinding
import com.sealor.spanishlaliga.model.TeamsResponse

class TeamActivity : BaseActivity<TeamPresenter>(), TeamView {

    private lateinit var binding: ActivityTeamBinding
    private var leagueId : Int = 0
    private val teamsAdapter = TeamAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_team)
        binding.adapter = teamsAdapter
        binding.layoutManager = LinearLayoutManager(this)
        binding.dividerItemDecoration = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        getIncomingIntent()
        presenter.onViewCreated(leagueId)
    }

    private fun getIncomingIntent(){
        leagueId = intent.getIntExtra("leagueId", 4335)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onViewDestroyed()
    }

    override fun updateTeams(teams: TeamsResponse) {
        teamsAdapter.updateTeams(teams.teams)
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        binding.progressVisibility = View.GONE
    }

    override fun hideLoading() {
        binding.progressVisibility = View.GONE
    }

    override fun instantiatePresenter(): TeamPresenter {
        return TeamPresenter(this)
    }


}