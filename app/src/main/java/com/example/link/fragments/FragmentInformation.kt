package com.example.link.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.link.databinding.FragmentInformationBinding
import com.example.link.viewmodel.SharedViewModel


class FragmentInformation : Fragment() {
    private lateinit var binding: FragmentInformationBinding
    private val sharedviewmodel: SharedViewModel by activityViewModels()
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
            sharedViewModel=sharedviewmodel
        }
    }
    fun onClickButtonUrl(){
        with(sharedviewmodel.siteEmpoyer.value){
            Toast.makeText(requireContext(), sharedviewmodel.siteEmpoyer.value, Toast.LENGTH_SHORT).show()
            if (this!=="") {
                val queryUrl: Uri = Uri.parse(sharedviewmodel.siteEmpoyer.value)
                val intent = Intent(Intent.ACTION_VIEW, queryUrl)
                context?.startActivity(intent)
            }
        }
    }
}

