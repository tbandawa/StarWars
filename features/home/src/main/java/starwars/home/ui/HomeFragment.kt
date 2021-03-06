package starwars.home.ui

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import starwars.data.api.response.Status
import starwars.data.model.Item
import starwars.home.databinding.HomeFragmentBinding

@InternalCoroutinesApi
@AndroidEntryPoint
class HomeFragment : Fragment(), ItemClickListener {

    private val viewModel: HomeViewModel by viewModels()

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
                                    this@HomeFragment
                                )
                            }
                        }
                    }
                    Status.ERROR -> {
                        binding.textError.text = resource.message
                        binding.layoutRetry.visibility = View.VISIBLE
                        binding.progressAction.visibility = View.GONE
                        binding.recyclerItems.visibility = View.GONE
                    }
                }
            }
        }

        binding.cardSearch.setOnClickListener {
            val uri = Uri.parse("starwars://search")
            findNavController().navigate(uri)
        }

        binding.buttonRetry.setOnClickListener {
            viewModel.getRootItems()
        }

    }

    override fun onClick(item: Item) {
        val uri = Uri.parse("starwars://resources/${item.type}")
        findNavController().navigate(uri)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}