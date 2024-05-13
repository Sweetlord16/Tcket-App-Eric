package com.example.t_ket.presentation.UserInfo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.t_ket.core.data.AppData
import com.example.t_ket.core.domain.model.Event
import com.example.t_ket.core.domain.model.User
import com.example.t_ket.core.domain.repository.EventUseCaseRepository
import com.example.t_ket.core.domain.repository.TicketUseCaseRepository
import com.example.t_ket.core.domain.repository.UserUseCaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserInfoViewModel @Inject constructor(
    private val UserGet: UserUseCaseRepository
) : ViewModel() {
    private val _userInfo: MutableLiveData<User?> = MutableLiveData()
    val userInfo: LiveData<User?>
        get() = _userInfo


    fun getInfo() {
        viewModelScope.launch {
            var info = UserGet.getUserInfo()
            info.name?.let { AppData.setCustomName(it) }
            _userInfo.value = info
            Log.d("TAG" ,"Result: $info")
            Log.d("TAG" ,"Comms")
        }
    }
}
