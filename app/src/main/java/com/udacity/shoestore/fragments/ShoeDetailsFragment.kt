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
import com.udacity.shoestore.databinding.FragmentShoeDetailsBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.viewmodels.ShoeDetailsViewModel
import androidx.fragment.app.activityViewModels
import timber.log.Timber

class ShoeDetailsFragment : Fragment() {

    private lateinit var binding: FragmentShoeDetailsBinding
    private lateinit var viewModel: ShoeDetailsViewModel
    private var shoe: Shoe = Shoe("",0.0,"","")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shoe_details,
            container,
            false
        )
        viewModel = ViewModelProvider(this).get(ShoeDetailsViewModel::class.java)
        binding.shoeDetailsViewModel = viewModel
        binding.shoe = shoe
        binding.setLifecycleOwner(this)

        viewModel.eventSave.observe(viewLifecycleOwner){ hasSaved ->
            if(hasSaved){
                onSave()
                viewModel.onSaveComplete()
            }
        }

        viewModel.eventCancel.observe(viewLifecycleOwner){ hasCancelled ->
            if(hasCancelled){
                onCancel()
                viewModel.onCancelComplete()
            }

        }
        return binding.root
    }

    private fun onCancel() {
        findNavController().navigate(ShoeDetailsFragmentDirections.actionShoeDetailsDestinationToShoeListDestination())
    }

    private fun onSave() {
        binding.apply {
            shoe?.name = shoeName.text.toString()
            shoe?.company = companyName.text.toString()
            shoe?.size = size.text.toString().toDouble()
            shoe?.description = description.text.toString()
        }
        findNavController().navigate(ShoeDetailsFragmentDirections.actionShoeDetailsDestinationToShoeListDestination(binding.shoe))
    }
}