package starwars.search.ui

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import me.tbandawa.android.commons.extensions.getResourceId
import starwars.data.api.response.Status
import starwars.search.R
import starwars.search.databinding.SearchFragmentBinding

@AndroidEntryPoint
class SearchFragment : Fragment(), ResourcesClickListener {

    private val viewModel: SearchViewModel by viewModels()

    private var _binding: SearchFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var searchTye: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SearchFragmentBinding.inflate(inflater, container, false).apply {
            view = this@SearchFragment
            (activity as AppCompatActivity?)!!.setSupportActionBar(toolBar)
            toolBar.setNavigationIcon(R.drawable.ic_exit)
            toolBar.setNavigationOnClickListener {
                activity?.onBackPressed()
            }
        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        val items = listOf("people", "planets", "films", "species", "vehicles", "starships")
        val adapter = ArrayAdapter(requireContext(), R.layout.dropdown_type, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerType.adapter = adapter

        viewModel.resourceItems.observe(viewLifecycleOwner) { result ->
            result?.let { resource ->
                when (resource.status) {
                    Status.LOADING -> {
                        binding.progressAction.visibility = View.VISIBLE
                        binding.recyclerResources.visibility = View.GONE
                        binding.layoutControls.visibility = View.GONE
                        binding.layoutRetry.visibility = View.GONE
                        binding.layoutEmpty.visibility = View.GONE
                    }
                    Status.SUCCESS -> {
                        binding.layoutControls.visibility = View.VISIBLE
                        binding.recyclerResources.visibility = View.VISIBLE
                        binding.layoutRetry.visibility = View.GONE
                        binding.progressAction.visibility = View.GONE
                        
                        binding.baseResult = result.data

                        if (resource.data?.results?.size  == 0) {
                            binding.layoutEmpty.visibility = View.VISIBLE
                        }

                        binding.recyclerResources.adapter = resource.data?.let {
                            ResourcesAdapter(
                                searchTye,
                                it.results,
                                this@SearchFragment
                            )
                        }

                    }
                    Status.ERROR -> {
                        binding.textError.text = resource.message
                        binding.layoutRetry.visibility = View.VISIBLE
                        binding.layoutControls.visibility = View.VISIBLE
                        binding.layoutEmpty.visibility = View.GONE
                        binding.progressAction.visibility = View.GONE
                        binding.recyclerResources.visibility = View.GONE
                    }
                }
            }
        }

        binding.editQuery.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH && binding.editQuery.text.isNotBlank()) {
                val typeIndex = binding.spinnerType.selectedItemPosition
                searchTye = items[typeIndex]
                viewModel.searchResource(searchTye, binding.editQuery.text.toString())
            }
            false
        }

        binding.buttonSearch.setOnClickListener {
            val typeIndex = binding.spinnerType.selectedItemPosition
            searchTye = items[typeIndex]
            binding.editQuery.clearFocus()
            if(binding.editQuery.text.isNotBlank()) {
                binding.editQuery.onEditorAction(EditorInfo.IME_ACTION_DONE)
                viewModel.searchResource(searchTye, binding.editQuery.text.toString())
            }
        }

    }

    override fun onNextClick(url: String) {
        viewModel.getResourcesByPage(searchTye, url)
    }

    override fun onPrevious(url: String) {
        viewModel.getResourcesByPage(searchTye, url)
    }

    override fun onResourceClick(url: String) {
        val page = url.getResourceId()
        val uri = Uri.parse("starwars://resource/${searchTye}/${page}")
        findNavController().navigate(uri)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}