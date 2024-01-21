/*
	 *	This content is generated from the API File Info.
	 *	(Alt+Shift+Ctrl+I).
	 *
	 *	@desc 		
	 *	@file 		log_in
	 *	@date 		Wednesday 17th of January 2024 04:06:38 PM
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
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class log_in_activity : Activity() {

    public override fun onStart() {
        super.onStart()
        val auth = FirebaseAuth.getInstance()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        /*if (currentUser != null) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }*/
    }
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.log_in)
        val auth = FirebaseAuth.getInstance()
        val EditTextEmail = findViewById<EditText>(R.id.Email_Login)
        val EditTextPassword = findViewById<EditText>(R.id.Password_Login)
        val Button_Login = findViewById<Button>(R.id._bg__button_primary_ek1)
        val textView = findViewById<TextView>(R.id.register)

        textView.setOnClickListener {
            val  intent = Intent(this, sign_up_activity::class.java)
            startActivity(intent)
            finish()
        }

        Button_Login.setOnClickListener {
            val Email2 = EditTextEmail.text.toString()
            val Password2 =  EditTextPassword.text.toString()


            if (TextUtils.isEmpty(Email2)){
                Toast.makeText(this, "โปรดกรอกอีเมล", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(Password2)){
                Toast.makeText(this, "โปรดกรอกรหัสผ่าน", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.signInWithEmailAndPassword(Email2, Password2)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            baseContext,
                            "เข้าสู่ระบบเสร็จสิ้น",
                            Toast.LENGTH_SHORT,
                        ).show()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(
                            baseContext,
                            "รหัสผ่านหรืออีเมลไม่ถูกต้อง",
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
                }
        }
    }
}
