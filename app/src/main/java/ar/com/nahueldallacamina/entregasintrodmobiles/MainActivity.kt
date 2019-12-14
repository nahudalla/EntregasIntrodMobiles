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
            val intent = Intent(this, Entregable1::class.java)
            startActivity(intent)
        }

        entregableDosButton.setOnClickListener {
            val intent = Intent(this, Entregable2EntryPoint::class.java)
            startActivity(intent)
        }

        entregableTresButton.setOnClickListener {
            val intent = Intent(this, Entregable3::class.java)
            startActivity(intent)
        }

        entregableCuatroButton.setOnClickListener {
            val intent = Intent(this, Entregable4::class.java)
            startActivity(intent)
        }
    }
}
