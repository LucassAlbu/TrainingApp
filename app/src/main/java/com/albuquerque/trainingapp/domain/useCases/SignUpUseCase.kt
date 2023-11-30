package com.albuquerque.trainingapp.domain.useCases

import com.albuquerque.trainingapp.repository.Repository
import com.google.firebase.auth.FirebaseUser
import com.projetoFirebase.util.DefaultListenerResponse
import javax.inject.Inject

class SignUpUseCase @Inject constructor(repositoryFirebase: Repository) :
    BaseUseCase<Repository, FirebaseUser, String>(repositoryFirebase) {
    override suspend fun <T> run(
        defaultListenerResponse: DefaultListenerResponse<FirebaseUser, String>,
        vararg data: T
    ) {
        val email = data.first() as String
        val password = data[1] as String



        repository.signUpFirebase(
            email,
            password,
            object : DefaultListenerResponse<FirebaseUser, String> {
                override fun onSuccess(data: FirebaseUser) {
                    defaultListenerResponse.onSuccess(data)
                }

                override fun onError(error: String) {
                    defaultListenerResponse.onError(error)
                }

            }
        )
    }
}