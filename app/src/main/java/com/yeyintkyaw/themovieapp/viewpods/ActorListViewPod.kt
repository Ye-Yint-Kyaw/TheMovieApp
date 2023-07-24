package com.yeyintkyaw.themovieapp.viewpods

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.yeyintkyaw.themovieapp.R
import com.yeyintkyaw.themovieapp.adapters.ActorAdapter
import com.yeyintkyaw.themovieapp.data.vos.ActorVO
import com.yeyintkyaw.themovieapp.delegates.ActorViewHolderDelegate
import com.yeyintkyaw.themovieapp.viewholders.ActorViewHolder

class ActorListViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RelativeLayout(context, attrs) {
    lateinit var mActorAdapter: ActorAdapter
    lateinit var mDelegate: ActorViewHolderDelegate

    override fun onFinishInflate() {
        setUpBestActor()
        super.onFinishInflate()


    }


    fun setUpActorViewPod(backgroundColorReference: Int, titleText: String, moreTitleText: String){
        val tvBestActors: TextView = findViewById(R.id.tvBestActors)
        val moreActors: TextView = findViewById(R.id.tvMoreActors)
        setBackgroundColor(ContextCompat.getColor(context, backgroundColorReference))
        tvBestActors.text = titleText
        moreActors.text = moreTitleText
        moreActors.paintFlags = Paint.UNDERLINE_TEXT_FLAG

    }

    fun setData(actors: List<ActorVO>){
        mActorAdapter.setNewData(actors)
    }

    private fun setUpBestActor(){
        mActorAdapter = ActorAdapter()

        val rvBestActors: RecyclerView = findViewById(R.id.rvBestActors)
        rvBestActors.adapter = mActorAdapter
        rvBestActors.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL, false )

    }


}