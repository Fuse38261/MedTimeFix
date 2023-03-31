package com.example.medtimev2

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView

class RecordListActivity : AppCompatActivity(){

    @SuppressLint("MissingInflatedId", "WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record_list)

        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
        val imageMenu2 = findViewById<ImageView>(R.id.med)

        // Make image to click an open the drawer
        imageMenu2.setOnClickListener {
            Log.d("NavBtn","Nav has been clicked")
            drawerLayout.openDrawer(GravityCompat.START)
        }

        // Set the NavigationView menu to change the activity when clicked
        val navView = findViewById<NavigationView>(R.id.navigationView3)
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

        val recyclerView = findViewById<RecyclerView>(R.id.rvTodoItems)
        val records = RecordService.default.records

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = TodoAdapter(records) {
            val intent = Intent(this, BigView::class.java)
            intent.putExtra(BigView.RECORD_INDEX_INTENT_NAME, it)
            startActivity(intent)
        }


    }
}