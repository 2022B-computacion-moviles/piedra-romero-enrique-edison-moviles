package com.example.outlook

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.outlook.adapter.CorreoAdapter
import com.example.outlook.adapter.SeccionAdapter

class ViewSeccion : AppCompatActivity() {
    lateinit var seccion: Seccion
    private lateinit var database: AppDataBase
    var correos = emptyList<Correo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_seccion)


        database =AppDataBase.getInstance(this)

/*
        database= Room.databaseBuilder(
            application, AppDataBase::class.java, AppDataBase.DATABASE_NAME)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
*/

        seccion = intent.getSerializableExtra("seccion") as Seccion
        val nombre = findViewById<TextView>(R.id.view_seccion_name)
        nombre.text=seccion.nameseccion


        correos = database.correoDao.get(seccion.id).toMutableList()
        if(correos.isEmpty()){
            when(seccion.nameseccion){
                "Bandeja de entrada"->{
                    database.correoDao.insert(
                        com.example.outlook.Correo(
                            emisor = "Son Goku",
                            receptor = "Yo",
                            mensaje = "Descuento en morras chidas",
                            idseccion = seccion.id
                        )
                    )
                    database.correoDao.insert(
                        com.example.outlook.Correo(
                            emisor = "Maria",
                            receptor = "Yo",
                            mensaje = "Descuento en morras chidas",
                            idseccion = seccion.id
                        )
                    )
                    database.correoDao.insert(
                        com.example.outlook.Correo(
                            emisor = "Henry",
                            receptor = "Yo",
                            mensaje = "Descuento en morras chidas",
                            idseccion = seccion.id
                        )
                    )
                    database.correoDao.insert(
                        com.example.outlook.Correo(
                            emisor = "Edison",
                            receptor = "Yo",
                            mensaje = "Descuento en morras chidas",
                            idseccion = seccion.id
                        )
                    )


                }
                "Elementos enviados"->{
                    database.correoDao.insert(
                        com.example.outlook.Correo(
                            emisor = "Yo",
                            receptor = "Paco",
                            mensaje = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                            idseccion = seccion.id
                        )
                    )
                    database.correoDao.insert(
                        com.example.outlook.Correo(
                            emisor = "Yo",
                            receptor = "Vannesa",
                            mensaje = "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt.",
                            idseccion = seccion.id
                        )
                    )
                    database.correoDao.insert(
                        com.example.outlook.Correo(
                            emisor = "Yo",
                            receptor = "Tao",
                            mensaje = "I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness. No one rejects, dislikes, or avoids pleasure itself, because it is pleasure, but because those who do not know how to pursue pleasure rationally encounter consequences that are extremely painful.",
                            idseccion = seccion.id
                        )
                    )
                }

                "Elementos eliminados"-> {
                    database.correoDao.insert(
                        com.example.outlook.Correo(
                            emisor = "Yo",
                            receptor = "Jorge",
                            mensaje = "At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. ",
                            idseccion = seccion.id
                        )
                    )
                    database.correoDao.insert(
                        com.example.outlook.Correo(
                            emisor = "Karoline",
                            receptor = "Yo",
                            mensaje = "Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat.",
                            idseccion = seccion.id
                        )
                    )
                }

                "Correo no deseado"-> {
                    database.correoDao.insert(
                        com.example.outlook.Correo(
                            emisor = "Spam",
                            receptor = "Yo",
                            mensaje = "Buenas noticias, Has sido seleccionado para formar parte del equipo de Facebook (Meta) como asistente en línea para la última tendencia. Trabaja menos de media hora al día para ganar hasta \$300 diarios. (SÓLO POR TIEMPO LIMITADO) Contáctanos para reservar tu lugar ahora. (El solicitante debe tener al menos 21 años o más)\n" +
                                    "\n" +
                                    "WhatsApp: wa.me/51943653238\n" +
                                    "\n" +
                                    "WhatsApp:+51943653238",
                            idseccion = seccion.id
                        )
                    )

                    database.correoDao.insert(
                        com.example.outlook.Correo(
                            emisor = "Espantaviejas (Kevin)",
                            receptor = "Yo",
                            mensaje = "Uffff",
                            idseccion = seccion.id
                        )
                    )
                }


            }



            correos = database.correoDao.get(seccion.id).toMutableList()
        }


        val recyclerViewCorreos = findViewById<RecyclerView>(R.id.rv_correos)
        initializeRecyclerView(correos,recyclerViewCorreos)

    }

    private fun initializeRecyclerView(list: List<Correo>, recyclerView: RecyclerView) {
        val manager= LinearLayoutManager(this)
        val decoration= DividerItemDecoration(this, manager.orientation)

        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = manager
        //recyclerView.adapter = CorreoAdapter(list) { correo, seccion -> onItemSelected(correo, seccion) }
        recyclerView.adapter = CorreoAdapter(list, { correo -> onCorreoSelected(correo) }, { seccion -> onSeccionSelected(seccion) })
        recyclerView.addItemDecoration(decoration)
    }

    private fun onCorreoSelected(correo: Correo) {
        // Manejar evento de selección de correo
        Toast.makeText(this, "seccion seleccionado: ${correo.emisor}", Toast.LENGTH_SHORT).show()

    }

    private fun onSeccionSelected(seccion: Seccion) {
        // Manejar evento de selección de sección
        Toast.makeText(this, "seccion seleccionado: ${seccion.nameseccion}", Toast.LENGTH_SHORT).show()
    }



}