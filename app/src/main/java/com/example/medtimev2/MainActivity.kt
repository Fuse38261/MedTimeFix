package com.example.medtimev2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.text.SimpleDateFormat
import java.util.*


    private var isLoggedIn: Boolean = false

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
        val imageMenu = findViewById<ImageView>(R.id.imageMenu)



        val firstActButton = findViewById<CardView>(R.id.card_receiver)
        firstActButton.setOnClickListener {
            Log.d("receiveBtn","Button has been clicked")
            val intent1 = Intent(this, reciever::class.java)
            startActivity(intent1)
            isLoggedIn = true
        }

        val secondActButton = findViewById<CardView>(R.id.card_payer)
        secondActButton.setOnClickListener {
            val intent2 = Intent(this, payer::class.java)
            startActivity(intent2)
        }


        // Make image to click an open the drawer
        imageMenu.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        // Set the NavigationView menu to change the activity when clicked
        val navView = findViewById<NavigationView>(R.id.naivgationView)
        navView.setNavigationItemSelectedListener{
            when(it.itemId){

                R.id.menu_main -> {
                    val intent3 = Intent(this, MainActivity::class.java)
                    startActivity(intent3)
                    true
                }R.id.menu_itemView -> {

                    if (isLoggedIn == true){
                        val intent4 = Intent(this, RecordListActivity::class.java)
                        startActivity(intent4)
                    }else{
                        Toast.makeText(this, "โปรดทำการสแกน Qr-Code ก่อนดูรายการยา", Toast.LENGTH_SHORT).show()
                    }

                true
                }
                else -> false
            }

        }
        printRecordDeserialized()
    }



    private fun printRecordDeserialized() {
        var record = Record()
        record.name = "Paracetamol"
        record.property = "used to treat fever and mild to moderate pain"
        record.warning = "have liver or kidney problems"
        record.countPerTime = 1.0
        record.timePerDay = 3
        //record.direction = Direction.BEFORE_BED

        val simpleDateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss",Locale.getDefault())
        //val directionDate = DirectionDate()
        //directionDate.beforeBed = Instant.parse("2022-02-01T14:23:05+07:00")

        //record.directionDate = directionDate

        try {
            val json = Json.encodeToString(record)
            Log.d(TAG, json)
        } catch (err: Error) {
            Log.e(TAG, err.message!!)
        }
    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }
}


