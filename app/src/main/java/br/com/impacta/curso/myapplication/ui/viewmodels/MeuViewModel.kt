package br.com.impacta.curso.myapplication.ui.viewmodels

import androidx.lifecycle.*
import br.com.impacta.curso.myapplication.data.local.database.AppDatabase
import br.com.impacta.curso.myapplication.data.models.Contato
import br.com.impacta.curso.myapplication.data.repositories.ContatoRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MeuViewModel(private val db: AppDatabase): ViewModel() {

    private val contatoRepo: ContatoRepo = ContatoRepo(db)

    private val _listaContatos: MutableLiveData<List<Contato>> = MutableLiveData(listOf())
    val listaContatos: LiveData<List<Contato>> = _listaContatos

    private val _contato: MutableLiveData<Contato?> = MutableLiveData(null)
    val contato: LiveData<Contato?> = _contato

    fun buscarListaContatos() {
        viewModelScope.launch(Dispatchers.IO) {
            val lista = contatoRepo.buscarTodos()
            _listaContatos.postValue(lista)
        }
    }

    fun buscarPorId(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val contato = contatoRepo.buscarPorId(id)
            _contato.postValue(contato)
        }
    }

    fun salvar(contato: Contato) {
        viewModelScope.launch(Dispatchers.IO) {
            contatoRepo.inserir(contato)
            _contato.postValue(contato)
        }
    }

    fun atualizar(contato: Contato) {
        viewModelScope.launch(Dispatchers.IO) {
            contatoRepo.atualizar(contato)
            _contato.postValue(contato)
        }
    }

    fun deletar(contato: Contato) {
        viewModelScope.launch(Dispatchers.IO) {
            contatoRepo.deletar(contato)
            _contato.postValue(null)
        }
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