package com.example.advancedappdevelopment.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.advancedappdevelopment.data.model.dataClass.User
import com.example.advancedappdevelopment.data.model.firebaseAdapter.updateCurrentUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.lang.IllegalArgumentException

class LoginViewModel : ViewModel() {
    private val auth: FirebaseAuth = Firebase.auth


    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    private val _loginSuccess = MutableLiveData(false)
    val loginSuccess: LiveData<Boolean> = _loginSuccess
    val _showLoginSuccess = MutableLiveData(false)
    val showLoginSuccess: LiveData<Boolean> = _showLoginSuccess


    private val _email = MutableLiveData("")
    val email: LiveData<String> = _email
    fun updateEmail(newEmail: String) {
        _email.value = newEmail
    }

    private val _password = MutableLiveData("")
    val password: LiveData<String> = _password
    fun updatePassword(newPassword: String) {
        _password.value = newPassword
    }

    fun loginUser() {
        if (_loading.value == false) {
            val email: String = _email.value ?: throw IllegalArgumentException("email expected")
            val password: String = _password.value ?: throw IllegalArgumentException("password expected")

            //Login in Firestore
            _loading.value = true
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        updateCurrentUser()
                        _showLoginSuccess.value = true
                        _loginSuccess.value = true
                        _loading.value = false
                    }
                }
                .addOnFailureListener {
                    _showLoginSuccess.value = true
                    _loginSuccess.value = false
                    _loading.value = false

                }
        }
    }

}