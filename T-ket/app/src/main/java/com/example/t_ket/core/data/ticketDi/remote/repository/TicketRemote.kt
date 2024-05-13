package com.example.t_ket.core.data.ticketDi.remote.repository

import com.example.t_ket.core.domain.model.Ticket

interface TicketRemote {
    suspend fun checkStatusTicket(id_ticket: String) : Boolean
    suspend fun getValidatedTickets(): List<Ticket>
    suspend fun getNotValidatedTickets(): List<Ticket>
    suspend fun getNumberOfValidatedTickets() : Int
    suspend fun getNumberOfNotValidatedTickets() : Int
    suspend fun getGroupTickets(group_id : String): Boolean?
    suspend fun getUserTickets(): List<Ticket>

}