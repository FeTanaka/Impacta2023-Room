package br.com.impacta.curso.myapplication.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import br.com.impacta.curso.myapplication.R
import br.com.impacta.curso.myapplication.data.local.database.AppDatabase
import br.com.impacta.curso.myapplication.data.models.Contato
import br.com.impacta.curso.myapplication.databinding.FragmentAddEditRemoveBinding
import br.com.impacta.curso.myapplication.ui.viewmodels.MeuViewModel
import br.com.impacta.curso.myapplication.ui.viewmodels.MeuViewModelFactory

class AddEditRemoveFragment : Fragment() {

    private var _binding: FragmentAddEditRemoveBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MeuViewModel by activityViewModels {
        MeuViewModelFactory(AppDatabase.getInstance(this.requireActivity()))
    }
    private val args: AddEditRemoveFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddEditRemoveBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (args.uid != -1) {
            viewModel.buscarPorId(args.uid)
            viewModel.contato.observe(viewLifecycleOwner) { c: Contato? ->
                c?.let{ contato: Contato ->
                    binding.nameEditText.setText(contato.nome)
                    binding.telephoneEditText.setText(contato.telefone)
                    binding.deleteButton.visibility = View.VISIBLE
                    binding.saveButton.text = "Salvar edição"

                    binding.saveButton.setOnClickListener {
                        contato.nome = binding.nameEditText.text.toString()
                        contato.telefone = binding.telephoneEditText.text.toString()
                        viewModel.atualizar(contato)
                    }

                    binding.deleteButton.setOnClickListener {
                        viewModel.deletar(contato)
                    }
                }
            }
        } else {
            binding.deleteButton.visibility = View.GONE
            binding.saveButton.text = "Salvar"

            binding.saveButton.setOnClickListener{
                val contato = Contato(nome = binding.nameEditText.text.toString(), telefone = binding.telephoneEditText.text.toString())
                viewModel.salvar(contato)
            }
        }
    }
}