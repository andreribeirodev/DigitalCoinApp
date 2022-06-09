package com.andreribeiro.moedasdigitais.db.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "coins")
data class CoinEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "asset_id")
    val assetId: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "volume_hrs_usd")
    val volumeHrsUsd: Float,
    @ColumnInfo(name = "volume_day_usd")
    val volumeDayUsd: Float,
    @ColumnInfo(name = "volume_mth_usd")
    val volumeMthUsd: Float,
    @ColumnInfo(name = "price_usd")
    val priceUsd: Float,
    @ColumnInfo(name = "icon_id")
    var iconId: String
) : Parcelable {
    fun cryptoImageFavorite(): String {
        if (iconId != null) {
            iconId = iconId?.replace("-".toRegex(), "")
            return "https://s3.eu-central-1.amazonaws.com//bbxt-static-icons/type-id/png_512/$iconId.png"
        } else {
            return "https://media.atkinsonsbullion.com/AtkinsonsBullion/media/product/auco2614/auco2614_1.png"
        }
    }
}
