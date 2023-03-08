package br.com.impacta.curso.myapplication.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Contato(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var nome: String,
    var telefone: String
)
