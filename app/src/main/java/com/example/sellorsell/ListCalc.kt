package com.example.sellorsell

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sellorsell.model.Calc
import java.text.SimpleDateFormat
import java.util.*

class ListCalc : AppCompatActivity() {
    private lateinit var rvList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_calc)

        val result = mutableListOf<Calc>()
        val adapter = ListMainAdapter(result)
        rvList = findViewById(R.id.list_view)
        rvList.layoutManager = LinearLayoutManager(this)
        rvList.adapter = adapter

        val type = intent?.extras?.getString("type") ?: throw java.lang
            .IllegalStateException("type not found")


        //val type = intent?.extras?.getString("type") ?: throw IllegalStateException("type not found")
        // buscar no banco esse tipo
        Thread {
            val app = application as App
            val dao = app.db.calDao()
            val response = dao.getRegisterByType(type)

            runOnUiThread {
                Log.i("teste", "Tudo ok")
                Log.i("Teste", "resposta: $response")
                result.addAll(response)
                adapter.notifyDataSetChanged()
            }
        }.start()

    }

    private inner class ListMainAdapter(
        private val listCac: List<Calc>,
        //private val onItemClickListener: (Int) -> Unit,
    ) : RecyclerView.Adapter<ListMainAdapter.ListMainViewHolder>() {



        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListMainViewHolder {
            val view = layoutInflater.inflate(android.R.layout.simple_list_item_1 , parent, false)
            return ListMainViewHolder(view)
        }

        override fun getItemCount(): Int {
            return listCac.size
        }

        override fun onBindViewHolder(holder: ListMainViewHolder, position: Int) {
            val itemCurrent = listCac[position]
            holder.bind(itemCurrent)

        }

        private inner class ListMainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun bind(item: Calc) {
                //val img: ImageView = itemView.findViewById(R.id.img_nome)
                //val name: TextView = itemView.findViewById(R.id.txt_nome)
                // val container: LinearLayout = itemView.findViewById(R.id.container_item)

                val tv = itemView as TextView

                // img.setImageResource(item.drawableId)
                //name.setText(item.textStringId)
                // container.setBackgroundColor(item.color)

                // container.setOnClickListener {
                //onItemClickListener.invoke(item.id)

                val res = item.res
                val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale("pt", "BR"))
                val data = sdf.format(item.createDate)
                tv.text = getString(R.string.list_response, res, data)
            }
        }
    }
}