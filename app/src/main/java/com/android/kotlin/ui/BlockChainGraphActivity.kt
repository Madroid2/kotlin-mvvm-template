package com.android.kotlin.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.android.kotlin.MobileTestApplication
import com.android.kotlin.di.components.DaggerActivityComponent
import com.android.kotlin.di.modules.ActivityModule
import com.android.kotlin.model.BitcoinPriceItem
import com.android.kotlin.utils.SchedulerProvider
import com.android.Util.R
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject
import com.jjoe64.graphview.series.LineGraphSeries
import com.jjoe64.graphview.series.DataPoint
import java.sql.Timestamp
import java.util.*


class BlockChainGraphActivity : AppCompatActivity() {

    @Inject
    lateinit var mainViewModel: MainActivityViewModel
    private lateinit var compositeDisposable: CompositeDisposable
    private var disposable: Disposable? = null
    private var noConnectionView: View? = null
    private var progressBar: TextView? = null
    private var graphView: GraphView? = null
    private var blockChainDescTextView: TextView? = null
    private var blockChainMarketCurrencyTextView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_block_chain_graph)
        val activityComponent = DaggerActivityComponent.builder()
                .appComponent(MobileTestApplication.get(this).component())
                .activityModule(ActivityModule(SchedulerProvider()))
                .build()
        activityComponent.inject(this)
        initializeUI()
        executeBitcoinMarketPriceApi()
        compositeDisposable = CompositeDisposable()
    }

    private fun initializeUI(){
        progressBar = findViewById(R.id.activity_block_chain_progress_bar)
        noConnectionView = findViewById(R.id.activity_block_chain_graph_no_conn_view)
        graphView = findViewById(R.id.activity_block_chain_graph)
        blockChainMarketCurrencyTextView = findViewById(R.id.activity_block_chain_title_text_view)
        blockChainDescTextView = findViewById(R.id.activity_block_chain_desc_text_view)
    }

    private fun executeBitcoinMarketPriceApi() {
        progressBar?.visibility = View.VISIBLE
        disposable = mainViewModel.provideBitcoinPrice("5weeks", "8hours")
                ?.subscribe(
                        { result ->
                            setPageAndGraphData(result)
                            progressBar?.visibility = View.GONE
                            mainViewModel.setIsLoading(false)
                        },
                        { e ->
                            e.printStackTrace()
                            noConnectionView?.visibility = View.VISIBLE
                            progressBar?.visibility = View.GONE
                        }
                )
    }

    private fun setPageAndGraphData(result : BitcoinPriceItem){
        blockChainMarketCurrencyTextView?.text = result.name
        blockChainDescTextView?.text = result.description
        val items: Array<DataPoint> = Array(result.values!!.size)
        { index ->
            val stamp = Timestamp(result.values?.get(index)!!.x * 1000)
            val date = Date(stamp.getTime())
            DataPoint(date, result.values?.get(index)?.y!!.toDouble())
        }
        val series = LineGraphSeries(items)
        graphView?.addSeries(series)
        graphView?.getGridLabelRenderer()?.setLabelFormatter(DateAsXAxisLabelFormatter(this))
    }

    override fun onPause() {
        super.onPause()
        if (compositeDisposable.size() > 0) {
            compositeDisposable.clear()
        }
    }

    override fun onResume() {
        super.onResume()
        if (disposable != null) {
            compositeDisposable.add(disposable!!)
        }
    }
}
