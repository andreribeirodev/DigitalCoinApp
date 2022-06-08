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
    var iconId: String,
) : Parcelable
