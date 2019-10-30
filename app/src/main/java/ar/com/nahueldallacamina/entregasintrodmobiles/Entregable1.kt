package ar.com.nahueldallacamina.entregasintrodmobiles

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_entregable1.*
import kotlin.properties.Delegates


class Entregable1 : AppCompatActivity() {
    private var counter : Int by Delegates.observable(0) { _, _, newValue ->
        counterTextView.text = newValue.toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entregable1)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        counterTextView.text = this.counter.toString()

        buttonAdd.setOnClickListener { this.counter++ }
        buttonSubtract.setOnClickListener { this.counter-- }
        resetButton.setOnClickListener { this.counter = 0 }
    }
}
