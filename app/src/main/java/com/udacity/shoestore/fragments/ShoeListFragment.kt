package com.udacity.shoestore.fragments

import android.os.Bundle
import android.provider.ContactsContract
import android.view.*
import androidx.fragment.app.Fragment
import com.udacity.shoestore.R
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ShoeListItemBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.viewmodels.ShoeDetailsViewModel
import com.udacity.shoestore.viewmodels.ShoeListViewModel

import timber.log.Timber

class ShoeListFragment : Fragment() {

    private lateinit var binding: FragmentShoeListBinding
    private val viewModel: ShoeListViewModel by activityViewModels()
    private lateinit var shoeListItemBinding: ShoeListItemBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shoe_list,
            container,
            false
        )

        val shoeListFragmentArgs by navArgs<ShoeListFragmentArgs>()
        //viewModel = ViewModelProvider(requireActivity()).get(ShoeListViewModel::class.java)

        binding.shoeListViewModel = viewModel
        if (shoeListFragmentArgs.shoe != null){
            viewModel.addShoe(shoeListFragmentArgs.shoe)
        }

        binding.setLifecycleOwner(this)

        viewModel.shoeList.observe(viewLifecycleOwner){ shoes ->
            if (shoes.isNotEmpty()){
                for (shoe in shoes){
                    if (shoe != null){
                        displayShoe(shoe)
                    }
                }
            }
        }

        viewModel.eventAddShoe.observe(viewLifecycleOwner){ addShoe ->
            if(addShoe){
                onAddShoe()
                viewModel.onAddShoeClickComplete()
            }
        }
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.navdrawer_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        findNavController().navigate(R.id.login_destination)
        return true
    }

    private fun onAddShoe() {
        findNavController().navigate(ShoeListFragmentDirections.actionShoeListDestinationToShoeDetailsFragment())
    }

    private fun displayShoe(shoe: Shoe){

        shoeListItemBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.shoe_list_item,
            null,
            false
        )

        shoeListItemBinding.shoeImage.setImageResource(R.drawable.vans)
        shoeListItemBinding.nameText.text = getString(R.string.shoe_name_display, shoe.name)
        shoeListItemBinding.companyText.text = getString(R.string.company_name_display, shoe.company)
        shoeListItemBinding.sizeText.text = getString(R.string.size_display, shoe.size.toString())
        shoeListItemBinding.descriptionText.text = getString(R.string.description_display, shoe.description)

        binding.linearLayout.addView(shoeListItemBinding.root)
    }
}
