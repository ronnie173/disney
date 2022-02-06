package com.example.disneyproject.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.disneyproject.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * Home view model
 *
 * @property repository
 * @constructor Create empty Home view model
 */
@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository):ViewModel() {

    /**
     * Get users
     * Uses coroutines to emit the data
     */
    fun getUsers() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.getAllComics()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}