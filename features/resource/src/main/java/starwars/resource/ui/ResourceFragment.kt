package starwars.resource.ui

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import me.tbandawa.android.commons.extensions.getResourceId
import starwars.data.api.response.Status
import starwars.data.model.Starship
import starwars.resource.R
import starwars.resource.databinding.ResourceFragmentBinding
import timber.log.Timber

class ResourceFragment : Fragment() {

    private val viewModel: ResourceViewModel by activityViewModels()

    private var _binding: ResourceFragmentBinding? = null
    private val binding get() = _binding!!

    private val args: ResourceFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ResourceFragmentBinding.inflate(inflater, container, false).apply {
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

        viewModel.getResource(args.resourceType, args.resourceId)

        viewModel.resource.observe(viewLifecycleOwner) { result ->
            result?.let { resource ->
                when (resource.status) {
                    Status.LOADING -> {
                        binding.progressAction.visibility = View.VISIBLE
                        binding.layoutRetry.visibility = View.GONE
                    }
                    Status.SUCCESS -> {
                        binding.layoutRetry.visibility = View.GONE
                        binding.progressAction.visibility = View.GONE

                        if (result.data is Starship) {
                            binding.starship = result.data as Starship


                        }

                    }
                    Status.ERROR -> {
                        binding.layoutRetry.visibility = View.VISIBLE
                        binding.progressAction.visibility = View.GONE
                    }
                }
            }
        }

        binding.buttonRetry.setOnClickListener {
            viewModel.getResource(args.resourceType, args.resourceId)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}