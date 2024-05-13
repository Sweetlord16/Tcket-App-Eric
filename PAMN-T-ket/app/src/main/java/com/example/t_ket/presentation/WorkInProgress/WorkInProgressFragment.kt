package com.example.t_ket.presentation.WorkInProgress

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.t_ket.R

class WorkInProgressFragment : Fragment() {

    companion object {
        fun newInstance() = WorkInProgressFragment()
    }

    private lateinit var viewModel: WorkInProgressViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_work_in_progress, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(WorkInProgressViewModel::class.java)
        // TODO: Use the ViewModel
    }

}