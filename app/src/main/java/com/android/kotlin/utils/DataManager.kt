package com.android.kotlin.utils

import com.android.kotlin.model.BitcoinPriceItem
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataManager @Inject constructor(private var apiInterface: ApiInterface) {
    fun getMarketPrice(timeSpan: String,rollingAverage : String): Observable<BitcoinPriceItem>{
        return apiInterface.getMarketPrice(timeSpan,rollingAverage)
    }

}