package com.example.t_ket.core.domain.repository

import com.example.t_ket.core.domain.model.User

interface UserUseCaseRepository {
    suspend fun associateUser(codigo: String): Boolean
    suspend fun isAdmin(codigo: String):Boolean?
    suspend fun getUserInfo() : User
}