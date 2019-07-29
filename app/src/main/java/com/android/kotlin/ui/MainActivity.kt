package com.android.kotlin.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.android.Util.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mShowTrendsBtn: Button = findViewById(R.id.activity_main_show_trends_button)
        mShowTrendsBtn.setOnClickListener {
            val mGraphActivityIntent = Intent(this, BlockChainGraphActivity::class.java)
            startActivity(mGraphActivityIntent)
        }

    }

}


