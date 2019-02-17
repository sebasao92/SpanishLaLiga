package com.sealor.spanishlaliga.ui.team

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.sealor.spanishlaliga.R
import com.sealor.spanishlaliga.databinding.ItemTeamBinding
import com.sealor.spanishlaliga.model.Event
import com.sealor.spanishlaliga.model.Team
import com.sealor.spanishlaliga.network.TeamApi
import com.squareup.picasso.Picasso
import javax.inject.Inject

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
            //Toast.makeText(context,"HOLIIIII".plus(events[0].dateEvent),Toast.LENGTH_LONG).show()
            val intent = Intent(context , DetailActivity::class.java)
            intent.putExtra("Team", teams[position])
            //getEventsById(teams[position].idTeam)
            //intent.putExtra("Events", getEventsById(teams[position].idTeam) as Serializable)
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