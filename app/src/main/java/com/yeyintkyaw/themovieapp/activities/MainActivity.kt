package com.yeyintkyaw.themovieapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator
import com.yeyintkyaw.themovieapp.R
import com.yeyintkyaw.themovieapp.adapters.BannerAdapter
import com.yeyintkyaw.themovieapp.adapters.ShowcaseAdapter
import com.yeyintkyaw.themovieapp.data.models.MovieModel
import com.yeyintkyaw.themovieapp.data.models.MovieModelImpl
import com.yeyintkyaw.themovieapp.data.vos.ActorVO
import com.yeyintkyaw.themovieapp.data.vos.GenreVO
import com.yeyintkyaw.themovieapp.data.vos.MovieVO
import com.yeyintkyaw.themovieapp.databinding.ActivityMainBinding
import com.yeyintkyaw.themovieapp.delegates.BannerViewHolderDelegate
import com.yeyintkyaw.themovieapp.delegates.MovieViewHolderDelegate
import com.yeyintkyaw.themovieapp.delegates.ShowcaseVIewHolderDelegate
import com.yeyintkyaw.themovieapp.dummy.dummyGenreList
import com.yeyintkyaw.themovieapp.mvp.presenters.MainPresenter
import com.yeyintkyaw.themovieapp.mvp.presenters.MainPresenterImpl
import com.yeyintkyaw.themovieapp.mvp.views.MainView
import com.yeyintkyaw.themovieapp.viewpods.ActorListViewPod
import com.yeyintkyaw.themovieapp.viewpods.MovieListViewPod

class MainActivity : AppCompatActivity(), MainView {

    lateinit var mBannerAdapter: BannerAdapter
    lateinit var mShowcaseAdapter: ShowcaseAdapter

    lateinit var mBestPopularMovieListViewPod: MovieListViewPod
    lateinit var mMoviesByGenres: MovieListViewPod
    lateinit var mActorListViewPod: ActorListViewPod

    //Model
    private val mMovieModel: MovieModel = MovieModelImpl

    //Presenter
    private lateinit var mPresenter: MainPresenter

    private  lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpPresenter()
        setUpToolbar()
        setUpViewPod()
        setUpBannerViewPager()
        setUpTabLayoutGenre()
        setUpListeners()
        setUpShowcase()
//        onTapSearchMovie()
//        requestLayer()
        mPresenter.onUiReady(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return super.onOptionsItemSelected(item)
    }

    private fun setUpPresenter(){
        mPresenter = ViewModelProvider(this)[MainPresenterImpl::class.java]
        (mPresenter as MainPresenterImpl).initView(this)
    }


//    private fun requestLayer(){
//        mMovieModel.getNowPlayingMovies{
//
//        }?.observe(this) {
//            mBannerAdapter.setNewData(it)
//        }
//
//        mMovieModel.getTopRatedMovies {
//
//        }?.observe(this){
//            mMoviesByGenres.setData(it)
//        }
//
//
//        mMovieModel.getPopularMovies{
//
//        }?.observe(this) {
//            mBestPopularMovieListViewPod.setData(it)
//        }
//
//
//        mMovieModel.getTopRatedMovies{
//
//        }?.observe(this) {
//            mShowcaseAdapter.setNewData(it)
//        }
//
//        mMovieModel.getPopularActors()?.observe(this){
//            it?.let { actorList->
//                mActorListViewPod.setData(it)
//            }
//        }
//    }

    private fun setUpViewPod(){
//        mBestPopularMovieListViewPod = binding.vpBestPopularMovieList.root
        val  vpBestPopularMovieList: MovieListViewPod = findViewById(R.id.vpBestPopularMovieList)
        mBestPopularMovieListViewPod = vpBestPopularMovieList as MovieListViewPod
        mBestPopularMovieListViewPod.setUpMovieListViewPod(mPresenter)

//        mMoviesByGenres = binding.vpMoviesByGenre.root
        val vpMoviesByGenres: MovieListViewPod = findViewById(R.id.vpMoviesByGenre)
        mMoviesByGenres = vpMoviesByGenres as MovieListViewPod
        mMoviesByGenres.setUpMovieListViewPod(mPresenter)

//        mActorListViewPod = binding.vpBestActor.root
        val vpBestActor: ActorListViewPod = findViewById(R.id.vpBestActor)
        mActorListViewPod = vpBestActor as ActorListViewPod
    }
//    private fun setUpViewPod(){
//        val vpBestPopularMovieList: MovieListViewPod = findViewById(R.id.vpBestPopularMovieList)
//        mBestPopularMovieListViewPod = vpBestPopularMovieList as MovieListViewPod
//        mBestPopularMovieListViewPod.setUpMovieListViewPod(this)
//
//        val vpMoviesByGenre: MovieListViewPod = findViewById(R.id.vpMoviesByGenre)
//        mMoviesByGenres = vpMoviesByGenre as MovieListViewPod
//        mMoviesByGenres.setUpMovieListViewPod(this)
//
//        val vpBestActor: ActorListViewPod = findViewById(R.id.vpBestActor)
//        mActorListViewPod = vpBestActor as ActorListViewPod
//    }

    private fun setUpBannerViewPager(){
        val dotsIndicitorBanner: SpringDotsIndicator = findViewById(R.id.dotsIndicitorBanner)
        val viewPagerBanner: ViewPager2 = findViewById(R.id.viewPagerBanner)
        mBannerAdapter = BannerAdapter(mPresenter)
        viewPagerBanner.adapter = mBannerAdapter
        dotsIndicitorBanner.attachTo(viewPagerBanner)
    }

    private fun setUpToolbar(){
        val toolBar: MaterialToolbar = findViewById(R.id.toolBar)
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
    }

    private fun setUpTabLayoutGenre(){
        val tabLayoutGenre: TabLayout = findViewById(R.id.tabLayoutGenre)
        dummyGenreList.forEach{
           tabLayoutGenre.newTab().apply {
              text = it
              tabLayoutGenre.addTab(this)
           }
        }
    }

    private fun setUpListeners(){
        val tabLayoutGenre : TabLayout = findViewById(R.id.tabLayoutGenre)
        tabLayoutGenre.addOnTabSelectedListener(object : OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                Snackbar.make(window.decorView, tab?.text ?: "" , Snackbar.LENGTH_LONG).show()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
    }

    private fun setUpShowcase(){
        val rvShowcase: RecyclerView = findViewById(R.id.rvShowcases)
        mShowcaseAdapter = ShowcaseAdapter(mPresenter)
        rvShowcase.adapter = mShowcaseAdapter
        rvShowcase.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)

    }
//
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.menu_discover, menu)
//        return true
//    }
//
//    override fun onTapMovieFromBanner(movieVO: MovieVO) {
//        startActivity(MovieDetailsActivity.newIntent(this, movieVO.id))
//    }
//
//    override fun onTapMovieFromShowcase(movieVO: MovieVO) {
//        startActivity(MovieDetailsActivity.newIntent(this, movieVO.id))
//    }
//
//    override fun onTapMovie(movieVO: MovieVO) {
//        startActivity(MovieDetailsActivity.newIntent(this, movieVO.id))
//    }
//    fun onTapSearchMovie(){
//        val toolBar: MaterialToolbar = findViewById(R.id.toolBar)
//        toolBar.setOnClickListener {
//            startActivity(MovieSearchActivity.newIntent(this))
//        }
//    }

    override fun showNowPlayingMovies(nowPlayingMovies: List<MovieVO>) {
        mBannerAdapter.setNewData(nowPlayingMovies)
    }

    override fun showPopularMovies(popularMovies: List<MovieVO>) {
        mBestPopularMovieListViewPod.setData(popularMovies)
    }

    override fun showTopRatedMovies(topRatedMovies: List<MovieVO>) {
        mShowcaseAdapter.setNewData(topRatedMovies)
    }

    override fun showGenres(genreList: List<GenreVO>) {
        TODO("Not yet implemented")
    }

    override fun showActors(actorList: List<ActorVO>) {
        mActorListViewPod.setData(actorList)
    }

    override fun showNavigateToMovieDetailScreen(movieId: Int) {
        startActivity(MovieDetailsActivity.newIntent(this, movieId = movieId))
    }

    override fun showError(errorString: String) {
        TODO("Not yet implemented")
    }
}