package com.example.t_ket.presentation.UserTicketList

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.t_ket.R
import com.example.t_ket.core.domain.model.Ticket
import com.example.t_ket.core.domain.usecase.TicketInteractorImpl
import com.example.t_ket.databinding.FragmentTicketListBinding
import com.example.t_ket.databinding.FragmentUserTicketListBinding
import com.example.t_ket.presentation.TicketList.adapter.TicketListAdapter
import com.example.t_ket.presentation.UserTicketList.UserTicketListViewModel
import com.example.t_ket.presentation.UserTicketList.adapter.UserTicketListAdapter
import com.example.t_ket.presentation.components.dialogs.ConfirmationDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.properties.Delegates

@AndroidEntryPoint
class UserTicketListFragment : Fragment() {
    private var _binding: FragmentUserTicketListBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: UserTicketListAdapter
    @Inject
    lateinit var ticketInteractor: TicketInteractorImpl
    private val UserTicketListViewModel by viewModels<UserTicketListViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserTicketListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
        initList()
        initSwpipeConfig()
    }

    private fun initSwpipeConfig() {
        binding.swipe.setOnRefreshListener {
            initObservers()
            binding.swipe.isRefreshing = false
        }
    }

    private fun initList() {
        var groupBoolean by Delegates.notNull<Boolean>();
        adapter = UserTicketListAdapter(onGroupSelected = {
            groupBoolean = it
        }, onItemSelected = {
            lifecycleScope.launch {
                if(!groupBoolean) {
                    it.id?.let { it1 ->
                        Toast.makeText(requireContext(),  it1 , Toast.LENGTH_LONG).show()
                        if (ticketInteractor.checkTicket("{\"id\":\"$it1\"}") == true) {
                            ConfirmationDialog(
                                onSubmitClickListener = { position ->
                                    Toast.makeText(requireContext(), ": $position", Toast.LENGTH_SHORT).show()
                                }
                            ).show(parentFragmentManager, "dialog")
                        }
                    }
                }else{
                    it.idGroup?.let { it1 ->
                        Toast.makeText(requireContext(),  it1 , Toast.LENGTH_LONG).show()
                        if (ticketInteractor.getGroupTickets(it1) == true) {
                            ConfirmationDialog(
                                onSubmitClickListener = { position ->
                                    Toast.makeText(requireContext(), ": $position", Toast.LENGTH_SHORT).show()
                                }
                            ).show(parentFragmentManager, "dialog")
                        }
                    }
                }
            }
        })
        binding.recyclerView1.layoutManager = LinearLayoutManager(context)
        binding.recyclerView1.adapter = adapter
    }

    private fun initObservers() {
        UserTicketListViewModel.getList().observe(viewLifecycleOwner) { state ->
            when(state) {
                is List<Ticket> -> {
                    with(binding){
                        adapter.updateList(state)
                        binding.etFilter.addTextChangedListener { userFilter ->
                            val listaFiltrada =
                                state.filter { ticket ->
                                    ticket.fullName?.lowercase()!!.contains(userFilter.toString().lowercase())
                                }
                            adapter.updateTickets(listaFiltrada)
                        }
                    }
                }
                null -> {
                    with(binding){
                        Log.d("TAG", "Error Info")
                    }
                }
            }
        }
    }



}