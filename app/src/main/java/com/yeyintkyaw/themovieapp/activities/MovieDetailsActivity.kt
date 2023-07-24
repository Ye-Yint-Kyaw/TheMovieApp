package com.yeyintkyaw.themovieapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.yeyintkyaw.themovieapp.R
import com.yeyintkyaw.themovieapp.data.models.MovieModel
import com.yeyintkyaw.themovieapp.data.models.MovieModelImpl
import com.yeyintkyaw.themovieapp.data.vos.ActorVO
import com.yeyintkyaw.themovieapp.data.vos.MovieVO
import com.yeyintkyaw.themovieapp.utils.IMAGE_BASE_URL
import com.yeyintkyaw.themovieapp.viewpods.ActorListViewPod

class MovieDetailsActivity : AppCompatActivity() {
    companion object{
        private const val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"

        fun newIntent(context: Context, movieId: Int): Intent {
            val intent = Intent(context, MovieDetailsActivity::class.java)
            intent.putExtra(EXTRA_MOVIE_ID, movieId)
            return intent
        }
    }


    //View Pods
    lateinit var actorsViewPod: ActorListViewPod
    lateinit var creatorsViewPod: ActorListViewPod

    //
    private val mMovieModel: MovieModel = MovieModelImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

//        window.setFlags(
//            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
//            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
//        )

        setUpViewPods()
//        setUpActor()
        goBack()


        val movieId = intent?.getIntExtra(EXTRA_MOVIE_ID , 0)
        movieId?.let {
            requestData(it)
        }

    }

    private fun requestData(movieId: Int){
        mMovieModel.getMovieDetails(
            movieId = movieId.toString(),
            onFailure = {
                ///
            })?.observe(this){
                it?.let {
                    movieDetails -> bindData(movieDetails)
                }
        }

        mMovieModel.getPopularActors()?.observe(this){
            it?.let {
               val vpActors: ActorListViewPod = findViewById(R.id.vpActors)
                vpActors.setData(it)
            }
        }

        mMovieModel.getPopularActors()?.observe(this){
            it?.let {
                val vpCreator: ActorListViewPod = findViewById(R.id.vpCreators)
                vpCreator.setData(it)
            }
        }
    }

//    private fun setUpActor(){
//        mMovieModel.getPopularActors{
//
//        }
//    }
    private fun goBack(){
        val back: ImageView = findViewById(R.id.back)
        back.setOnClickListener{
            super.onBackPressed()
        }
    }



    private fun setUpViewPods(){
        val vpActors: ActorListViewPod = findViewById(R.id.vpActors)
        val vpCreator: ActorListViewPod = findViewById(R.id.vpCreators)
        actorsViewPod = vpActors as ActorListViewPod
        actorsViewPod.setUpActorViewPod(
            backgroundColorReference = R.color.colorPrimary,
            titleText = getString(R.string.lbl_actors),
            moreTitleText = ""
        )

        creatorsViewPod = vpCreator as ActorListViewPod
        creatorsViewPod.setUpActorViewPod(
            backgroundColorReference = R.color.colorPrimary,
            titleText = getString(R.string.lbl_creators),
            moreTitleText = getString(R.string.lbl_more_creators)
        )
    }

    private fun bindData(movie: MovieVO){
        val ivMovieDetail: ImageView = findViewById(R.id.ivMovieDetail)
        val tvMovieName: TextView = findViewById(R.id.tvMovieName)
        val tvReleaseDate: TextView = findViewById(R.id.tvReleaseYear)
        val rbRatingStar: RatingBar = findViewById(R.id.rbMovieDetailRating)
        val tvRating: TextView = findViewById(R.id.tvRating)
        val tvNumberOfVote: TextView = findViewById(R.id.tvNumberOfVotes)
        val tvOverView: TextView = findViewById(R.id.tvOverview)

        Glide.with(this)
            .load("$IMAGE_BASE_URL${movie.posterPath}")
            .into(ivMovieDetail)
        tvMovieName.text = movie.title?:""
        tvReleaseDate.text = movie.releaseDate?.substring(0,3)
        rbRatingStar.rating = movie.getRatingBasedOnFiveStars()
        tvRating.text = movie.voteAverage.toString()
        movie.voteCount?.let {
            tvNumberOfVote.text = "$it VOTES"
        }
        tvOverView.text = movie.overView


    }



}