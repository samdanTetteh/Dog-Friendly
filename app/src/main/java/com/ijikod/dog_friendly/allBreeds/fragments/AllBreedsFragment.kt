package com.ijikod.dog_friendly.allBreeds.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ijikod.dog_friendly.allBreeds.AllBreedsViewModel
import com.ijikod.dog_friendly.allBreeds.adapter.BreedListAdapter
import com.ijikod.dog_friendly.allBreeds.state.AllBreedsEvents
import com.ijikod.dog_friendly.common.AutoCompositeDisposable
import com.ijikod.dog_friendly.common.addTo
import com.ijikod.dog_friendly.databinding.FragmentAllBreedsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllBreedsFragment : Fragment() {

    private var _binding: FragmentAllBreedsBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val disposable: AutoCompositeDisposable by lazy { AutoCompositeDisposable(lifecycle) }
    private val viewModel: AllBreedsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAllBreedsBinding.inflate(inflater, container, false)

        val listAdapter = BreedListAdapter(
            showDetails = ::showBreedDetails)
        binding.allBreedsList.adapter = listAdapter

        viewModel.init()

        viewModel.events()
            .subscribe{ event ->

                binding.progressBar.isVisible = (event is AllBreedsEvents.Loading)

                if (event is AllBreedsEvents.Error) {
                    event.error.message?.let { errorMsg ->
                        showToast(errorMsg)
                    }
                }

                if (event is AllBreedsEvents.ShowAllBreeds) {
                    event.state.let { state ->
                        state.getAllBreeds?.let {
                            binding.progressBar.isVisible = state.isLoading
                            listAdapter.data = state.allBreedsFormattedData
                            listAdapter.notifyDataSetChanged()
                        }
                    }
                }
            }.addTo(disposable)

        return binding.root
    }


    private fun showBreedDetails(breed: String, subBreed: String = String()) {
        val detailsFragmentAction = AllBreedsFragmentDirections
            .actionAllBreedsFragmentToDetailsFragment(breed, subBreed)
        findNavController().navigate(detailsFragmentAction)
    }

    private fun showToast(msg: String){
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}