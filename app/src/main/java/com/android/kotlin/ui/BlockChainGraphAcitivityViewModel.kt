package com.android.kotlin.ui

import com.android.kotlin.model.BitcoinPriceItem
import com.android.kotlin.utils.BaseSchedulerProvider
import com.android.kotlin.utils.DataManager
import io.reactivex.Observable
import javax.inject.Inject

class BlockChainGraphActivityViewModel @Inject constructor(private var dataManager: DataManager,
                                                           private var schedulerProvider: BaseSchedulerProvider) {

    var loading: Boolean? = null

    fun setIsLoading(loading: Boolean) {
        this.loading = loading
    }


    fun provideBitcoinPrice(timeSpan: String,rollingAverage: String): Observable<BitcoinPriceItem>? {
        setIsLoading(true)
        return dataManager.getMarketPrice(timeSpan,rollingAverage)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .map { result -> result }

    }
}