package com.example.link.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.link.R
import com.example.link.databinding.Fragment1Binding
import com.example.link.viewmodel.SharedViewModel

class Fragment1 : Fragment() {
    private lateinit var binding: Fragment1Binding
    private val sharedviewmodel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = Fragment1Binding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            fragment1=this@Fragment1
            sharedViewModel=sharedviewmodel
        }

    }

    fun pressButton2(){
        getData()
        findNavController().navigate(R.id.action_fragment1_to_fragmentRecycler)
    }
    private fun getData(){
        val search=binding.textSearh.text.toString()
        sharedviewmodel.setSearch(search)
      //  Toast.makeText(requireContext(), "$searh", Toast.LENGTH_SHORT).show()
    }

}


