package com.example.t_ket.presentation.UserTicketList.adapter


import android.util.Log
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.t_ket.core.data.AppData
import com.example.t_ket.core.domain.model.Ticket
import com.example.t_ket.databinding.ItemTicket2Binding
import com.example.t_ket.databinding.ItemTicketBinding
import kotlinx.coroutines.launch

class UserTicketListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemTicket2Binding.bind(view)

    fun render(ticket: Ticket, onItemSelected: (Ticket) -> Unit,  onGroupSelected:(Boolean) -> Unit) {

        binding.itemTitle.text = "User: ${ticket.fullName}"

        if (AppData.event.equals("UOT")){

            binding.itemDni.text = "Event: University on Tour"

            Log.d("TICKE", "chapa")
        }
        if (ticket.idGroup.equals("69")){

            binding.itemDni.text = "Event: Mucha Party Fiesta"

            Log.d("TICKE", "chipi")
        }
        binding.itemGid.text = "Group ID: ${ticket.idGroup}"


        Log.d("TICKE", "${ticket.image}")
        Log.d("APDATA", "${AppData.image}")

        Glide.with(binding.partyView.context)
            .load(ticket.image)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(binding.partyView)
    }
}