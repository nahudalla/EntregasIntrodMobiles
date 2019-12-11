package ar.com.nahueldallacamina.entregasintrodmobiles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_entregable3.*
import kotlin.properties.Delegates

class Entregable3 : AppCompatActivity() {
    var running : Boolean by Delegates.observable(false) {_, _, running ->
        if (running) this.run()
        else this.stop()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entregable3)

        this.running = false

        entregable3StartStopButton.setOnClickListener {
            this.running = !this.running
        }
    }

    private fun run () {
        entregable3StartStopButton.text = getString(R.string.stop)
    }

    private fun stop () {
        entregable3StartStopButton.text = getString(R.string.start)
    }
}