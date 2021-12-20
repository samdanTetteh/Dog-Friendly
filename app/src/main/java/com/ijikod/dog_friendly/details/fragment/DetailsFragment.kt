package com.ijikod.dog_friendly.details.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ijikod.dog_friendly.allBreeds.AllBreedsViewModel
import com.ijikod.dog_friendly.allBreeds.state.AllBreedsEvents
import com.ijikod.dog_friendly.common.*
import com.ijikod.dog_friendly.databinding.FragmentDetailsBinding
import com.ijikod.dog_friendly.details.adapter.BreedDetailsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment: Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: AllBreedsViewModel by viewModels()
    private val disposable: AutoCompositeDisposable by lazy { AutoCompositeDisposable(lifecycle) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter  = BreedDetailsAdapter()
        binding.breedDetailsList.adapter = adapter
        binding.breedDetailsList.autoFitColumns(100)

        arguments?.let { args ->
            val breed = args.getString(BREED_ARG, String())
            val  subBreed = args.getString(SUB_BREED_ARG, String())

            (viewModel::onShowBreedDetails)(breed, subBreed)
        }

        viewModel.events()
            .subscribe{ event ->
                binding.progressBar.isVisible = (event is AllBreedsEvents.Loading)

                if (event is AllBreedsEvents.ShowBreedDetails) {
                    event.state.getBreedDetails?.let { breedDetails ->
                        adapter.data =  breedDetails
                        adapter.notifyDataSetChanged()
                    }
                }

            }.addTo(disposable)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }



}