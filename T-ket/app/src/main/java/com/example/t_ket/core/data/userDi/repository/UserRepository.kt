package com.example.t_ket.core.data.userDi.repository

import com.example.t_ket.core.domain.model.User

interface UserRepository {
    suspend fun checkIsStaff(id_event:String, staffCode:String): Boolean

    fun setUser(id_event: String, staffCode:String)

    fun getIdEvent(): String

    suspend fun isAdmin(id_event:String, staffCode:String): Boolean?

    suspend fun  getUserInfo() : User
}