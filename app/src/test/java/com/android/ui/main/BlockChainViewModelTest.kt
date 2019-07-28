package com.android.ui.main

import com.android.kotlin.model.BitcoinPriceItem
import com.android.kotlin.ui.BlockChainGraphActivityViewModel
import com.android.kotlin.utils.ApiInterface
import com.android.kotlin.utils.DataManager
import com.android.kotlin.utils.TrampolineScheduleProvider
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class BlockChainViewModelTest {
    @Mock
    private lateinit var mockRepository: DataManager
    private var apiInterface = mock(ApiInterface::class.java)
    private val schedulerProvider = TrampolineScheduleProvider()

    private lateinit var mainActivityViewModel: BlockChainGraphActivityViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mockRepository = DataManager(apiInterface)
        mainActivityViewModel = BlockChainGraphActivityViewModel(mockRepository, schedulerProvider)
    }

    @Test
    fun showDataFromApi() {
        Mockito.`when`(mockRepository.getMarketPrice("5weeks", "8hours")).thenReturn(Observable.just(BitcoinPriceItem("ok", "", "", "", "", null)))
        val testObserver = TestObserver<BitcoinPriceItem>()
        mainActivityViewModel.provideBitcoinPrice("5weeks", "8hours")?.subscribe(testObserver)
        testObserver.assertNoErrors()
        testObserver.assertValue { ipAddress -> ipAddress.status.equals("ok") }

    }

}

