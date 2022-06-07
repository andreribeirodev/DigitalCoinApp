package com.andreribeiro.moedasdigitais.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CoinModel(
    @SerializedName("asset_id")
    val Id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("type_is_crypto")
    val type: Int,
    @SerializedName("volume_1hrs_usd")
    val volumeHrsUsd: Float,
    @SerializedName("volume_1day_usd")
    val volumeDayUsd: Float,
    @SerializedName("volume_1mth_usd")
    val volumeMthUsd: Float,
    @SerializedName("price_usd")
    val priceUsd: Float,
    @SerializedName("id_icon")
    var iconId: String?,
    @SerializedName("data_start")
    val dataStart: String,
    @SerializedName("data_end")
    val dataEnd: String

) : Parcelable {

    fun cryptoImage(): String {
        if (iconId != null) {
            iconId = iconId?.replace("-".toRegex(), "")
            return "https://s3.eu-central-1.amazonaws.com//bbxt-static-icons/type-id/png_512/$iconId.png"
        } else {
            return "https://media.atkinsonsbullion.com/AtkinsonsBullion/media/product/auco2614/auco2614_1.png"
        }
    }
}
