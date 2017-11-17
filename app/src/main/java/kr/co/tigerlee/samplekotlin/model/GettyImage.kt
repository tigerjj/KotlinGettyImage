package kr.co.tigerlee.samplekotlin.model

import com.google.gson.annotations.SerializedName

/**
 * Created by tigerlee on 2017. 11. 17..
 */
data class GettyImage
(
        @SerializedName("id") val id: String,
        @SerializedName("title") val title: String,
        @SerializedName("caption") val caption: String,
        @SerializedName("display_sizes") val display_sizes: ArrayList<DisplaySize>
)
