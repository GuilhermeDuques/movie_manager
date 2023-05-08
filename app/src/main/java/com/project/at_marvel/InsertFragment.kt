package com.project.at_marvel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.project.at_marvel.databinding.FragmentInsertBinding
import com.project.at_marvel.help.FirebaseHelper
import com.project.at_marvel.models.Comentario

class InsertFragment : Fragment() {
    private val args: InsertFragmentArgs by navArgs()
    private var _binding: FragmentInsertBinding? = null
    private val binding get() = _binding!!
    private var comentario: Comentario = Comentario()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInsertBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        getArgs()
    }
    private fun getArgs(){
        args.comentarioArgs.let {
            if(it != null){
                comentario = it
                configItem()
            }
        }
    }
    private fun configItem(){

        binding.etFilme.setText(comentario.filme)
        binding.etAvaliacao.setText(comentario.avaliacao)


    }
    private fun initListeners(){
        binding.btnSave.setOnClickListener{
            validacao()
        }
    }
    private fun validacao(){
        val nameFilme = binding.etFilme.text.toString().trim()
        if (nameFilme.isNotEmpty()){

            comentario.filme = nameFilme
            comentario.avaliacao = binding.etAvaliacao.text.toString().trim()
            saveItem()

        } else{
            Toast.makeText(requireContext(),"Preencha os campos", Toast.LENGTH_SHORT).show()
        }
    }
    private fun saveItem(){
        FirebaseHelper.getDatabase()
            .child("Comentarios")
            .child(FirebaseHelper.getIdUser() ?: "")
            .child(comentario.id)
            .setValue(comentario)
            .addOnCompleteListener{
                item -> if(item.isSuccessful){
                            Toast.makeText(requireContext(),"Item Salvo", Toast.LENGTH_SHORT).show()
                            findNavController().navigate(R.id.listFragment)
                        }else{
                            Toast.makeText(requireContext(),"Não salvo", Toast.LENGTH_SHORT).show()
                        }
            }.addOnFailureListener {
                Toast.makeText(requireContext(),"Não salvo", Toast.LENGTH_SHORT).show()
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}