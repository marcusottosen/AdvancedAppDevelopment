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

class RegisterViewModel : ViewModel() {
    private val auth: FirebaseAuth = Firebase.auth


    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    private val _registerSucces = MutableLiveData(false)
    val registerSuccess: LiveData<Boolean> = _registerSucces
    val _showRegisterSucces = MutableLiveData(false)
    val showRegisterSucess: LiveData<Boolean> = _showRegisterSucces


    private val _name = MutableLiveData("")
    val name: LiveData<String> = _name
    fun updateName(newFirstName: String) {
        _name.value = newFirstName
    }

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

    private val _passwordCheck = MutableLiveData("")
    val passwordCheck: LiveData<String> = _passwordCheck
    fun updatePasswordCheck(newPasswordCheck: String) {
        _passwordCheck.value = newPasswordCheck
    }


    fun registerUser() {
        if (_loading.value == false){
            if (_password.value == _passwordCheck.value){
                val name: String = _name.value ?: throw IllegalArgumentException("name expected")
                val email: String = _email.value ?: throw IllegalArgumentException("email expected")
                val password: String = _password.value ?: throw IllegalArgumentException("password expected")


                //Create in Firestore
                _loading.value = true
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            Firebase.firestore.collection("users").document(FirebaseAuth.getInstance().uid.toString()).set(
                                User(
                                    id = FirebaseAuth.getInstance().uid.toString(),
                                    name = name,
                                    email = email,
                                    signUpDate = com.google.firebase.Timestamp.now()
                                ))
                            updateCurrentUser()
                        }
                        _showRegisterSucces.value = true
                        _registerSucces.value = true
                        _loading.value = false
                    }
                    .addOnFailureListener {
                        _showRegisterSucces.value = true
                        _registerSucces.value = false

                    }
            }
        }
    }

}