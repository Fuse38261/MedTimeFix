/*
	 *	This content is generated from the API File Info.
	 *	(Alt+Shift+Ctrl+I).
	 *
	 *	@desc 		
	 *	@file 		sign_up
	 *	@date 		Tuesday 16th of January 2024 04:36:52 AM
	 *	@title 		Page 1
	 *	@author 	
	 *	@keywords 	
	 *	@generator 	Export Kit v1.3.figma
	 *
	 */
package com.example.medtimev2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class sign_up_activity : Activity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up)
        //val Username = findViewById<EditText>(R.id.Username).toString()
        val records = RecordService.default.records
        val EditTextEmail = findViewById<EditText>(R.id.Email)
        val EditTextPassword = findViewById<EditText>(R.id.Password)
        val Button_Register = findViewById<Button>(R.id._bg__button_primary_ek1)
        val textView = findViewById<TextView>(R.id.login)
        val auth = FirebaseAuth.getInstance()

        textView.setOnClickListener {
            val  intent = Intent(this, log_in_activity::class.java)
            startActivity(intent)
            finish()
        }

        Button_Register.setOnClickListener {
            val Email = EditTextEmail.text.toString()
            val Password =  EditTextPassword.text.toString()

            if (TextUtils.isEmpty(Email)){
                Toast.makeText(this , "โปรดกรอกอีเมล",Toast.LENGTH_SHORT).show()
            }
            if (TextUtils.isEmpty(Password)){
                Toast.makeText(this , "โปรดกรอกรหัสผ่าน",Toast.LENGTH_SHORT).show()
            }


            auth.createUserWithEmailAndPassword(Email, Password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("Sign_in_Success", "createUserWithEmail:success")
                        val user = auth.currentUser
                        Toast.makeText(
                            baseContext,
                            "ลงชื่อเข้าใข้สำเร็จ",
                            Toast.LENGTH_SHORT,
                        ).show()



                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("Sign_in_Failed", "createUserWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext,
                            "Authentication failed.",
                            Toast.LENGTH_SHORT,
                        ).show()

                    }
                }
        }
    }
}
