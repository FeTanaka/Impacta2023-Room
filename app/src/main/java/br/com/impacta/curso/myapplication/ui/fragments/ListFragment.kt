package br.com.impacta.curso.myapplication.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.impacta.curso.myapplication.R
import br.com.impacta.curso.myapplication.data.local.database.AppDatabase
import br.com.impacta.curso.myapplication.data.models.Contato
import br.com.impacta.curso.myapplication.databinding.FragmentListBinding
import br.com.impacta.curso.myapplication.ui.activities.MainActivity
import br.com.impacta.curso.myapplication.ui.adapters.ContatoAdaptador
import br.com.impacta.curso.myapplication.ui.viewmodels.MeuViewModel
import br.com.impacta.curso.myapplication.ui.viewmodels.MeuViewModelFactory

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MeuViewModel by activityViewModels {
        MeuViewModelFactory(AppDatabase.getInstance(this.requireActivity()))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.listaContatos.observe(viewLifecycleOwner) { lista ->
            binding.recyclerView.adapter = ContatoAdaptador(lista)
            binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        }

        binding.floatingActionButton.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToAddEditRemoveFragment(-1)
            it.findNavController().navigate(action)
        }

        viewModel.buscarListaContatos()
    }
}