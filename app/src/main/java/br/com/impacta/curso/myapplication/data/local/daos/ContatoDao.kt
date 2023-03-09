package br.com.impacta.curso.myapplication.data.local.daos

import androidx.room.*
import br.com.impacta.curso.myapplication.data.models.Contato

@Dao
interface ContatoDao {

    @Insert
    suspend fun inserir(contato: Contato)

    @Insert
    suspend fun inserirTodos(lista: List<Contato>)

    @Update
    suspend fun atualizar(contato: Contato)

    @Delete
    suspend fun deletar(contato: Contato)

    @Query("SELECT * FROM contato WHERE id = :uid")
    suspend fun buscarPorId(uid: Int): Contato

    @Query("SELECT * FROM contato")
    suspend fun buscarTodos(): List<Contato>
}