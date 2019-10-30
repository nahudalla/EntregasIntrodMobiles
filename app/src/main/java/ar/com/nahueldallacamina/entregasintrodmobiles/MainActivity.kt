package ar.com.nahueldallacamina.entregasintrodmobiles

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        entregableUnoButton.setOnClickListener {
            val myIntent = Intent(this, Entregable1::class.java)
            startActivity(myIntent)
        }
    }
}
