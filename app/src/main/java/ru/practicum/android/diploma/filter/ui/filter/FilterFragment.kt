package ru.practicum.android.diploma.filter.ui.filter

import android.os.Build
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentFilterBinding
import ru.practicum.android.diploma.filter.domain.models.Filter
import ru.practicum.android.diploma.search.ui.SearchFragment

class FilterFragment : Fragment() {

    private var _binding: FragmentFilterBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FilterViewModel by viewModel()

    private val callback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            findNavController().popBackStack(R.id.searchFragment, false)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilterBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFilterFromBundle()
        initializeListeners()
        initializeObservers()

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setFilterFromBundle() {
        val filter = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requireArguments().getSerializable(FILTER_EXTRA, Filter::class.java) as Filter
        } else {
            requireArguments().getSerializable(FILTER_EXTRA) as Filter
        }
        viewModel.setFilter(filter)
        with(binding) {
            val country = filter.country?.name
            val region = filter.region?.name
            val sector = filter.sector?.name
            if (country != null && region != null) {
                etLocationName.setText(requireContext().getString(R.string.filter_location, country, region))
                binding.tilLocationLabel.defaultHintTextColor =
                    ContextCompat.getColorStateList(requireContext(), getOnPrimaryColor())
            } else if (country != null) {
                etLocationName.setText(country)
                binding.tilLocationLabel.defaultHintTextColor =
                    ContextCompat.getColorStateList(requireContext(), getOnPrimaryColor())
            } else if (region != null) {
                etLocationName.setText(region)
                binding.tilLocationLabel.defaultHintTextColor =
                    ContextCompat.getColorStateList(requireContext(), getOnPrimaryColor())
            }

            if (sector != null) {
                binding.tilSectorLabel.defaultHintTextColor =
                    ContextCompat.getColorStateList(requireContext(), getOnPrimaryColor())
                binding.tilSectorLabel.isEnabled = true
                etSectorName.setText(sector)
            }

            if (filter.salary != null) {
                etSalary.setText(filter.salary.toString())
            }

            checkBox.isChecked = filter.onlyWithSalary
        }
    }

    private fun initializeListeners() {
        binding.etLocationName.setOnClickListener {
            findNavController().navigate(
                resId = R.id.action_filterFragment_to_locationFragment,
                args = createArguments(viewModel.newFilter)
            )
        }
        binding.etSectorName.setOnClickListener {
            findNavController().navigate(
                resId = R.id.action_filterFragment_to_sectorFragment,
                args = createArguments(viewModel.newFilter)
            )
        }
        binding.tbSettingsFilter.setNavigationOnClickListener {
            findNavController().popBackStack(R.id.searchFragment, false)
        }
        binding.btFilterApply.setOnClickListener {
            viewModel.saveFilter(reset = false)
            setFragmentResult(SearchFragment.FILTERS_KEY, bundleOf(SearchFragment.FILTERS_EXTRA to true))
            findNavController().popBackStack(R.id.searchFragment, false)
        }

        binding.btFilterReset.setOnClickListener {
            viewModel.saveFilter(reset = true)
            findNavController().navigate(R.id.action_filterFragment_to_searchFragment)
        }
        binding.etSalary.doOnTextChanged { text, _, _, _ ->
            viewModel.changeSalary(text.toString())
        }
        binding.checkBox.setOnCheckedChangeListener { _, isChecked ->
            viewModel.changeOnlyWithSalary(isChecked)
        }
    }

    private fun initializeObservers() {
        viewModel.isChanges.observe(viewLifecycleOwner) { isChanges ->
            binding.btFilterApply.isVisible = isChanges
            binding.btFilterReset.isVisible = viewModel.newFilter != Filter()
        }
    }

    companion object {
        const val FILTER_EXTRA = "FILTER_EXTRA"
        fun createArguments(filter: Filter): Bundle = bundleOf(FILTER_EXTRA to filter)
    }

    private fun getOnPrimaryColor(): Int {
        val typedValue = TypedValue()
        requireContext().theme.resolveAttribute(com.google.android.material.R.attr.colorOnPrimary, typedValue, true)
        return typedValue.resourceId
    }
}
