package br.com.impacta.curso.myapplication.data.local.daos

import androidx.room.*
import br.com.impacta.curso.myapplication.data.models.Contato

@Dao
interface ContatoDao {

    @Insert
    fun inserir(contato: Contato)

    @Insert
    fun inserirTodos(lista: List<Contato>)

    @Update
    fun atualizar(contato: Contato)

    @Delete
    fun deletar(contato: Contato)

    @Query("SELECT * FROM contato WHERE id = :uid")
    fun buscarPorId(uid: Int): Contato

    @Query("SELECT * FROM contato")
    fun buscarTodos(): List<Contato>
}