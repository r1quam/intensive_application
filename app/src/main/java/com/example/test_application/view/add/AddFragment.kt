package com.example.test_application.view.add

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.test_application.R
import com.example.test_application.databinding.FragmentAddBinding
import com.example.test_application.domain.entity.Priority
import com.example.test_application.utils.AlarmUtils
import com.example.test_application.utils.day
import com.example.test_application.utils.getDateFormatted
import com.example.test_application.utils.getTimeFormatted
import com.example.test_application.utils.hour
import com.example.test_application.utils.minute
import com.example.test_application.utils.month
import com.example.test_application.utils.year
import com.example.test_application.view.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddFragment : BaseFragment<FragmentAddBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAddBinding =
        FragmentAddBinding::inflate

    private val viewModel: AddViewModel by viewModels()

    private val alarmUtils by lazy { AlarmUtils(requireContext()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            toolbar.setNavigationOnClickListener { findNavController().popBackStack() }
            etDate.setOnClickListener { showDatePickerDialog() }
            etTimeStart.setOnClickListener { showTimePickerDialog(true) }
            etTimeFinish.setOnClickListener { showTimePickerDialog(false) }
            btnAdd.setOnClickListener {
                val priority = when {
                    chip2.isChecked -> Priority.LOW
                    chip3.isChecked -> Priority.NORMAL
                    else -> Priority.HIGH
                }
                viewModel.addTask(etName.text.toString(), etDescription.text.toString(), priority)

            }

        }

        setDate()

        viewModel.canFinish.observe(viewLifecycleOwner) {
            it?.let {
                alarmUtils.setAlarm(it)
                findNavController().popBackStack()
            } ?: showErrorToast()
        }
    }

    private fun showDatePickerDialog() {
        DatePickerDialog(
            requireContext(),
            { _, year, month, day ->
                viewModel.setTime(year, month, day)
                setDate()
                showTimePickerDialog(true)
            },
            viewModel.timeStart.year,
            viewModel.timeStart.month,
            viewModel.timeStart.day,
        ).show()
    }

    private fun showTimePickerDialog(start: Boolean) {
        TimePickerDialog(
            requireContext(),
            { _, hours, minutes ->
                if (start) showTimePickerDialog(false)
                viewModel.setTime(hours, minutes, start)
                setDate()
            },
            if (start) viewModel.timeStart.hour else viewModel.timeFinish.hour,
            if (start) viewModel.timeStart.minute else viewModel.timeFinish.minute,
            true,
        ).show()
    }


    private fun setDate() {
        with(viewModel) {
            binding.etDate.setText(getDateFormatted(timeStart))
            binding.etTimeStart.setText(getTimeFormatted(timeStart))
            binding.etTimeFinish.setText(getTimeFormatted(timeFinish))
            binding.tvError.isVisible = timeStart > timeFinish
        }
    }

    private fun showErrorToast() {
        Toast.makeText(requireContext(), R.string.wrong_time_interval, Toast.LENGTH_SHORT).show()
    }
}
