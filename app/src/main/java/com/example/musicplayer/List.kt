package com.example.musicplayer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.karumi.dexter.Dexter
import android.Manifest
import android.content.ContentResolver
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.ticket.*
import kotlinx.android.synthetic.main.ticket.view.*
import java.io.File
import java.lang.Exception
import java.lang.reflect.Array
import android.widget.Toast as Toast1

class List : AppCompatActivity() {
    var mp : MediaPlayer ?= null
    var listSongs = ArrayList<SongInfo>()
    var adapter:mysongAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        checkUserPermission()
    }

    inner class mysongAdapter: BaseAdapter{
        var myListSong = ArrayList<SongInfo>()
        constructor(myListSong:ArrayList<SongInfo>) : super(){
            this.myListSong = myListSong
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val myView = layoutInflater.inflate(R.layout.ticket ,null)
            val Song = this.myListSong[position]
            myView.sngName.text = Song.Title
            myView.authName.text = Song.AuthorName
            myView.sngName.setOnClickListener {

            }
            return myView
        }

        override fun getItem(item: Int): Any {
            return this.myListSong[item]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return this.myListSong.size
        }
    }

    fun checkUserPermission(){
        if(Build.VERSION.SDK_INT>=23){
            if(ActivityCompat.checkSelfPermission(this , android.Manifest.permission.READ_EXTERNAL_STORAGE)
                !=PackageManager.PERMISSION_GRANTED){
                requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                    REQUEST_CODE_ASK_PERMISSIONS)
                return
            }
        }
        loadSong()
    }
    private val REQUEST_CODE_ASK_PERMISSIONS = 123

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: kotlin.Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            REQUEST_CODE_ASK_PERMISSIONS-> if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                loadSong()
            }else{
                Toast1.makeText(this , "Permission Denied" , Toast1.LENGTH_SHORT).show()
            }
            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    fun loadSong(){
        val allSongsURI = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val selection = MediaStore.Audio.Media.IS_MUSIC+"!=0"
        val cursor = contentResolver.query(allSongsURI , null , selection ,null , null)
        if(cursor!=null){
            if(cursor.moveToNext()){
                do{
                    val songURL = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA))
                    val songAuthor= cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST))
                    val songName= cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME))
                listSongs.add(SongInfo(songName , songAuthor , songURL))
                }while (cursor.moveToNext())
            }
            cursor.close()
            adapter=mysongAdapter(listSongs)
            songslist.adapter = adapter
        }
    }

}


