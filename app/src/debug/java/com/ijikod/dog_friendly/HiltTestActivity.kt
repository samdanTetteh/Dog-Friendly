package com.ijikod.dog_friendly

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ijikod.dog_friendly.databinding.ActivityMainBinding
import com.ijikod.dog_friendly.details.fragment.FragmentListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HiltTestActivity : AppCompatActivity(), FragmentListener {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
    }
    override fun onChangeToolBarTitle(title: String) {
        activityMainBinding.toolbar.title = title
    }
}