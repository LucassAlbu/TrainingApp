package com.albuquerque.trainingapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.projetoFirebase.util.CustomMutableLiveData

open class BaseViewModel<T> : ViewModel() {
    val uiStateLogin: CustomMutableLiveData<T> = CustomMutableLiveData()
    fun uiStateLogin(): LiveData<T> = uiStateLogin

    val uiStateSignUp: CustomMutableLiveData<T> = CustomMutableLiveData()
    fun uiStateSignUp(): LiveData<T> = uiStateSignUp

    val uiStateRecoverAccount: CustomMutableLiveData<T> = CustomMutableLiveData()
    fun uiStateRecoverAccount(): LiveData<T> = uiStateRecoverAccount

    fun cleanUiState() {
        uiStateLogin.value = null!!
        uiStateSignUp.value = null!!
        uiStateRecoverAccount.value = null!!
    }
}