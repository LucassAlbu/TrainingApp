package com.albuquerque.trainingapp.repository

import com.albuquerque.trainingapp.util.FirebaseHelper
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.projetoFirebase.util.DefaultListenerResponse
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val fireBaseAuth: FirebaseAuth
) : Repository {
    override val currentUser: FirebaseUser?
        get() = fireBaseAuth.currentUser


    override suspend fun loginFirebase(
        email: String,
        password: String,
        listenerResponse: DefaultListenerResponse<FirebaseUser, String>
    ) {
        return try {
            val result = fireBaseAuth.signInWithEmailAndPassword(
                email,
                password
            ).await()
            listenerResponse.onSuccess(result.user!!)

        } catch (e: Exception) {
            e.printStackTrace()
            listenerResponse.onError(e.localizedMessage)
        }
    }

    override suspend fun signUpFirebase(
        email: String,
        password: String,
        listenerResponse: DefaultListenerResponse<FirebaseUser, String>
    ) {
        return try {
            val result =
                fireBaseAuth.createUserWithEmailAndPassword(email, password)
                    .await()
            listenerResponse.onSuccess(result.user!!)
        } catch (e: Exception) {
            e.printStackTrace()
            listenerResponse.onError(e.localizedMessage)
        }
    }

    override suspend fun recoverAccountFireBase(
        email: String,
        listenerResponse: DefaultListenerResponse<FirebaseUser, String>
    ) {
        return try {
            val result = fireBaseAuth.sendPasswordResetEmail(email).await()

        } catch (e: Exception) {
            e.printStackTrace()
            listenerResponse.onError(e.localizedMessage)
        }
    }

    override fun logout() {
        fireBaseAuth.signOut()
    }
}