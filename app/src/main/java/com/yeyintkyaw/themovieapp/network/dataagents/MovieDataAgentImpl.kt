//package com.yeyintkyaw.themovieapp.network.dataagents
//
//import android.os.AsyncTask
//import android.util.Log
//import com.google.gson.Gson
//import com.yeyintkyaw.themovieapp.data.vos.ActorVO
//import com.yeyintkyaw.themovieapp.data.vos.MovieVO
//import com.yeyintkyaw.themovieapp.network.responses.MovieListResponse
//import com.yeyintkyaw.themovieapp.utils.API_GET_NOW_PLAYING
//import com.yeyintkyaw.themovieapp.utils.BASE_URL
//import com.yeyintkyaw.themovieapp.utils.MOVIE_API_KEY
//import java.io.BufferedReader
//import java.io.IOException
//import java.io.InputStreamReader
//import java.lang.Exception
//import java.lang.StringBuilder
//import java.net.HttpURLConnection
//import java.net.URL
//
//object MovieDataAgentImpl : MovieDataAgent {
//    override fun getNowPlayingMovies(
//        onSuccess: (List<MovieVO>) -> Unit,
//        onFailure: (String) -> Unit
//    ) {
//        GetNowPlayingMoviesTask().execute()
//    }
//
//    override fun getPopularMovies(onSuccess: (List<MovieVO>) -> Unit, onFailure: (String) -> Unit) {
//
//    }
//
//    override fun getTopRatedMovies(
//        onSuccess: (List<MovieVO>) -> Unit,
//        onFailure: (String) -> Unit
//    ) {
//
//    }
//
//    override fun getPopularActors(
//        onSuccess: (List<ActorVO>) -> Unit,
//        onFailure: (String) -> Unit
//    ) {
//
//    }
//
//    class GetNowPlayingMoviesTask(): AsyncTask<Void, Void, MovieListResponse>(){
//        override fun doInBackground(vararg p0: Void?): MovieListResponse? {
//            val url: URL
//            var  reader: BufferedReader? = null
//            val stringBuilder: StringBuilder
//
//            try {
//                url = URL("""$BASE_URL$API_GET_NOW_PLAYING?api_key=$MOVIE_API_KEY&language=en-US&page=1""")
//                val connection = url.openConnection() as HttpURLConnection
//
//                //Set Up HTTP Method
//                connection.requestMethod = "GET"
//
//                //give it 15 seconds to response
//                connection.readTimeout = 15 * 1000
//                connection.doInput = true
//                connection.doOutput = false
//
//                connection.connect()
//
//                //Read the output from the server
//                reader = BufferedReader(
//                    InputStreamReader(connection.inputStream)
//                )
//                stringBuilder = StringBuilder()
//
//                for (line in reader.readLines()){
//                    stringBuilder.append(line + "\n")
//                }
//
//                val responseString = stringBuilder.toString()
//                Log.d("NowPlayingMoves", responseString)
//
//                val MovieListResponse = Gson().fromJson(
//                    responseString,
//                    MovieListResponse::class.java
//                )
//                return  MovieListResponse
//
//            } catch (e: Exception){
//                e.printStackTrace()
//                Log.e("NewsError", e.message ?: "")
//            } finally {
//                if(reader != null){
//                    try {
//                        reader.close()
//                    }catch (ioe: IOException){
//                        ioe.printStackTrace()
//                    }
//                }
//            }
//
//            return null
//
//        }
//
//        override fun onPostExecute(result: MovieListResponse?) {
//            super.onPostExecute(result)
//        }
//
//    }
//}