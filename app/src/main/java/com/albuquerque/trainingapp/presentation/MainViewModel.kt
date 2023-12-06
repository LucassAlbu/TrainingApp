package com.albuquerque.trainingapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.albuquerque.trainingapp.domain.useCases.LoginUseCase
import com.albuquerque.trainingapp.domain.useCases.RecoverAccountUseCase
import com.albuquerque.trainingapp.domain.useCases.SignUpUseCase
import com.albuquerque.trainingapp.repository.Repository
import com.google.firebase.auth.FirebaseUser
import com.projetoFirebase.util.DefaultListenerResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository,
    private val loginFBUseCase: LoginUseCase,
    private val signUpFBUseCase: SignUpUseCase,
    private val recoverAccountFBUseCase: RecoverAccountUseCase,
) : BaseViewModel<UIState>() {

    private val _login = MutableLiveData<FirebaseUser>(null)
    val login: LiveData<FirebaseUser> = _login

    private val _signUp = MutableLiveData<FirebaseUser>(null)
    val signUp: LiveData<FirebaseUser> = _signUp

    private val _recoverAccount = MutableLiveData<FirebaseUser>(null)
    val recoverAccount: LiveData<FirebaseUser> = _recoverAccount

    val currentUser: FirebaseUser?
        get() = repository.currentUser

    init {
        if (repository.currentUser != null) {
            _login.value = repository.currentUser!!
        }
    }

    fun loginFireBase(
        email: String,
        password: String
    ) {
        uiStateLogin.postValue(UIState.Loading)
        viewModelScope.launch {
            loginFBUseCase.run(
                object : DefaultListenerResponse<FirebaseUser, String> {
                    override fun onSuccess(data: FirebaseUser) {
                        _login.postValue(data)
                        uiStateLogin.postValue(UIState.AuthSuccess)
                    }

                    override fun onError(error: String) {
                        uiStateLogin.postValue(UIState.AuthError(error, "Erro ao fazer Login"))
                    }

                },
                email,
                password

            )
        }
    }

    fun signUpFirebase(
        email: String,
        password: String
    ) {
        uiStateSignUp.postValue(UIState.Loading)
        viewModelScope.launch {
            signUpFBUseCase.run(
                object : DefaultListenerResponse<FirebaseUser, String> {
                    override fun onSuccess(data: FirebaseUser) {
                        _signUp.postValue(data)
                        uiStateSignUp.postValue(UIState.AuthSuccess)
                    }

                    override fun onError(error: String) {
                        uiStateSignUp.postValue(UIState.AuthError(error, "Erro ao cadastrar"))
                    }
                },
                email,
                password
            )
        }
    }


    fun recoverAccountFireBase(
        email: String
    ) {
        uiStateRecoverAccount.postValue(UIState.Loading)
        viewModelScope.launch {
            recoverAccountFBUseCase.run(
                object : DefaultListenerResponse<FirebaseUser, String> {
                    override fun onSuccess(data: FirebaseUser) {
                        _recoverAccount.postValue(data)
                        uiStateRecoverAccount.postValue(UIState.AuthSuccess)
                    }

                    override fun onError(error: String) {
                        uiStateRecoverAccount.postValue(UIState.ErrorRecoverAccount)

                    }
                },
                email
            )
        }
    }


}