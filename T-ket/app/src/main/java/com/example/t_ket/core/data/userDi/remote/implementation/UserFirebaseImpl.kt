package com.example.t_ket.core.data.userDi.remote.implementation
import android.util.Log
import com.example.t_ket.core.data.AppData
import com.google.firebase.firestore.FirebaseFirestore
import com.example.t_ket.core.data.userDi.remote.repository.UserRemote
import com.example.t_ket.core.domain.model.Event
import com.example.t_ket.core.domain.model.User
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await

class UserFirebaseImpl(): UserRemote {
    private val firestore = FirebaseFirestore.getInstance()
    private val storage = FirebaseStorage.getInstance()

    val db = FirebaseFirestore.getInstance()

    override suspend fun isStaff(EventId: String, StaffCode: String): Boolean {
        try {
            val documentSnapshot = firestore.collection("Users").document("Users").get().await()

            if (documentSnapshot.exists()) {

                val userData = documentSnapshot.data
                if (userData != null) {
                    val usersMap = userData["Users"] as? Map<String, Any> ?: emptyMap()

                    for ((key, userMap) in usersMap) {
                        val userFields = userMap as? Map<String, Any> ?: emptyMap()

                        // Verificar si los campos "codeOfEvent" y "codeOfStaff" coinciden
                        val codeOfEvent = userFields["codeOfEvent"] as? String
                        val codeOfStaff = userFields["codeOfStaff"] as? String
                        if (codeOfEvent == EventId && codeOfStaff == StaffCode) {
                            Log.d("GGGGGGGGGGGGGGGGGGG", "Usuario encontrado en el mapa $key del documento ${documentSnapshot.id}")
                            return true
                        }
                    }
                }
            } else {
                Log.d("KKKKKKKKKK", "El documento 'Users' no existe")
            }
        } catch (e: Exception) {
            Log.e("KKKKKKKKKK", "Error al obtener el documento 'Users': $e")
        }

        Log.d("KKKKKKKKKK", "Usuario no encontrado")
        return false
    }
    override suspend fun isAdmin(EventId: String, StaffCode: String): Boolean? {
        try {
            val documentSnapshot = firestore.collection("Users").document("Users").get().await()

            if (documentSnapshot.exists()) {

                val userData = documentSnapshot.data
                if (userData != null) {
                    val usersMap = userData["Users"] as? Map<String, Any> ?: emptyMap()

                    for ((key, userMap) in usersMap) {
                        val userFields = userMap as? Map<String, Any> ?: emptyMap()

                        // Verificar si los campos "codeOfEvent" y "codeOfStaff" coinciden
                        val codeOfEvent = userFields["codeOfEvent"] as? String
                        val codeOfStaff = userFields["codeOfStaff"] as? String
                        val admin = userFields["admin"] as? Boolean

                        if (codeOfEvent == EventId && codeOfStaff == StaffCode) {
                            Log.d("GGGGGGGGGGGGGGGGGGG", "Usuario encontrado en el mapa $key del documento ${documentSnapshot.id}")
                            Log.d("GGGGGGGGGGGGGGGGGGG", "el admin es  $admin")

                            return admin
                        }
                    }
                }
            } else {
                Log.d("KKKKKKKKKK", "El documento 'Users' no existe")
            }
        } catch (e: Exception) {
            Log.e("KKKKKKKKKK", "Error al obtener el documento 'Users': $e")
        }

        Log.d("KKKKKKKKKK", "Usuario no encontrado")
        return false
    }
    override suspend fun getUserInfo(): User {
        val userInfo = User()

        try {
            val documentSnapshot = firestore.collection("Users").document("Users").get().await()

            if (documentSnapshot.exists()) {
                val userData = documentSnapshot.data
                if (userData != null) {
                    val usersMap = userData["Users"] as? Map<String, Any> ?: emptyMap()

                    for ((_, userFields) in usersMap) {
                        val userFieldsMap = userFields as? Map<String, Any> ?: emptyMap()
                        // Verificar si los campos "codeOfEvent" y "codeOfStaff" coinciden
                        val codeOfEvent = userFieldsMap["codeOfEvent"] as? String
                        val codeOfStaff = userFieldsMap["codeOfStaff"] as? String

                        if (AppData.code == codeOfStaff) {

                            userInfo.codeOfEvent = userFieldsMap["codeOfEvent"] as? String
                            userInfo.codeOfStaff = userFieldsMap["codeOfStaff"] as? String
                            userInfo.name = userFieldsMap["Name"] as? String
                            userInfo.gmail = userFieldsMap["gmail"] as? String
                            val storageRef = userFieldsMap["image"] as? String
                            val url = storageRef?.let { storage.getReferenceFromUrl(it).downloadUrl.await().toString() }
                            userInfo.image = url


                            // Agrega los atributos adicionales según sea necesario
                            return userInfo
                        }

                    }
                }
            } else {
                Log.d("KKKKKKKKKK", "El documento 'Users' no existe")
            }
        } catch (e: Exception) {
            Log.e("KKKKKKKKKK", "Error al obtener el documento 'Users': $e")
        }

        return userInfo
    }


}