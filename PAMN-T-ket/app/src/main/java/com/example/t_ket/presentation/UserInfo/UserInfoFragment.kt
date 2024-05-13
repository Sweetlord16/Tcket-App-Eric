package com.example.t_ket.presentation.UserInfo

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.t_ket.R
import com.example.t_ket.core.domain.model.Event
import com.example.t_ket.core.domain.model.User
import com.example.t_ket.databinding.FragmentEventInfoBinding
import com.example.t_ket.databinding.FragmentUserInfoBinding
import com.example.t_ket.presentation.EventInfo.EventInfoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserInfoFragment : Fragment() {

    private var _binding: FragmentUserInfoBinding? = null
    private val binding get() = _binding!!


    private val UserInfoViewModel by viewModels<UserInfoViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        UserInfoViewModel.getInfo()
        _binding = FragmentUserInfoBinding.inflate(layoutInflater, container, false)

        return binding.root    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()

    }
    override fun onDestroyView() {
        super.onDestroyView()
        // Limpia la memoria caché de Glide y anula la carga de imágenes
        Glide.with(requireContext()).clear(binding.imageUser)
    }

    private fun initObservers() {
        UserInfoViewModel.userInfo.observe(viewLifecycleOwner) { state ->
            when(state) {
                is User -> {
                    with(binding){
                        nombreUser.text="${state.name}"
                        FirstName.text = "FirstName: ${state.name?.split(" ")?.firstOrNull()}"
                        surname.text = "Surname: ${state.name?.split(" ")?.drop(1)?.joinToString(" ")}"

                        gmail.text="Gmail: ${state.gmail}"
                        codeId.text="User Code ID: ${state.codeOfEvent + state.codeOfStaff}"

                        android.util.Log.d("TAG", "He pasado por aqui")

                        android.util.Log.d("TAG", "Imagen: ${state.image}")
                        com.bumptech.glide.Glide.with(requireContext())
                            .load(state.image)
                            .diskCacheStrategy(com.bumptech.glide.load.engine.DiskCacheStrategy.ALL)
                            .into(imageUser)
                    }
                }
                null -> {
                    with(binding){
                        android.util.Log.d("TAG", "Error Info")
                    }
                }
            }
        }
    }

}