package com.example.t_ket.core.data.userDi.remote.repository

import com.example.t_ket.core.domain.model.User

interface UserRemote {
    suspend fun isStaff(EventId: String, StaffCode: String): Boolean
    suspend fun isAdmin(EventId: String, StaffCode: String): Boolean?
    suspend fun getUserInfo() : User

}