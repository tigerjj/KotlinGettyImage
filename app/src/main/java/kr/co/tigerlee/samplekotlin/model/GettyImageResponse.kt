package kr.co.tigerlee.samplekotlin.model

import com.google.gson.annotations.SerializedName

/**
 * Created by tigerlee on 2017. 11. 17..
 */
data class GettyImageResponse
(
        @SerializedName("result_count") val resultCount: Int,
        @SerializedName("images") val images: ArrayList<GettyImage>
)