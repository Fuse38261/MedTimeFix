package com.example.medtimev2

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity



class SplashScreenActivity : AppCompatActivity() {
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.splash_screen)

        //Animation Variable
        var topAnim: Animation
        var bottomAnim: Animation
        var name: TextView
        var slogan: TextView
        var logo : ImageView




        
        //Use the animation
        topAnim = AnimationUtils.loadAnimation(this,R.animator.top_animation)
        bottomAnim = AnimationUtils.loadAnimation(this,R.animator.bottom_animation)

        //Hooks
        name = findViewById(R.id.textView)
        slogan = findViewById(R.id.textView2)
        logo = findViewById(R.id.imageLogo)

        name.animation = bottomAnim
        slogan.animation = bottomAnim
        logo.animation = topAnim


        //The delay to go to next activity
        Handler().postDelayed({
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            finish()
        }, 5000)






    }
}




