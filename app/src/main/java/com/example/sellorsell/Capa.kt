package com.example.sellorsell

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class Capa : AppCompatActivity() {

    private lateinit var btnCapa: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_capa)

         btnCapa = findViewById(R.id.imageView)
        btnCapa.setOnClickListener {
            val i = Intent(this@Capa, MainActivity::class.java)
            startActivity(i)

        }





    }
}