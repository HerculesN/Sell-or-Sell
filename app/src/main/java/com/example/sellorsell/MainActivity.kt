package com.example.sellorsell

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainItens = mutableListOf<MainItem>()
        mainItens.add(
            MainItem(
                id = 1,
                drawableId = R.drawable.gravata,
                textStringId = R.string.homem,
                color = Color.TRANSPARENT
            )
        )
        mainItens.add(
            MainItem(
                id = 2,
                drawableId = R.drawable.cachicol,
                textStringId = R.string.mulher,
                color = Color.TRANSPARENT
            )
        )
        mainItens.add(
            MainItem(
                id = 3,
                drawableId = R.drawable.chocalho,
                textStringId = R.string.bebe,
                color = Color.TRANSPARENT
            )
        )

        val adapter = MainAdapter(mainItens) { id ->
            when (id) {
                1 -> {
                    val i = Intent(this@MainActivity, Store::class.java)
                    startActivity(i)
                }
            }
        }


        rvView = findViewById(R.id.rv_item)
        rvView.adapter = adapter
        rvView.layoutManager = LinearLayoutManager(this)

    }

    private inner class MainAdapter(
        private val mainItem: List<MainItem>,
        private val onItemClickListener: (Int) -> Unit,

        ) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
            val view = layoutInflater.inflate(R.layout.main_item, parent, false)
            return MainViewHolder(view)
        }

        override fun getItemCount(): Int {
            return mainItem.size
        }

        override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
            val itenCurrent = mainItem[position]
            holder.bind(itenCurrent)
        }





        private inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun bind(item: MainItem) {
                val img: ImageView = itemView.findViewById(R.id.img_item)
                val name: TextView = itemView.findViewById(R.id.txt_item)
                val container: LinearLayout = itemView.findViewById(R.id.container_item)

                img.setImageResource(item.drawableId)
                name.setText(item.textStringId)
                container.setBackgroundColor(item.color)

                container.setOnClickListener {
                    onItemClickListener.invoke(item.id)
                }
            }
        }
    }

}