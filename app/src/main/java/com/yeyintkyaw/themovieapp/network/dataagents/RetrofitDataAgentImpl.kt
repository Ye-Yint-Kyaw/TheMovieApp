//package com.yeyintkyaw.themovieapp.network.dataagents
//
//import android.util.Log
//import com.yeyintkyaw.themovieapp.data.vos.ActorVO
//import com.yeyintkyaw.themovieapp.data.vos.MovieVO
//import com.yeyintkyaw.themovieapp.network.TheMovieApi
//import com.yeyintkyaw.themovieapp.network.responses.ActorListResponse
//import com.yeyintkyaw.themovieapp.network.responses.MovieListResponse
//import com.yeyintkyaw.themovieapp.utils.BASE_URL
//import okhttp3.OkHttpClient
//import okhttp3.logging.HttpLoggingInterceptor
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//import java.util.concurrent.TimeUnit
//
//object RetrofitDataAgentImpl : MovieDataAgent {
//
//    private var mTheMovieApi: TheMovieApi? = null
//
//    init {
//        val logger = HttpLoggingInterceptor()
//        logger.setLevel(HttpLoggingInterceptor.Level.BASIC)
//
//        val mOkHttpClient = OkHttpClient.Builder()
//            .connectTimeout(15, TimeUnit.SECONDS)
//            .readTimeout(15, TimeUnit.SECONDS)
//            .writeTimeout(15, TimeUnit.SECONDS)
//            .addInterceptor(logger)
//            .build()
//
//        val retrofit = Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .client(mOkHttpClient)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//        mTheMovieApi = retrofit.create(TheMovieApi::class.java)
//    }
//
//    override fun getNowPlayingMovies(
//        onSuccess: (List<MovieVO>) -> Unit,
//        onFailure: (String) -> Unit
//    ) {
//        mTheMovieApi?.getNowPlayingMovies()?.enqueue(object :Callback<MovieListResponse> {
//            override fun onResponse(
//                call: Call<MovieListResponse>,
//                response: Response<MovieListResponse>
//            ) {
//                if (response.isSuccessful){
//                    val movieList = response.body()?.result ?: listOf()
//                    onSuccess(movieList)
//
//                } else{
//                    onFailure(response.message())
//                }
//            }
//
//            override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
//                onFailure(t.message ?: "")
//            }
//        } )
//    }
//
//    override fun getPopularMovies(
//        onSuccess: (List<MovieVO>) -> Unit,
//        onFailure: (String) -> Unit
//    ) {
//        mTheMovieApi?.getPopularMovies()?.enqueue(object : Callback<MovieListResponse>{
//            override fun onResponse(
//                call: Call<MovieListResponse>,
//                response: Response<MovieListResponse>
//            ) {
//                if (response.isSuccessful){
//                    val movieList = response.body()?.result ?: listOf()
//                    onSuccess(movieList)
//                }else{
//                    onFailure(response.message())
//                }
//            }
//
//            override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
//                onFailure(t.message?:"")
//            }
//        } )
//    }
//
//    override fun getTopRatedMovies(
//        onSuccess: (List<MovieVO>) -> Unit,
//        onFailure: (String) -> Unit
//    ) {
//        mTheMovieApi?.getTopRatedMovies()?.enqueue(object : Callback<MovieListResponse> {
//            override fun onResponse(
//                call: Call<MovieListResponse>,
//                response: Response<MovieListResponse>
//            ) {
//                if(response.isSuccessful){
//                    val movieList = response.body()?.result ?: listOf()
//                    onSuccess(movieList)
//                }else{
//                    onFailure(response.message())
//                }
//            }
//
//            override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
//                onFailure(t.message?:"")
//            }
//        } )
//    }
//
//    override fun getPopularActors(
//        onSuccess: (List<ActorVO>) -> Unit,
//        onFailure: (String) -> Unit
//    ) {
//        mTheMovieApi?.getPopularActors()?.enqueue(object : Callback<ActorListResponse> {
//            override fun onResponse(
//                call: Call<ActorListResponse>,
//                response: Response<ActorListResponse>
//            ) {
//                if (response.isSuccessful){
//                    val actorList = response.body()?.results ?: listOf()
//                    onSuccess(actorList)
//                }else{
//                    onFailure(response.message())
//                }
//            }
//
//
//            override fun onFailure(call: Call<ActorListResponse>, t: Throwable) {
//                onFailure(t.message?:"")
//            }
//        } )
//    }
//
//    override fun getMovieDetails(
//        movieId: String,
//        onSuccess: (MovieVO) -> Unit,
//        onFailure: (String) -> Unit
//    ) {
//        mTheMovieApi?.getMovieDetails(movieId = movieId)?.enqueue(object : Callback<MovieVO>{
//            override fun onResponse(call: Call<MovieVO>, response: Response<MovieVO>) {
//                if(response.isSuccessful){
//                    response.body()?.let {
//                        onSuccess(it)
//                    }
//                }else{
//                    onFailure(response.message())
//                }
//            }
//
//            override fun onFailure(call: Call<MovieVO>, t: Throwable) {
//                onFailure(t.message ?: "")
//            }
//
//        } )
//    }
//}