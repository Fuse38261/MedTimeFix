package com.example.medtimev2

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.qrcode.QRCodeWriter
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.*


class Qrcode : AppCompatActivity() {

    private lateinit var qrIV: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qrcode)

        // Get view from IDs
        qrIV = findViewById(R.id.qrimage)

        // Get Record from intent
        val intent = intent
        val record = intent.getParcelableExtra<Record>(Qrcode.RECORD_INTENT_NAME)



        try {
            val json = Json.encodeToString(record)
            Log.d(Qrcode.TAG,json)
            val qr_text = getQrCodeBitmap(json)
            qrIV.setImageBitmap(qr_text)
        } catch (err: Error) {
            Log.e(Qrcode.TAG, err.message!!)
        }
    }

    //Generated the Qr-Code
    private fun getQrCodeBitmap(json: String): Bitmap {
        val size = 512 //pixels
        val hints = Hashtable<EncodeHintType,Any>()
        hints[EncodeHintType.CHARACTER_SET] = "UTF-8" // Set the set of the character that get from encoding to UTF-8
        hints[EncodeHintType.ERROR_CORRECTION] = ErrorCorrectionLevel.L // Specifies the level of error correction
        val bits = QRCodeWriter().encode(json, BarcodeFormat.QR_CODE, size, size,hints)
        return Bitmap.createBitmap(size, size, Bitmap.Config.RGB_565).also {
            for (x in 0 until size) {
                for (y in 0 until size) {
                    it.setPixel(x, y, if (bits[x, y]) Color.BLACK else Color.WHITE)
                }
            }
        }
    }

    companion object {
        private val TAG = Qrcode::class.java.simpleName
        const val RECORD_INTENT_NAME = "RECORD"
    }
}




