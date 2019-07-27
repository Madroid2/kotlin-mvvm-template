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
        val button: Button = findViewById(R.id.activity_main_button)
        button.setOnClickListener {
            val intent = Intent(this, BlockChainGraphActivity::class.java)
            startActivity(intent)
        }

    }

}


