package com.kodeleku.mvvm_damd.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kodeleku.mvvm_damd.data.repository.UserRepository
import com.kodeleku.mvvm_damd.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userRepository: UserRepository): ViewModel() {

    private val _userState = MutableStateFlow<User?>(null)
    val userState: StateFlow<User?> get() = _userState

    /**
     * Iniciar sesión y actualizar estado
     */
    fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            val user = userRepository.loginUser(email, password)
            _userState.value = user
        }
    }

    /**
     * Registrar usuario y actualizar estado
     */
    fun registerUser(email: String, password: String) {
        viewModelScope.launch {
            val user = userRepository.registerUser(email, password)
            _userState.value = user
        }
    }

    /**
     * Obtener usuario local
     */
    fun getLocalUser() {
        viewModelScope.launch {
            _userState.value = userRepository.getLocalUser()
        }
    }

    /**
     * Cerrar sesión
     */
    fun logoutUser() {
        viewModelScope.launch {
            userRepository.logoutUser()
            _userState.value = null
        }
    }
}
