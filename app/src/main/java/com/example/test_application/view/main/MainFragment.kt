package com.example.test_application.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.test_application.databinding.FragmentMainBinding
import com.example.test_application.view.base.BaseFragment
import com.example.test_application.view.main.MainFragmentDirections.Companion.actionMainFragmentToAddFragment
import com.example.test_application.view.main.MainFragmentDirections.Companion.actionMainFragmentToDetailsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainBinding =
        FragmentMainBinding::inflate

    private val viewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            cvCalendar.setOnDateChangeListener { _, year, month, day ->
                viewModel.getItemsByDate(year, month, day)
            }
            btnAdd.setOnClickListener {
                findNavController().navigate(actionMainFragmentToAddFragment())
            }

            viewModel.items.observe(viewLifecycleOwner) {
                taskView.setItems(it) { id ->
                    findNavController().navigate(actionMainFragmentToDetailsFragment(id))
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()

        viewModel.getInitialItems()
    }
}
