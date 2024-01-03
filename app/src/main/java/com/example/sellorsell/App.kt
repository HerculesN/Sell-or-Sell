package com.example.sellorsell

import android.app.Application
import com.example.sellorsell.model.AppDataBase

class App : Application() {

    lateinit var db: AppDataBase

    override fun onCreate(){
        super.onCreate()
        db = AppDataBase.getDataBase(this)
    }
}