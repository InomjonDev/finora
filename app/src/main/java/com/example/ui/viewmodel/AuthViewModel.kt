package com.example.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

sealed interface AuthUiState {
    object Idle : AuthUiState
    object Loading : AuthUiState
    data class Success(val message: String) : AuthUiState
    data class Error(val error: String) : AuthUiState
}

class AuthViewModel : ViewModel() {

    // Login Form State
    private val _loginEmail = MutableStateFlow("")
    val loginEmail: StateFlow<String> = _loginEmail.asStateFlow()

    private val _loginPassword = MutableStateFlow("")
    val loginPassword: StateFlow<String> = _loginPassword.asStateFlow()

    private val _loginPasswordVisible = MutableStateFlow(false)
    val loginPasswordVisible: StateFlow<Boolean> = _loginPasswordVisible.asStateFlow()

    // Sign Up Form State
    private val _signUpName = MutableStateFlow("")
    val signUpName: StateFlow<String> = _signUpName.asStateFlow()

    private val _signUpEmail = MutableStateFlow("")
    val signUpEmail: StateFlow<String> = _signUpEmail.asStateFlow()

    private val _signUpPassword = MutableStateFlow("")
    val signUpPassword: StateFlow<String> = _signUpPassword.asStateFlow()

    private val _signUpConfirmPassword = MutableStateFlow("")
    val signUpConfirmPassword: StateFlow<String> = _signUpConfirmPassword.asStateFlow()

    private val _signUpPasswordVisible = MutableStateFlow(false)
    val signUpPasswordVisible: StateFlow<Boolean> = _signUpPasswordVisible.asStateFlow()

    private val _termsAccepted = MutableStateFlow(false)
    val termsAccepted: StateFlow<Boolean> = _termsAccepted.asStateFlow()

    // Common UI state
    private val _uiState = MutableStateFlow<AuthUiState>(AuthUiState.Idle)
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    fun updateLoginEmail(email: String) {
        _loginEmail.value = email
    }

    fun updateLoginPassword(password: String) {
        _loginPassword.value = password
    }

    fun toggleLoginPasswordVisibility() {
        _loginPasswordVisible.value = !_loginPasswordVisible.value
    }

    fun updateSignUpName(name: String) {
        _signUpName.value = name
    }

    fun updateSignUpEmail(email: String) {
        _signUpEmail.value = email
    }

    fun updateSignUpPassword(password: String) {
        _signUpPassword.value = password
    }

    fun updateSignUpConfirmPassword(password: String) {
        _signUpConfirmPassword.value = password
    }

    fun toggleSignUpPasswordVisibility() {
        _signUpPasswordVisible.value = !_signUpPasswordVisible.value
    }

    fun updateTermsAccepted(accepted: Boolean) {
        _termsAccepted.value = accepted
    }

    fun resetState() {
        _uiState.value = AuthUiState.Idle
    }

    fun performLogin(onSuccess: () -> Unit) {
        val email = _loginEmail.value.trim()
        val password = _loginPassword.value

        if (email.isEmpty()) {
            _uiState.value = AuthUiState.Error("Please enter your email address.")
            return
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _uiState.value = AuthUiState.Error("Please enter a valid email address.")
            return
        }
        if (password.length < 6) {
            _uiState.value = AuthUiState.Error("Password must be at least 6 characters.")
            return
        }

        _uiState.value = AuthUiState.Loading
        // Simulate background login network step
        kotlinx.coroutines.GlobalScope.run {
            // Note: We use a simple coroutine to display a visual loading delay in the applet
            val timer = java.util.Timer()
            timer.schedule(object : java.util.TimerTask() {
                override fun run() {
                    _uiState.value = AuthUiState.Success("Log in successful!")
                    onSuccess()
                }
            }, 1200)
        }
    }

    fun performSignUp(onSuccess: () -> Unit) {
        val name = _signUpName.value.trim()
        val email = _signUpEmail.value.trim()
        val password = _signUpPassword.value
        val confirmPassword = _signUpConfirmPassword.value
        val terms = _termsAccepted.value

        if (name.isEmpty()) {
            _uiState.value = AuthUiState.Error("Please enter your full name.")
            return
        }
        if (email.isEmpty()) {
            _uiState.value = AuthUiState.Error("Please enter your email address.")
            return
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _uiState.value = AuthUiState.Error("Please enter a valid email address.")
            return
        }
        if (password.length < 6) {
            _uiState.value = AuthUiState.Error("Password must be at least 6 characters.")
            return
        }
        if (password != confirmPassword) {
            _uiState.value = AuthUiState.Error("Passwords do not match.")
            return
        }
        if (!terms) {
            _uiState.value = AuthUiState.Error("Please agree to the Terms of Service and Privacy Policy.")
            return
        }

        _uiState.value = AuthUiState.Loading
        val timer = java.util.Timer()
        timer.schedule(object : java.util.TimerTask() {
            override fun run() {
                _uiState.value = AuthUiState.Success("Account created successfully!")
                onSuccess()
            }
        }, 1500)
    }
}
