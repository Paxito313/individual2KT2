package com.example.in2.ui.showitems

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.in2.MyApplication
import com.example.in2.data.Item
import com.example.in2.databinding.FragmentShowItemsBinding
import com.example.in2.viewmodel.ItemViewModel
import com.example.in2.viewmodel.ItemViewModelFactory

class ShowItemsFragment : Fragment() {

    private var _binding: FragmentShowItemsBinding? = null
    private val binding get() = _binding!!
    private val selectedItems = mutableListOf<Item>()

    private val itemViewModel: ItemViewModel by viewModels {
        val application = requireActivity().application as MyApplication
        ItemViewModelFactory(application.repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShowItemsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ItemAdapter { item, isChecked ->
            if (isChecked) {
                selectedItems.add(item)
            } else {
                selectedItems.remove(item)
            }
        }
        binding.recyclerViewItems.adapter = adapter
        binding.recyclerViewItems.layoutManager = LinearLayoutManager(context)

        itemViewModel.allItems.observe(viewLifecycleOwner) { items ->
            items?.let { adapter.submitList(it) }
        }

        binding.buttonDeleteSelected.setOnClickListener {
            if (selectedItems.isNotEmpty()) {
                selectedItems.forEach { itemViewModel.delete(it) }
                selectedItems.clear()
                Toast.makeText(context, "Items seleccionados borrados", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Selecciona al menos un item para borrar", Toast.LENGTH_SHORT).show()
            }
        }

        binding.buttonDeleteAll.setOnClickListener {
            itemViewModel.deleteAll()
            Toast.makeText(context, "Todos los items borrados", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
