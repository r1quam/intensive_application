package com.example.test_application.view.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.test_application.databinding.FragmentDetailsBinding
import com.example.test_application.utils.getDateFormatted
import com.example.test_application.utils.getTimeFormatted
import com.example.test_application.view.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDetailsBinding =
        FragmentDetailsBinding::inflate

    private val args: DetailsFragmentArgs by navArgs()

    private val viewModel: DetailsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            toolbar.setNavigationOnClickListener { findNavController().popBackStack() }

            viewModel.task.observe(viewLifecycleOwner) {
                etName.setText(it.name)
                etDate.setText(getDateFormatted(it.dateStart))
                etTimeStart.setText(getTimeFormatted(it.dateStart))
                etTimeFinish.setText(getTimeFormatted(it.dateFinish))
                etDescription.setText(it.description)
            }
        }

        viewModel.getTaskById(args.id)
    }
}
