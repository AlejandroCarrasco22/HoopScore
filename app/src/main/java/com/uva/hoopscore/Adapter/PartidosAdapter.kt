package com.uva.hoopscore.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uva.hoopscore.Modelo.Result
import com.uva.hoopscore.R


class PartidosAdapter(private val partidosList:List<Result>, private val onClickListener: (Result) -> Unit) : RecyclerView.Adapter<PartidosViewHolder>() {
    // Función que crea el layout donde se van a incluir cada uno de los item Partido
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartidosViewHolder {
        val layoutIntflater = LayoutInflater.from(parent.context)
        return PartidosViewHolder(layoutIntflater.inflate(R.layout.item_partido, parent, false))
    }

    // Función que detecta cuando se hace click sobre cada uno de los item Partido
    override fun onBindViewHolder(holder: PartidosViewHolder, position: Int) {
        val item = partidosList[position]
        holder.render(item, onClickListener)
    }

    // Función que devuelve el tamaño de la lista de todos los item Partido
    override fun getItemCount(): Int = partidosList.size
}
