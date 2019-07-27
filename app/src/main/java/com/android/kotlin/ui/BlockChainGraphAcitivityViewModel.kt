package com.android.kotlin.ui

import com.android.kotlin.model.BitcoinPriceItem
import com.android.kotlin.utils.DataManager
import com.android.kotlin.utils.SchedulerProvider
import io.reactivex.Observable
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(private var dataManager: DataManager,
                                        private var schedulerProvider: SchedulerProvider) {

    var loading: Boolean? = null

    fun setIsLoading(loading: Boolean) {
        this.loading = loading
    }


    fun provideBitcoinPrice(timeSpan: String,rollingAverage: String): Observable<BitcoinPriceItem>? {
        setIsLoading(true)
        return dataManager.getMarketPrice(timeSpan,rollingAverage)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .doOnError {  }
                .map { result -> result }

    }
}