package starwars.resources.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import starwars.resources.R
import starwars.resources.databinding.ResourcesFragmentBinding
import timber.log.Timber
import java.util.*

class ResourcesFragment : Fragment() {

    private val viewModel: ResourcesViewModel by activityViewModels()

    private var _binding: ResourcesFragmentBinding? = null
    private val binding get() = _binding!!

    private val args: ResourcesFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ResourcesFragmentBinding.inflate(inflater, container, false).apply {
            (activity as AppCompatActivity?)!!.setSupportActionBar(toolBar)
            toolBar.title = args.resourceType.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.getDefault()
                ) else it.toString()
            }
            toolBar.setNavigationIcon(R.drawable.ic_exit)
            toolBar.setNavigationOnClickListener {
                activity?.onBackPressed()
            }
        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}