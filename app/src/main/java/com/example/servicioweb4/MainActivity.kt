package com.example.servicioweb4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.*
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val policy =
            StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        btnLogear.setOnClickListener(){
            val usuario = txtUsuario.text.toString()
            val clave = txtClave.text.toString()
            val queue = Volley.newRequestQueue(this)
            var url = getString(R.string.urlAPI) + "/usuarios?"
            url = url + "usuario=" + usuario + "&clave=" + clave
            val stringRequest = JsonArrayRequest(url,
                Response.Listener { response ->
                    try {
                        val valor = response.getJSONObject(0)
                        /*
                        Toast.makeText(
                            applicationContext,
                            "Validación de usuario: "+valor.getString("usuario")+
                                    " con clave: "+valor.getString("clave")+" correcta",
                            Toast.LENGTH_LONG
                        ).show() */
                        val llamaractividad = Intent(applicationContext, ListadoPeliculas::class.java)
                        startActivity(llamaractividad)
                        finish()

                    }catch (e: JSONException){
                        Toast.makeText(applicationContext,
                            "Error en las credenciales proporcionadas",
                            Toast.LENGTH_LONG).show()
                    }
                },Response.ErrorListener {
                    Toast.makeText(applicationContext,
                        "Compruebe que tiene accesoa a internet",
                        Toast.LENGTH_LONG).show()
                }
            )
            queue.add(stringRequest)



        }
    }

}