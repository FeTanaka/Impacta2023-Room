package br.com.impacta.curso.myapplication.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.impacta.curso.myapplication.data.local.database.AppDatabase
import br.com.impacta.curso.myapplication.data.models.Contato
import br.com.impacta.curso.myapplication.data.repositories.ContatoRepo

class MeuViewModel(private val db: AppDatabase): ViewModel() {

    private val contatoRepo: ContatoRepo = ContatoRepo(db)

    private val _listaContatos: MutableLiveData<List<Contato>> = MutableLiveData(listOf())
    val listaContatos: LiveData<List<Contato>> = _listaContatos

    private val _contato: MutableLiveData<Contato?> = MutableLiveData(null)
    val contato: LiveData<Contato?> = _contato

    fun buscarListaContatos() {
        val lista = contatoRepo.buscarTodos()
        _listaContatos.value = lista
    }

    fun buscarPorId(id: Int) {
        val contato = contatoRepo.buscarPorId(id)
        _contato.value = contato
    }

    fun salvar(contato: Contato) {
        contatoRepo.inserir(contato)
        _contato.value = contato
    }

    fun atualizar(contato: Contato) {
        contatoRepo.atualizar(contato)
        _contato.value = contato
    }

    fun deletar(contato: Contato) {
        contatoRepo.deletar(contato)
        _contato.value = null
    }
}

class MeuViewModelFactory(private val db: AppDatabase): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MeuViewModel::class.java)) {
            return MeuViewModel(db) as T
        }
        throw java.lang.IllegalArgumentException()
    }
}