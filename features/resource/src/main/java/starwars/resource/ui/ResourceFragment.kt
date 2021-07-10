package starwars.resource.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import starwars.data.api.response.Status
import starwars.data.model.*
import starwars.resource.R
import starwars.resource.databinding.ResourceFragmentBinding

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
            toolBar.title = ""
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
                        binding.layoutDetails.visibility = View.GONE
                        binding.layoutRetry.visibility = View.GONE
                    }
                    Status.SUCCESS -> {
                        binding.apply {

                            species = null
                            vehicle = null
                            planet = null
                            film = null
                            person = null
                            starship = null

                            if (result.data is Person)
                                person = result.data as Person
                            if (result.data is Film)
                                film = result.data as Film
                            if (result.data is Planet)
                                planet = result.data as Planet
                            if (result.data is Vehicle)
                                vehicle = result.data as Vehicle
                            if (result.data is Species)
                                species = result.data as Species
                            if (result.data is Starship)
                                starship = result.data as Starship

                            layoutDetails.visibility = View.VISIBLE
                            layoutRetry.visibility = View.GONE
                            progressAction.visibility = View.GONE

                        }
                    }
                    Status.ERROR -> {
                        binding.layoutRetry.visibility = View.VISIBLE
                        binding.progressAction.visibility = View.GONE
                        binding.layoutDetails.visibility = View.GONE
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