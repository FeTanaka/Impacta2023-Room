package br.com.impacta.curso.myapplication.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.impacta.curso.myapplication.data.models.Contato
import br.com.impacta.curso.myapplication.databinding.ContatoItemBinding

class ContatoAdaptador(
    private val lista: List<Contato>,
    private val action: (Int) -> Unit
): RecyclerView.Adapter<ContatoAdaptador.ContatoViewHolder>() {

    inner class ContatoViewHolder(val binding: ContatoItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(contato: Contato) {
            binding.contato = contato
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContatoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ContatoItemBinding.inflate(inflater, parent, false)
        return ContatoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContatoViewHolder, position: Int) {
        holder.bind(lista[position])
        holder.binding.root.setOnClickListener{
            action(lista[position].id)
        }
    }

    override fun getItemCount(): Int = lista.size
}