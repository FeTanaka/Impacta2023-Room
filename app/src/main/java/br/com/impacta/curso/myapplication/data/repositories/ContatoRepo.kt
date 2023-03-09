package br.com.impacta.curso.myapplication.data.repositories

import br.com.impacta.curso.myapplication.data.local.database.AppDatabase
import br.com.impacta.curso.myapplication.data.models.Contato

class ContatoRepo(private val db: AppDatabase) {

    private val dao = db.contatoDao()

    suspend fun inserir(contato: Contato) {
        dao.inserir(contato)
    }

    suspend fun atualizar(contato: Contato) {
        dao.atualizar(contato)
    }

    suspend fun deletar(contato: Contato) {
        dao.deletar(contato)
    }

    suspend fun buscarPorId(id: Int): Contato {
        return dao.buscarPorId(id)
    }

    suspend fun buscarTodos(): List<Contato> {
        return dao.buscarTodos()
    }

}