package com.example.listadaptertest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.listadaptertest.databinding.FragmentHomeBinding


class FragmentHome : Fragment() {

    private lateinit var viewModel: HomeViewModel

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val adapter = SimpleItemAdapter { item ->
        viewModel.onItemClick(item.itemId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        binding.homeRecyclerview.adapter = adapter

        viewModel.items.observe(viewLifecycleOwner) {
            /**
             * Originally, one of the most accepted answers out there was to perform
             * addItemCount(it.toMutableList()) but it didn't quite work. Before
             * that, DiffUtils was not even being called, yes, but with the toMutableList()
             * call, DiffUtils was called but during the evaluation you could see that
             * the newItem and oldItem would always be the same! After making SimpleItem's
             * itemClickCount immutable, I reverted to the original submitList(it).
             */
            adapter.submitList(it)
        }
    }
}