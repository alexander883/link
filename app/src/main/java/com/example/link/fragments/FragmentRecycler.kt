package com.example.link.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.link.adapter.HhAdapter
import com.example.link.viewmodel.HhApiViewModel
import com.example.link.databinding.FragmentRecyclerBinding

class FragmentRecycler : Fragment() {
    private lateinit var binding: FragmentRecyclerBinding
    private val apiviewmodel: HhApiViewModel by activityViewModels()
    private val adapter = HhAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View { Log.i("LOG", " onCreateView")
      //  apiviewmodel = ViewModelProvider(requireActivity()).get(HhApiViewModel::class.java)
        val fragmentBinding = FragmentRecyclerBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("LOG", "onViewCreated")
        binding.apply {
            lifecycleOwner = viewLifecycleOwner

            apiViewModel=apiviewmodel
            fragmentRecycler=this@FragmentRecycler

            foundHh.adapter=adapter

        }

        Log.i("LOG", "BINding end")


       adapter.data=apiviewmodel?.status?.value!!

      //  Toast.makeText(requireContext(), "${k[0]}", Toast.LENGTH_SHORT).show()
    }
}