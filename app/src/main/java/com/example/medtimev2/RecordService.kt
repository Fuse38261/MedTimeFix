package com.example.medtimev2

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.reflect.typeOf


class RecordService() {
    var records: MutableList<Record> = mutableListOf<Record>()
    var db = FirebaseFirestore.getInstance();
    private val TAG = "RecordService"

    init {
        reloadRecords()
    }

    fun addNewRecord(new: Record) {
        records.add(new)
        val currentUser = FirebaseAuth.getInstance().currentUser;
        if (currentUser === null) {
            Log.d(TAG, "Cannot find user, ignore add record to Firestore")
            return;
        }

        db.collection("users/${currentUser.uid}/records")
            .add(new)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }

    fun reloadRecords() {
        val currentUser = FirebaseAuth.getInstance().currentUser;
        if (currentUser === null) {
            return;
        }
        db.collection("users/${currentUser.uid}/records")
            .get()
            .addOnSuccessListener { result ->
                records = mutableListOf<Record>()
                for (document in result) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                    records.add(Record(document.data))
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }
    }

    companion object {
        var default: RecordService = RecordService()
    }
}