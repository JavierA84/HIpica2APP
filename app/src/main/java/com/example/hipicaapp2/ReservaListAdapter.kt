package com.example.hipicaapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hipicaapp.R
import com.example.hipicaapp.data.Reserva

class ReservaListAdapter(private val onItemClicked: (Reserva) -> Unit) :
    RecyclerView.Adapter<ReservaListAdapter.ReservaViewHolder>() {

    private var reservas: List<Reserva> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservaViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return ReservaViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ReservaViewHolder, position: Int) {
        val current = reservas[position]
        holder.nombreJineteItemView.text = current.nombreJinete
        holder.nombreCaballoItemView.text = current.nombreCaballo
        holder.fechaItemView.text = current.fecha.toString()
        holder.horaItemView.text = current.hora
        holder.comentarioItemView.text = current.comentario

        holder.itemView.setOnClickListener {
            onItemClicked(current)
        }
    }

    internal fun setReservas(reservas: List<Reserva>) {
        this.reservas = reservas
        notifyDataSetChanged()
    }

    override fun getItemCount() = reservas.size

    class ReservaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombreJineteItemView: TextView = itemView.findViewById(R.id.text_view_nombre_jinete)
        val nombreCaballoItemView: TextView = itemView.findViewById(R.id.text_view_nombre_caballo)
        val fechaItemView: TextView = itemView.findViewById(R.id.text_view_fecha)
        val horaItemView: TextView = itemView.findViewById(R.id.text_view_hora)
        val comentarioItemView: TextView = itemView.findViewById(R.id.text_view_comentario)
    }
}
