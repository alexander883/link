package com.example.link

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.link.databinding.Fragment1Binding
import com.example.link.databinding.FragmentResultBinding


class FragmentResult : Fragment() {
    private lateinit var binding: FragmentResultBinding
    private var apiviewmodel: HhApiViewModel?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        apiviewmodel = ViewModelProvider(requireActivity()).get(HhApiViewModel::class.java)
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