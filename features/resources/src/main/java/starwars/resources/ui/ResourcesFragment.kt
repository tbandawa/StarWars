package starwars.resources.ui

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import me.tbandawa.android.commons.extensions.capitaliseFirst
import me.tbandawa.android.commons.extensions.getResourceId
import starwars.data.api.response.Status
import starwars.resources.R
import starwars.resources.databinding.ResourcesFragmentBinding
import java.util.*

@AndroidEntryPoint
class ResourcesFragment : Fragment(), ResourcesClickListener {

    private val viewModel: ResourcesViewModel by viewModels()

    private var _binding: ResourcesFragmentBinding? = null
    private val binding get() = _binding!!

    private val args: ResourcesFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ResourcesFragmentBinding.inflate(inflater, container, false).apply {
            view = this@ResourcesFragment
            (activity as AppCompatActivity?)!!.setSupportActionBar(toolBar)
            toolBar.title = args.resourceType.capitaliseFirst()
            toolBar.setNavigationIcon(R.drawable.ic_exit)
            toolBar.setNavigationOnClickListener {
                activity?.onBackPressed()
            }
        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        viewModel.getResources(args.resourceType)

        viewModel.resourceItems.observe(viewLifecycleOwner) { result ->
            result?.let { resource ->
                when (resource.status) {
                    Status.LOADING -> {
                        binding.progressAction.visibility = View.VISIBLE
                        binding.recyclerResources.visibility = View.GONE
                        binding.layoutControls.visibility = View.GONE
                        binding.layoutRetry.visibility = View.GONE
                    }
                    Status.SUCCESS -> {
                        binding.layoutRetry.visibility = View.GONE
                        binding.progressAction.visibility = View.GONE
                        binding.baseResult = result.data

                        binding.layoutControls.visibility = View.VISIBLE
                        binding.recyclerResources.apply {
                            visibility = View.VISIBLE
                            adapter = resource.data?.let { resources ->
                                ResourcesAdapter(
                                    args.resourceType,
                                    resources.results,
                                    ResourceClickListener { url ->
                                        val page = url.getResourceId()
                                        val uri = Uri.parse("starwars://resource/${args.resourceType}/${page}")
                                        findNavController().navigate(uri)
                                    }
                                )
                            }
                        }

                    }
                    Status.ERROR -> {
                        binding.textError.text = resource.message
                        binding.layoutRetry.visibility = View.VISIBLE
                        binding.layoutControls.visibility = View.VISIBLE
                        binding.progressAction.visibility = View.GONE
                        binding.recyclerResources.visibility = View.GONE
                    }
                }
            }
        }

    }

    override fun onNextClick(url: String) {
        viewModel.getResourcesByPage(args.resourceType, url)
    }

    override fun onPrevious(url: String) {
        viewModel.getResourcesByPage(args.resourceType, url)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}