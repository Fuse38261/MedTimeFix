/*package com.example.medtimev2

import android.graphics.Bitmap
import android.graphics.Point
import android.os.Bundle
import android.text.TextUtils
import android.view.Display
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidx.appcompat.app.AppCompatActivity

class Qrcode : AppCompatActivity() {

    lateinit var qrIV: ImageView
    lateinit var msgEdt: EditText
    lateinit var generateQRBtn: Button
    lateinit var bitmap: Bitmap
    lateinit var qrEncoder: QRGenerator


    var qrname: String = findViewById<EditText>(R.id.inputname).toString()
    var qrprop: String = findViewById<EditText>(R.id.inputprop).toString()
    var qrwarn: String = findViewById<EditText>(R.id.inputwarn).toString()
    var qrtime: Double = findViewById<EditText>(R.id.inputtime).toString().toDouble()
    var qrpro: Int = findViewById<EditText>(R.id.inputprop).toString().toInt()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qrcode)

        var record = Record()
        record.name = qrname
        record.property = qrprop
        record.warning = qrwarn
        record.countPerTime = qrtime
        record.timePerDay = qrpro

        generateQRBtn = findViewById(R.id.Nexttoqr)
        qrIV = findViewById(R.id.qrimage)
        msgEdt = findViewById(R.id.inputtime)

       /* val json = Json.encodeToString(record)
        val image = getQrCodeBitmap(json)


        qrIV.setImageBitmap(image)*/

        if (TextUtils.isEmpty(msgEdt.text.toString())) {

            // on below line we are displaying toast message to display enter some text
            Toast.makeText(applicationContext, "Enter your message", Toast.LENGTH_SHORT).show()
        } else {
            // on below line we are getting service for window manager
            val windowManager: WindowManager = getSystemService(WINDOW_SERVICE) as WindowManager

            // on below line we are initializing a
            // variable for our default display
            val display: Display = windowManager.defaultDisplay

            // on below line we are creating a variable
            // for point which is use to display in qr code
            val point: Point = Point()
            display.getSize(point)

            // on below line we are getting
            // height and width of our point
            val width = point.x
            val height = point.y

            // on below line we are generating
            // dimensions for width and height
            var dimen = if (width < height) width else height
            dimen = dimen * 3 / 4

            // on below line we are initializing our qr encoder
            qrEncoder = QRGEncoder(msgEdt.text.toString(), null, QRGContents.Type.TEXT, dimen)

            // on below line we are running a try
            // and catch block for initialing our bitmap
            try {
                // on below line we are
                // initializing our bitmap
                bitmap = qrEncoder.encodeAsBitmap()

                // on below line we are setting
                // this bitmap to our image view
                qrIV.setImageBitmap(bitmap)
            } catch (e: Exception) {
                // on below line we
                // are handling exeption
                e.printStackTrace()
            }
        }
    }
}



    /*fun getQrCodeBitmap(json: String): Bitmap {
        val size = 512 //pixels
        val bits = QRCodeWriter().encode(json, BarcodeFormat.QR_CODE, size, size)
        return Bitmap.createBitmap(size, size, Bitmap.Config.RGB_565).also {
            for (x in 0 until size) {
                for (y in 0 until size) {
                    it.setPixel(x, y, if (bits[x, y]) Color.BLACK else Color.WHITE)
                }
            }
        }
    }*/
*/