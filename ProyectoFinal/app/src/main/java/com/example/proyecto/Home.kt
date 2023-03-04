package com.example.proyecto

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto.adapter.RcVwAdapterInstruments
import com.example.proyecto.data.entity.Instrument
import com.example.proyecto.data.firebase.FirebaseGlobal
import com.example.proyecto.data.firebase.FirebaseInstruments
import com.example.proyecto.data.util.DataInstruments
import com.google.firebase.auth.FirebaseAuth

class Home : AppCompatActivity() {
    private var selectedInstrumentCode: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val btnCerrar = findViewById<Button>(R.id.btn_home_close)
        val emailText = findViewById<TextView>(R.id.home_email)
        var emailUser= intent.getStringExtra("emailUser")

        emailText.text=emailUser

        btnCerrar.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }


        for (instrument in DataInstruments.instrumentsData) {
            FirebaseGlobal.firebaseInstruments.create(instrument)
        }

        val rvInstruments=findViewById<RecyclerView>(R.id.rv_intruments)

        FirebaseGlobal.firebaseInstruments.getAllInstruments { instruments ->
            //var aux=""
            //for (instrument in instruments) {
            //    aux+=instrument.nombre +" ";
            //}
            initializeRecyclerView(instruments, rvInstruments)
            registerForContextMenu(rvInstruments)

        }



    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initializeRecyclerView(
        list: ArrayList<Instrument>,
        recyclerView: RecyclerView
    ) {
        val adapter = RcVwAdapterInstruments(this, list)

        recyclerView.adapter = adapter
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        adapter.notifyDataSetChanged()
    }




    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_instrument_ver -> {
                //val intent = Intent(this, ListCarro::class.java)
                //intent.putExtra("selectedConcesionarioCode", selectedConcesionarioCode)
                //startActivity(intent)
                Toast.makeText(this, "Usuario seleccionado:", Toast.LENGTH_SHORT).show()

                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }



    fun setSelectedInstrumentCode(instrumentCode: Int) {
        selectedInstrumentCode = instrumentCode
    }


}