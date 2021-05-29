package com.example.link.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.link.R
import com.example.link.adapter.EmployersAdapter
import com.example.link.databinding.FragmentRecyclerBinding
import com.example.link.viewmodel.SharedViewModel

class FragmentRecycler: Fragment(), EmployersAdapter.OnItemClickListener  {
    private lateinit var binding: FragmentRecyclerBinding
    private val sharedviewmodel: SharedViewModel by activityViewModels()
    private val adapter = EmployersAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentRecyclerBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            sharedViewModel=sharedviewmodel
            fragmentRecycler=this@FragmentRecycler
            foundHh.adapter=adapter
        }
        with(sharedviewmodel)
        {
            reset()
            resetEmpoyer()
            getHhEmployers()
        }
        adapter.data=sharedviewmodel.employers.value!!
        adapter.page=sharedviewmodel.currentPage.value!!
    }
    fun onClickBack(){
        sharedviewmodel.clickBack()
        adapter.page=sharedviewmodel.currentPage.value!!
    }
    fun onClickForward(){
        sharedviewmodel.clickForward()
        adapter.page=sharedviewmodel.currentPage.value!!
    }
////клик по компании из списка
    override fun onItemClick(position: Int) {
        val clickedItem =adapter.data[position]
        sharedviewmodel.getHhSingleEmployer(clickedItem.id)
        findNavController().navigate(R.id.action_fragmentRecycler_to_fragmentInformation)
       // Toast.makeText(requireContext(), "$position", Toast.LENGTH_SHORT).show()
    }
}

