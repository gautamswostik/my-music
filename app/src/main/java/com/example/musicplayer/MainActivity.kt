package com.example.musicplayer

import android.annotation.SuppressLint
import android.content.Intent
import android.media.Image
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_splash_screen.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    var mp: MediaPlayer ? = null
    val postion : Int = 0
    var totalTime : Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val string : String = intent.getStringExtra("songs")

//        play.setOnClickListener {
//            string.
//        }

        list.setOnClickListener{
            val intent = Intent(this , List::class.java)
            Toast.makeText(this , "Songs" , Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }
    }

}
