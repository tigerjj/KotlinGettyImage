package kr.co.tigerlee.samplekotlin.model

import com.google.gson.annotations.SerializedName

/**
 * Created by tigerlee on 2017. 11. 17..
 */
data class DisplaySize
(
        @SerializedName("is_watermarked") var isWaterMarked: Boolean,
        @SerializedName("name") var name: String,
        @SerializedName("uri") var uri: String
)