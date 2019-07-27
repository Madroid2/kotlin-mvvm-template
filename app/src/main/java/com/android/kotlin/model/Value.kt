package com.android.kotlin.model

import com.google.gson.annotations.SerializedName

data class Value(
        @SerializedName("x") var x: Long = 0,
        @SerializedName("y") var y: Double = 0.0
)