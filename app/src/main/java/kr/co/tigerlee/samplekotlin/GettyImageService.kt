package kr.co.tigerlee.samplekotlin

import kr.co.tigerlee.samplekotlin.model.GettyImageResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


/**
 * Created by tigerlee on 2017. 11. 17..
 */
interface GettyImageService {
    @Headers("Api-Key: yourkey")
    @GET("search/images")
    fun gettyImages(@Query("phrase") phrase: String, @Query("page") page: Int, @Query("page_size") pageSize: Int): Call<GettyImageResponse>
}