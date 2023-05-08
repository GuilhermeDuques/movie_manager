package com.project.at_marvel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.project.at_marvel.adapters.ComentariosAdapter
import com.project.at_marvel.databinding.FragmentListBinding
import com.project.at_marvel.help.FirebaseHelper
import com.project.at_marvel.models.Comentario


class ListFragment : Fragment() {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private val listaComentarios = mutableListOf<com.project.at_marvel.models.Comentario>()
    private lateinit var comentariosAdapter: ComentariosAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClicks()
        getLista()
    }
    private fun initClicks(){
        binding.floatingActionButton.setOnClickListener{
            val bundle = bundleOf("comentarioArgs" to Comentario())
            findNavController().navigate(R.id.insertFragment, bundle)
        }
    }
    private fun getLista(){

        FirebaseHelper
            .getDatabase()
            .child("Comentarios")
            .child(FirebaseHelper.getIdUser() ?: "")

            .addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    listaComentarios.clear()
                    if(snapshot.exists()){

                        for(snap in snapshot.children){
                            val comentario = snap.getValue(com.project.at_marvel.models.Comentario::class.java) as com.project.at_marvel.models.Comentario
                            listaComentarios.add(comentario)

                        }
                    }
                    listaComentarios.reverse()
                    initAdapter()

                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(requireContext(), "Erro", Toast.LENGTH_SHORT).show()
                }

            })
    }
    private fun initAdapter(){
        binding.rvList.layoutManager = LinearLayoutManager(requireContext())
        binding.rvList.setHasFixedSize(true)
        comentariosAdapter = ComentariosAdapter(requireContext(),listaComentarios) { list, select ->
            opcaoSelecionada(list, select )

        }
        binding.rvList.adapter = comentariosAdapter
    }
    private fun opcaoSelecionada(itemComentario: Comentario, select: Int){
        when(select){
            ComentariosAdapter.SELECT_REMOVE ->{
                deleteComentario(itemComentario)
            }
            ComentariosAdapter.SELECT_EDIT -> {

                val action = ListFragmentDirections
                    .actionListFragmentToInsertFragment(itemComentario)
                findNavController().navigate(action)
            }
        }

    }

    private fun deleteComentario(listaComentario: Comentario){
        FirebaseHelper
            .getDatabase()
            .child("Comentarios")
            .child(FirebaseHelper.getIdUser() ?: "").child(listaComentario.id).removeValue()
        getLista()


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}