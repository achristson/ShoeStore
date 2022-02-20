package com.udacity.shoestore.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.udacity.shoestore.R
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentInstructionsBinding
import com.udacity.shoestore.viewmodels.InstructionsViewModel

class InstructionsFragment : Fragment() {

    private lateinit var binding: FragmentInstructionsBinding
    private lateinit var viewModel: InstructionsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_instructions,
            container,
            false
        )
        viewModel = ViewModelProvider(this).get(InstructionsViewModel::class.java)
        binding.instructionsViewModel = viewModel
        binding.setLifecycleOwner(this)

        viewModel.eventNavigateNext.observe(viewLifecycleOwner) { hasNavigatedNext ->
            if(hasNavigatedNext){
                navigateNext()
                viewModel.onNavigateNextComplete()
            }
        }
        return binding.root
    }

    private fun navigateNext() {
        findNavController().navigate(InstructionsFragmentDirections.actionInstructionsDestinationToShoeListDestination())
    }
}