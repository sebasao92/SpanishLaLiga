package com.sealor.spanishlaliga.ui.team

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.sealor.spanishlaliga.R
import com.sealor.spanishlaliga.databinding.ItemTeamBinding
import com.sealor.spanishlaliga.model.Team
import com.sealor.spanishlaliga.ui.detail.DetailActivity
import com.squareup.picasso.Picasso

class TeamAdapter(private val context: Context) : RecyclerView.Adapter<TeamAdapter.TeamViewHolder>() {

    private var teams: List<Team> = listOf()

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int): TeamViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding: ItemTeamBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_team, parent, false)
        return TeamViewHolder(binding)
    }

    fun updateTeams(teams : List<Team>){
        this.teams = teams
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return teams.size
    }

    override fun onBindViewHolder(holder : TeamViewHolder, position : Int) {
        holder.bind(teams[position])

        holder.parentLayout.setOnClickListener {
            val intent = Intent(context , DetailActivity::class.java)
            intent.putExtra("Team", teams[position])
            context.startActivity(intent)
        }

    }

    inner class TeamViewHolder(private val binding : ItemTeamBinding) : RecyclerView.ViewHolder(binding.root) {

        lateinit var parentLayout : ConstraintLayout

        fun bind(team : Team) {
            binding.team = team
            Picasso.get().load(team.strTeamBadge).into(itemView.findViewById(R.id.team_badge) as ImageView)
            parentLayout = itemView.findViewById(R.id.parent_layout)
            binding.executePendingBindings()
        }
    }
}