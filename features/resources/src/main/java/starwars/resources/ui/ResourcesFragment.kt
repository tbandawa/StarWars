package starwars.resources.ui

import android.net.UrlQuerySanitizer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import me.tbandawa.android.commons.extensions.capitaliseFirst
import starwars.data.api.response.Status
import starwars.resources.R
import starwars.resources.databinding.ResourcesFragmentBinding
import timber.log.Timber
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
                        binding.layoutRetry.visibility = View.GONE
                    }
                    Status.SUCCESS -> {
                        binding.layoutRetry.visibility = View.GONE
                        binding.progressAction.visibility = View.GONE
                        binding.baseResult = result.data

                        Timber.d("previous = ${resource.data?.previous}")
                        Timber.d("next = ${resource.data?.next}")

                    }
                    Status.ERROR -> {
                        binding.layoutRetry.visibility = View.VISIBLE
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