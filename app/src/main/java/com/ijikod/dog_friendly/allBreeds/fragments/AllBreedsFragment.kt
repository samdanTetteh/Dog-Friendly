package com.ijikod.dog_friendly.allBreeds.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.ijikod.dog_friendly.allBreeds.AllBreedsViewModel
import com.ijikod.dog_friendly.allBreeds.adapter.BreedListAdapter
import com.ijikod.dog_friendly.common.AsyncResult
import com.ijikod.dog_friendly.common.AutoCompositeDisposable
import com.ijikod.dog_friendly.common.addTo
import com.ijikod.dog_friendly.databinding.FragmentAllBreedsBinding
import com.ijikod.domain.allBreeds.entity.AllBreeds
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
        var data: AllBreeds?

        val listAdapter = BreedListAdapter(
            showSubBreeds = ::showSubBreed)
        binding.allBreedsList.adapter = listAdapter

        viewModel
            .states()
            .subscribe{ state ->
                binding.progressBar.isVisible = state.isLoading

                if (state.allBreeds is AsyncResult.Success) {
                    state.getAllBreeds.let {
                        listAdapter.data = state.breedsData
                    }
                }


        }.addTo(disposable)

        return binding.root
    }


    private fun showSubBreed(subBreed: String) {
        TODO("implement sub breed")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}