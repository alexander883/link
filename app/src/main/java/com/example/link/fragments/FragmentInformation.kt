package com.example.link.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.link.databinding.FragmentInformationBinding
import com.example.link.viewmodel.HhApiViewModel


class FragmentInformation : Fragment() {
    private lateinit var binding: FragmentInformationBinding
    private val apiviewmodel: HhApiViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentInformationBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            fragmentInformation=this@FragmentInformation
            apiViewModel=apiviewmodel
            }

        }
    }