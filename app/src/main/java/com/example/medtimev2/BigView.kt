package com.example.medtimev2

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.bumptech.glide.Glide
import com.google.android.material.navigation.NavigationView
import java.util.*

class BigView : AppCompatActivity() {

    lateinit var tts: TextToSpeech
    private lateinit var medImage: ImageView

    @SuppressLint("CutPasteId", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_big_view)
        // Import information from record
        val intent = getIntent()
        val recordIndex = intent.getIntExtra(BigView.RECORD_INDEX_INTENT_NAME, -1)

        val record = RecordService.default.records[recordIndex]

        val name: TextView = findViewById(R.id.viewname)
        val time: TextView = findViewById(R.id.viewtime)
        val med: TextView = findViewById(R.id.viewmed)
        val warn: TextView = findViewById(R.id.viewwarning)
        val prop: TextView = findViewById(R.id.viewprop)
        val meal: TextView = findViewById(R.id.viewMeal)

        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout2)
        val imageMenu = findViewById<ImageView>(R.id.imageMenu2)

        name.text = record.name
        prop.text = record.property
        warn.text  = record.warning
        med.text = record.timePerDay.toString()
        time.text = record.countPerTime.toString()
        meal.text = record.timeMeal

        // Make image to click an open the drawer
        imageMenu.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        // Set the NavigationView menu to change the activity when clicked
        val navView = findViewById<NavigationView>(R.id.naivgationView)
        navView.setNavigationItemSelectedListener{
            when(it.itemId){

                R.id.menu_main -> {

                    val intent5 = Intent(this, MainActivity::class.java)
                    startActivity(intent5)
                    true
                }R.id.menu_itemView -> {

                    val intent6 = Intent(this, RecordListActivity::class.java)
                    startActivity(intent6)
                    true
            }
                else -> false
            }

        }


        // Btn for select time
        val selecttime = findViewById<Button>(R.id.btntime)
        selecttime.setOnClickListener {
            val Intent = Intent(this,clockforbandaf::class.java)
            startActivity(Intent)
        }

       val imageBack = findViewById<ImageView>(R.id.imageBack)
        imageBack.setOnClickListener {
            val Intent2 = Intent(this,RecordListActivity::class.java)
            startActivity(Intent2)
        }

        medImage = findViewById(R.id.medImageView)

        // Set up click listener for button to select image
        findViewById<Button>(R.id.selectImageBtn).setOnClickListener {
            Log.d("MyActivity", "Select image button has been clicked.")
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(
                Intent.createChooser(intent, "Select Picture"),
                BigView.PICK_IMAGE_REQUEST
            )
        }

        // Text-To-Speech
        val bl1 = findViewById<ImageButton>(R.id.ttsBtn1)

        // Set Btn to react when clicked
        bl1.setOnClickListener{
            tts = TextToSpeech(applicationContext, TextToSpeech.OnInitListener {
                if(it==TextToSpeech.SUCCESS) {
                    tts.language = Locale.US
                    tts.setSpeechRate(1.0f)
                    tts.speak(name.text.toString(), TextToSpeech.QUEUE_ADD, null)
                }
            })
        }
    }

    companion object {
        const val RECORD_INDEX_INTENT_NAME = "RECORD_INDEX"
        private const val PICK_IMAGE_REQUEST = 1
    }

    // Function for load up the image
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.data != null) {
            val uri = data.data

            Glide.with(this).load(uri).into(medImage)
        }
    }
}