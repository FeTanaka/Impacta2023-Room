package br.com.impacta.curso.myapplication.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.impacta.curso.myapplication.R
import br.com.impacta.curso.myapplication.data.local.database.AppDatabase

class MainActivity : AppCompatActivity() {

    val db: AppDatabase by lazy {
        AppDatabase.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}