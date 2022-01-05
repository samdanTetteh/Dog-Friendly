package com.ijikod.dog_friendly.allBreeds.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.composethemeadapter.MdcTheme
import com.ijikod.dog_friendly.R
import com.ijikod.dog_friendly.allBreeds.AllBreedsViewModel
import com.ijikod.dog_friendly.allBreeds.state.AllBreedsEvents
import com.ijikod.dog_friendly.allBreeds.state.AllBreedsStates
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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.init()

        viewModel.events()
            .subscribe{ event ->
                when (event) {
                    is AllBreedsEvents.Loading -> {
                        binding.allBreedsView.setContent {
                            MdcTheme {
                                ShowLoading()
                            }
                        }
                    }

                    is AllBreedsEvents.Error -> {
                        event.error.message?.let { errorMsg ->
                            showToast(errorMsg)
                        }
                    }

                    is AllBreedsEvents.ShowAllBreeds -> {
                        event.state.let { state ->
                            state.getAllBreeds?.let {
                                binding.allBreedsView.setContent {
                                    MdcTheme {
                                        BreedsList(breeds = state.allBreedsFormattedData)
                                    }
                                }
                            }
                        }
                    }
                    else -> {}
                }
            }.addTo(disposable)
    }


    @Composable
    private fun ShowLoading(){
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(Modifier.height(30.dp))
            LinearProgressIndicator()
            Spacer(Modifier.height(30.dp))
        }
    }


    @Composable
    private fun BreedsList(breeds: List<AllBreedsStates.RecyclerViewItem>) {
        LazyColumn {
            items(breeds.size) { index ->
                when(breeds[index]) {
                    is AllBreedsStates.SectionItem -> {
                        BreedTextContent(breed = (breeds[index] as AllBreedsStates.SectionItem).breed)
                    }

                    is AllBreedsStates.ContentItem -> {
                        SubBreedTextContent(subBreed = (breeds[index] as AllBreedsStates.ContentItem).subBreed,
                        breed = (breeds[index] as AllBreedsStates.ContentItem).breed)
                    }
                }
            }
        }

    }


    @Composable
    private fun BreedTextContent(breed: String) {
        Text(
            text = breed,
            style = MaterialTheme.typography.h5,
            modifier = Modifier
                .clickable {
                    showBreedDetails(breed)
                }
                .background(Color(R.color.breed_bg_color))
                .fillMaxWidth()
                .padding(
                    horizontal = dimensionResource(id = R.dimen.breed_txt_padding),
                    vertical = dimensionResource(id = R.dimen.breed_txt_padding)
                )
                .wrapContentWidth(Alignment.Start)
        )
    }


    @Composable
    private fun SubBreedTextContent(subBreed: String, breed: String) {
        Text(
            text = subBreed,
            style = MaterialTheme.typography.subtitle2,
            modifier = Modifier
                .clickable {
                    showBreedDetails(breed, subBreed)
                }
                .fillMaxWidth()
                .padding(
                    horizontal = dimensionResource(id = R.dimen.sub_breed_padding),
                    vertical = dimensionResource(id = R.dimen.sub_breed_padding)
                )
                .wrapContentWidth(Alignment.Start)
        )
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