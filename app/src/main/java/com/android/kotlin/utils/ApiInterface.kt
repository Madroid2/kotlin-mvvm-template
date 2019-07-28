package com.android.kotlin.utils

import com.android.kotlin.model.BitcoinPriceItem
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("market-price")
    fun getMarketPrice(@Query("timespan") timeSpan: String,
                       @Query("rollingAverage") rollingAverage: String): Observable<BitcoinPriceItem>
}