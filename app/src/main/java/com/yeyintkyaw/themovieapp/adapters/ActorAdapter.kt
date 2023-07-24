package com.yeyintkyaw.themovieapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yeyintkyaw.themovieapp.R
import com.yeyintkyaw.themovieapp.data.vos.ActorVO
import com.yeyintkyaw.themovieapp.delegates.ActorViewHolderDelegate
import com.yeyintkyaw.themovieapp.viewholders.ActorViewHolder

class ActorAdapter(): RecyclerView.Adapter<ActorViewHolder>() {

    private var mActors: List<ActorVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_best_actor, parent,false)
        return ActorViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mActors.count()
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        if (mActors.isNotEmpty()){
            holder.bindData(mActors[position])
        }
    }

    @SuppressLint("NotifyDataSetChange")
    fun setNewData(actors: List<ActorVO>){
        mActors = actors
        notifyDataSetChanged()
    }
}