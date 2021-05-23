package com.example.link.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.link.databinding.FragmentResultBinding
import com.example.link.viewmodel.SharedViewModel


class FragmentResult : Fragment() {
    private lateinit var binding: FragmentResultBinding
    private val apiviewmodel: SharedViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       // apiviewmodel = ViewModelProvider(requireActivity()).get(HhApiViewModel::class.java)
        val fragmentBinding = FragmentResultBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            fragmentResult=this@FragmentResult
            apiViewModel=apiviewmodel
        }

    }
}