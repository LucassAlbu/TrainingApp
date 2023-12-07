package com.albuquerque.trainingapp.presentation

sealed class UIState {

    object Loading : UIState()
    object AuthSuccess : UIState()
    object ErrorApi : UIState()
    object ErrorNetwork : UIState()
    object EmptyState : UIState()
    object FireBaseLogout : UIState()
    object ErrorRecoverAccount : UIState()
    data class AuthError(val error: String, val title: String) : UIState()

}
