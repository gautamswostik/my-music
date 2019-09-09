package com.example.musicplayer

import android.support.design.widget.TabItem

class SongInfo {
    var Title : String ? = null
    var AuthorName : String ? = null
    var songURL : String ? = null
    constructor(Title:String, AuthorName:String,songURL:String){
        this.Title = Title
        this.AuthorName = AuthorName
        this.songURL = songURL
    }

}