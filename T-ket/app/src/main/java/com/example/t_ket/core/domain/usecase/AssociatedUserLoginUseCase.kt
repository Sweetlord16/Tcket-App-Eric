package com.example.t_ket.core.domain.usecase

import android.util.Log
import  com.example.t_ket.core.data.userDi.repository.UserRepository
import  com.example.t_ket.core.data.userDi.implementation.UserRepositoryImpl
import com.example.t_ket.core.domain.model.User

import com.example.t_ket.core.domain.repository.UserUseCaseRepository
import javax.inject.Inject

class AssociatedUserLoginUseCase @Inject constructor() : UserUseCaseRepository {
     private val userRepository : UserRepository = UserRepositoryImpl()
     override suspend fun associateUser(codigo: String): Boolean{
         if(codigo == ""){
             return false
         }
         return userRepository.checkIsStaff(codigo.substring(0,3),codigo.substring(4,7))
    }

    override suspend fun isAdmin(codigo: String): Boolean? {
        return userRepository.isAdmin(codigo.substring(0,3),codigo.substring(4,7))  }

    override suspend fun getUserInfo(): User {
        val user = userRepository.getUserInfo()
        Log.d("KKKKKKKKKK", "$user")
        return user
    }
}