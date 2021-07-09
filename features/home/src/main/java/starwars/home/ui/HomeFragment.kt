package starwars.home.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import starwars.data.api.response.Status
import starwars.home.databinding.HomeFragmentBinding

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by activityViewModels()

    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        viewModel.getRootItems()

        viewModel.rootItems.observe(viewLifecycleOwner) { result ->
            result?.let { resource ->
                when (resource.status) {
                    Status.LOADING -> {
                        binding.progressAction.visibility = View.VISIBLE
                        binding.recyclerItems.visibility = View.GONE
                        binding.layoutRetry.visibility = View.GONE
                    }
                    Status.SUCCESS -> {
                        binding.layoutRetry.visibility = View.GONE
                        binding.progressAction.visibility = View.GONE
                        binding.recyclerItems.apply {
                            visibility = View.VISIBLE
                            adapter = resource.data?.let { items ->
                                ItemAdapter(
                                    items.toList(),
                                    ItemClickListener { item ->

                                    }
                                )
                            }
                        }
                    }
                    Status.ERROR -> {
                        binding.layoutRetry.visibility = View.VISIBLE
                        binding.progressAction.visibility = View.GONE
                        binding.recyclerItems.visibility = View.GONE
                    }
                }
            }
        }

        binding.buttonRetry.setOnClickListener {
            viewModel.getRootItems()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}