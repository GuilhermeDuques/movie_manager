package com.project.at_marvel.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.at_marvel.databinding.ListAdapterBinding

class ComentariosAdapter(
    private val context: android.content.Context,
    private val listaComentarios: List<com.project.at_marvel.models.Comentario>,
    val bottomSelected: (com.project.at_marvel.models.Comentario, Int) -> Unit
) : RecyclerView.Adapter<ComentariosAdapter.MyViewHolder>() {

    companion object{
        val SELECT_REMOVE: Int = 1
        val SELECT_EDIT: Int = 2

    }



    lateinit var binding: ListAdapterBinding
    inner class MyViewHolder(val binding : ListAdapterBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ListAdapterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val itemComentario = listaComentarios[position]
        holder.binding.textAvaliacao.text = itemComentario.avaliacao
        holder.binding.textFilme.text = itemComentario.filme
        holder.binding.btnDelete.setOnClickListener{bottomSelected(itemComentario, SELECT_REMOVE)}
        holder.binding.btnEdit.setOnClickListener{bottomSelected(itemComentario, SELECT_EDIT)}


    }

    override fun getItemCount() = listaComentarios.size

}