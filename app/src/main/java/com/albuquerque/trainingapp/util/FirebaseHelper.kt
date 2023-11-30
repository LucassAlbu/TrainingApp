package com.albuquerque.trainingapp.util

import com.albuquerque.trainingapp.R
import com.google.firebase.auth.FirebaseAuth

class FirebaseHelper {

    companion object {

        fun getAuth() = FirebaseAuth.getInstance()
        fun getIdUser() = getAuth().currentUser?.uid ?: ""
        fun isAuthenticated() = getAuth().currentUser != null

        fun validError(error: String): Int {
            return when {
                error.contains(
                    "There is no user record corresponding to this identifier"
                ) -> R.string.account_not_registered

                error.contains(
                    " The email address is badly formatted"
                ) ->
                    R.string.invalid_email

                error.contains(
                    "The password is invalid or the user does not have a password"
                )
                -> R.string.invalid_password

                error.contains("The email address is already in use by another account")
                -> R.string.email_in_use

                error.contains("Password should be at least 6 characters") -> R.string.strong_password
                else -> R.string.error_generic

            }
        }
    }
}
