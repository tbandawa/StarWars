package starwars.resource.ui

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import dagger.hilt.android.AndroidEntryPoint
import starwars.data.api.response.Status
import starwars.data.model.*
import starwars.resource.R
import starwars.resource.databinding.ResourceFragmentBinding
import timber.log.Timber

@AndroidEntryPoint
class ResourceFragment : Fragment() {

    private val viewModel: ResourceViewModel by viewModels()

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

                            val externalLinks = result.data as BaseResource
                            externalLinks.films?.let { films ->
                                binding.layoutGroupFilms.visibility = View.VISIBLE
                                for (film in films) {
                                    populateChips(binding.groupFilms, film)
                                }
                            }
                            externalLinks.characters?.let { characters ->
                                binding.layoutGroupCharacters.visibility = View.VISIBLE
                                for (character in characters) {
                                    populateChips(binding.groupCharacters, character)
                                }
                            }
                            externalLinks.residents?.let { residents ->
                                binding.layoutGroupResidents.visibility = View.VISIBLE
                                for (resident in residents) {
                                    populateChips(binding.groupResidents, resident)
                                }
                            }
                            externalLinks.people?.let { people ->
                                binding.layoutGroupPeople.visibility = View.VISIBLE
                                for (person in people) {
                                    populateChips(binding.groupPeople, person)
                                }
                            }
                            externalLinks.pilots?.let { pilots ->
                                binding.layoutGroupPilots.visibility = View.VISIBLE
                                for (pilot in pilots) {
                                    populateChips(binding.groupPilots, pilot)
                                }
                            }
                            externalLinks.planets?.let { planets ->
                                binding.layoutGroupPlanets.visibility = View.VISIBLE
                                for (planet in planets) {
                                    populateChips(binding.groupPlanets, planet)
                                }
                            }
                            externalLinks.starships?.let { starships ->
                                binding.layoutGroupStarships.visibility = View.VISIBLE
                                for (starship in starships) {
                                    populateChips(binding.groupStarships, starship)
                                }
                            }
                            externalLinks.vehicles?.let { vehicles ->
                                binding.layoutGroupVehicles.visibility = View.VISIBLE
                                for (vehicle in vehicles) {
                                    populateChips(binding.groupVehicles, vehicle)
                                }
                            }
                            externalLinks.species?.let { species ->
                                binding.layoutGroupSpecies.visibility = View.VISIBLE
                                for (specie in species) {
                                    populateChips(binding.groupSpecies, specie)
                                }
                            }

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

    private fun populateChips(chipGroup: ChipGroup, resourceLink: String) {
        val chip = Chip(requireContext())
        chip.text = resourceLink
        chip.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorBlack))
        chip.chipBackgroundColor = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.colorWhite))
        chipGroup.addView(chip as View)
        chip.isClickable = false
        chip.setOnClickListener {
            Timber.d(resourceLink)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}