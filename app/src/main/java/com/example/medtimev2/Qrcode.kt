package com.example.medtimev2

import android.graphics.Bitmap
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.qrcode.QRCodeWriter
import kotlinx.datetime.Instant
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.text.SimpleDateFormat
import java.util.*

class Qrcode : AppCompatActivity() {

    private lateinit var qrImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qrcode)

        qrImage = findViewById(R.id.qrimage)

        var record = Record()
        record.name = "Paracetamol"
        record.property = "used to treat fever and mild to moderate pain"
        record.warning = "have liver or kidney problems"
        record.countPerTime = 1.0
        record.timePerDay = 3
        record.direction = Direction.BEFORE_BED
        val beforeBed = Instant.parse("2022-02-01T14:23:05+07:00")
        record.directionDate = DirectionDate(null, null, null, beforeBed)

        val json = Json.encodeToString(record)
        val image = getQrCodeBitmap(json)

        qrImage.setImageBitmap(image)
    }

    fun getQrCodeBitmap(json: String): Bitmap {
        val size = 512 //pixels
        val bits = QRCodeWriter().encode(json, BarcodeFormat.QR_CODE, size, size)
        return Bitmap.createBitmap(size, size, Bitmap.Config.RGB_565).also {
            for (x in 0 until size) {
                for (y in 0 until size) {
                    it.setPixel(x, y, if (bits[x, y]) Color.BLACK else Color.WHITE)
                }
            }
        }
    }
}