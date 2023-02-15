package com.example.outlook

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GRecyclerView : AppCompatActivity() {

    private lateinit var adaptador: FRecyclerViewAdaptadorElements
    val app=applicationContext as SeccionAPP


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grecycler_view)

        val listSeccion = arrayListOf<Seccion>()
        listSeccion.add(Seccion("segmento1"))
        listSeccion.add(Seccion("segmento2"))

        //var seccions=app.room.seccionDao().getAll()
        app.room.seccionDao().insert(listSeccion)


        val listCorrreos = arrayListOf<Correo>()
        listCorrreos
            .add(Correo("enriq","piedra", "Lorem ipsum dolor sit amet. Et optio voluptatum qui doloremque optio et voluptatibus voluptas in harum maxime et vitae quas aut tempora repellat et velit sequi. Est distinctio omnis eum blanditiis provident ea rerum voluptatem. Non illum magni ad nihil consectetur aut porro consequatur qui minus eligendi. A beatae nemo ut magni distinctio vel consequatur galisum."))
        listCorrreos
            .add(Correo("Enrique","piedra 2", "Est aliquid similique et voluptatem perferendis vel dolor odit ea praesentium assumenda ut odio fugit. Eos voluptate sint qui enim magnam qui corporis alias non obcaecati velit hic esse placeat qui voluptatem maiores qui velit error. Est deleniti facilis eum cumque enim ex rerum dolores."))
        listCorrreos
            .add(Correo("Enrique","piedra 3", "Et neque culpa id vitae beatae aut dolorum officia. In officiis veritatis 33 quia facilis ab dolorum quaerat? Et molestiae ducimus aut numquam rerum est nostrum nisi eos internos eaque qui quisquam quia."))

        val recyclerView = findViewById<RecyclerView>(R.id.grv_correos)


        inicializarRecyclerView(listCorrreos,recyclerView)


        val botonAñadir = findViewById<Button>(R.id.btn_crear_elements)
        botonAñadir
            .setOnClickListener {
                listCorrreos
                    .add(Correo("Enrique","piedra 3", "Et neque culpa id vitae beatae aut dolorum officia. In officiis veritatis 33 quia facilis ab dolorum quaerat? Et molestiae ducimus aut numquam rerum est nostrum nisi eos internos eaque qui quisquam quia."))
                adaptador.notifyDataSetChanged()
            }
    }
    fun inicializarRecyclerView(
        lista:ArrayList<Correo>,
        recyclerView: RecyclerView
    ){
        adaptador = FRecyclerViewAdaptadorElements(
            this,
            lista,
            recyclerView
        )
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()

        val mLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager =mLayoutManager
        //recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)

        val mDividerItemDecoration = DividerItemDecoration(
            recyclerView.context,
            mLayoutManager.orientation
        )
        recyclerView.addItemDecoration(mDividerItemDecoration)



        adaptador.notifyDataSetChanged()
    }

}