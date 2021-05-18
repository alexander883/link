package com.example.link.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.link.HhApiViewModel
import com.example.link.R
import com.example.link.databinding.Fragment1Binding

class Fragment1 : Fragment() {
    private lateinit var binding: Fragment1Binding
    private val apiviewmodel: HhApiViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       // apiviewmodel = ViewModelProvider(requireActivity()).get(HhApiViewModel::class.java)
        val fragmentBinding = Fragment1Binding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            fragment1=this@Fragment1
            apiViewModel=apiviewmodel
        }

    }

    fun pressButton1(){
        getData()
        findNavController().navigate(R.id.action_fragment1_to_fragmentResult)
    }

    fun pressButton2(){
        getData()
        Log.i("LOG", " Переход 2")
        findNavController().navigate(R.id.action_fragment1_to_fragmentRecycler)
    }
    private fun getData(){
        val searh=binding.textSearh.text.toString()
        apiviewmodel.setSearch(searh)
        Toast.makeText(requireContext(), "$searh", Toast.LENGTH_SHORT).show()
        apiviewmodel?.getHh()
    }
}