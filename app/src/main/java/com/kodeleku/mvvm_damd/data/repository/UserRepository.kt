package com.kodeleku.mvvm_damd.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.kodeleku.mvvm_damd.data.local.UserDao
import com.kodeleku.mvvm_damd.model.User
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
    private val userDao: UserDao
) {

    /**
     * Autenticar usuario con email y contraseña
     */
    suspend fun loginUser(email: String, password: String): User? {
        return try {
            val result = auth.signInWithEmailAndPassword(email, password).await()
            result.user?.let { firebaseUser ->
                val user = User(firebaseUser.uid, firebaseUser.email ?: "")
                userDao.insertUser(user) // Guarda en Room
                user // Retorna el usuario autenticado
            }
        } catch (e: Exception) {
            null
        }
    }

    /**
     * Registrar usuario con email y contraseña
     */
    suspend fun registerUser(email: String, password: String): User? {
        return try {
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            result.user?.let { firebaseUser ->
                val user = User(firebaseUser.uid, email)
                userDao.insertUser(user) // Guarda en Room
                firestore.collection("users").document(user.uid).set(user).await() // Guarda en Firestore
                user
            }
        } catch (e: Exception) {
            null
        }
    }

    /**
     * Obtener usuario autenticado localmente
     */
    suspend fun getLocalUser(): User? {
        val firebaseUser = auth.currentUser
        return firebaseUser?.let { userDao.getUserById(it.uid) }
    }

    /**
     * Cerrar sesión y limpiar usuario local
     */
    suspend fun logoutUser() {
        userDao.insertUser(User("", "")) // Borra usuario local
        auth.signOut() // Cierra sesión en Firebase
    }
}
