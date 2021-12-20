package com.ijikod.dog_friendly

import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

// A custom runner to set up the instrumented application class for tests.
class CustomTestRunner : AndroidJUnitRunner() {

    override fun newApplication(cl: ClassLoader?, name: String?, context: Context?): android.app.Application {
        return super.newApplication(cl, Application::class.java.name, context)
    }
}