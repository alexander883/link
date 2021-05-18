package com.example.link

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.link.databinding.FragmentRecyclerBinding
import com.example.link.databinding.FragmentResultBinding

class FragmentRecycler : Fragment() {
    private lateinit var binding: FragmentRecyclerBinding
    private var apiviewmodel: HhApiViewModel?=null
    private val adapter = HhAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        apiviewmodel = ViewModelProvider(requireActivity()).get(HhApiViewModel::class.java)
        val fragmentBinding = FragmentRecyclerBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            fragmentRecycler=this@FragmentRecycler
            apiViewModel=apiviewmodel
            foundHh.adapter=adapter
        }
        val h=apiviewmodel?.status?.value
        Log.i("LOG", " $h")
       adapter.data=apiviewmodel?.status?.value!!
        val k=apiviewmodel?.status?.value!!
        Log.i("LOG", " $k")
      //  Toast.makeText(requireContext(), "${k[0]}", Toast.LENGTH_SHORT).show()
    }
}