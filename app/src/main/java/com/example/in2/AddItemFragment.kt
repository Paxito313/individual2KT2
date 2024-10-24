package com.example.in2.ui.additem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.in2.MyApplication
import com.example.in2.R
import com.example.in2.data.Item
import com.example.in2.databinding.FragmentAddItemBinding
import com.example.in2.viewmodel.ItemViewModel
import com.example.in2.viewmodel.ItemViewModelFactory

class AddItemFragment : Fragment() {

    private var _binding: FragmentAddItemBinding? = null
    private val binding get() = _binding!!

    private val itemViewModel: ItemViewModel by viewModels {
        ItemViewModelFactory((activity?.application as MyApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonAddItem.setOnClickListener {
            val name = binding.editTextItemName.text.toString()
            val price = binding.editTextItemPrice.text.toString().toDoubleOrNull() ?: 0.0
            val quantity = binding.editTextItemQuantity.text.toString().toIntOrNull() ?: 0
            if (name.isNotEmpty() && price > 0 && quantity > 0) {
                val item = Item(name = name, price = price, quantity = quantity)
                itemViewModel.insert(item)
                Toast.makeText(context, "Item agregado exitosamente", Toast.LENGTH_SHORT).show()
            }
        }

        binding.buttonShowItems.setOnClickListener {
            findNavController().navigate(R.id.action_addItemFragment_to_showItemsFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
