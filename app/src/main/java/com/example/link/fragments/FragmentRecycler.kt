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

import com.example.link.R
import com.example.link.adapter.HhAdapter
import com.example.link.databinding.FragmentRecyclerBinding
import com.example.link.viewmodel.HhApiViewModel

class FragmentRecycler : Fragment(), HhAdapter.OnItemClickListener  {
    private lateinit var binding: FragmentRecyclerBinding
    private val apiviewmodel: HhApiViewModel by activityViewModels()
    private val adapter = HhAdapter(this)

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

       adapter.data=apiviewmodel.status?.value!!

      //  Toast.makeText(requireContext(), "${k[0]}", Toast.LENGTH_SHORT).show()
    }
    override fun onItemClick(position: Int) {
        val clickedItem =adapter.data[position]
        Toast.makeText(requireContext(), "$position", Toast.LENGTH_SHORT).show()
    }

    fun onClickBack(){

    }
    fun onClickForward(){
        apiviewmodel.clickForward()
        findNavController().navigate(R.id.action_fragmentRecycler_self)
        //Toast.makeText(requireContext(), "hrhdh", Toast.LENGTH_SHORT).show()
    }
}