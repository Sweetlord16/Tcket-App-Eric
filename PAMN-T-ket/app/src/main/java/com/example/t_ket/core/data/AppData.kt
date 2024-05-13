package com.example.t_ket.core.data

import com.example.t_ket.core.domain.model.Event


object AppData {
    var event: String = ""
    var name: String = ""
    var image: String?=""
    var code: String = ""
    var eventInf = Event()
    fun setEventCode(codigo: String) {
        event = codigo
    }
    fun setCustomName(codigo: String) {
        name = codigo
    }

    fun setCustomImage(codigo: String) {
        image = codigo
    }
    fun setEventInfo(event: Event){
        eventInf = event
    }
    fun setCustomCode(codigo: String) {
        code = codigo
    }
}