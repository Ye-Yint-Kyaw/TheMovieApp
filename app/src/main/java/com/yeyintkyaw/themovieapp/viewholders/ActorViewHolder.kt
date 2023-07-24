package com.yeyintkyaw.themovieapp.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yeyintkyaw.themovieapp.R
import com.yeyintkyaw.themovieapp.data.vos.ActorVO
import com.yeyintkyaw.themovieapp.databinding.ViewHolderBestActorBinding
import com.yeyintkyaw.themovieapp.delegates.ActorViewHolderDelegate
import com.yeyintkyaw.themovieapp.utils.IMAGE_BASE_URL

class ActorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private lateinit var binding: ViewHolderBestActorBinding


    fun bindData(actor: ActorVO){
        val ivActorImage : ImageView = itemView.findViewById(R.id.ivBestActor)
        val tvActorName : TextView = itemView.findViewById(R.id.tvActorName)
        Glide.with(itemView.context)
            .load("${IMAGE_BASE_URL}${actor.profilePath}")
            .into(ivActorImage)
        tvActorName.text = actor.name
    }
}