package com.example.t_ket.core.domain.model
//Aqui  va los datos del usuario
data class User(
    var codeOfEvent:String? ="",
    var codeOfStaff: String? ="",
    var name: String? = null,
    var admin: Boolean = false,
    var gmail: String? = null,
    var image: String? = null
)
