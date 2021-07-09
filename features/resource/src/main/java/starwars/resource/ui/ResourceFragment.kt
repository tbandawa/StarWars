package starwars.resource.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
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

            Timber.d("resourceType = ${args.resourceType}")
            Timber.d("resourceId = ${args.resourceId}")

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}