package ar.com.nahueldallacamina.entregasintrodmobiles

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_entregable3.*
import kotlin.properties.Delegates

class Entregable3 : AppCompatActivity() {
    private companion object {
        private const val PREFERENCES_COUNTER_KEY = "counter"
        private const val PREFERENCES_STEP_KEY = "step"
        private const val DEFAULT_COUNTER_VALUE = 0L
        private const val DEFAULT_STEP_TIMEOUT_VALUE = 1000L
    }

    private var running : Boolean by Delegates.observable(false) {_, _, running ->
        if (running) this.run()
        else this.stop()
    }
    var counter : Long by Delegates.observable(0L) {_, _, counter ->
        entregable3Counter.text = counter.toString()
    }
    var timeout : Long = 0L
    private var asyncTask : Entregable3IncrementCounterTask? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entregable3)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Entregable 3"

        val preferences = getPreferences(Context.MODE_PRIVATE)

        this.running = false
        this.counter = preferences.getLong(PREFERENCES_COUNTER_KEY, DEFAULT_COUNTER_VALUE)
        this.timeout = preferences.getLong(PREFERENCES_STEP_KEY, DEFAULT_STEP_TIMEOUT_VALUE)

        entregable3StepTimeText.setText(this.timeout.toString())

        entregable3StartStopButton.setOnClickListener {
            this.running = !this.running
        }
        entregable3ResetButton.setOnClickListener {
            this.counter = 0
        }
    }

    override fun onPause() {
        super.onPause()

        with (getPreferences(Context.MODE_PRIVATE).edit()) {
            putLong(PREFERENCES_COUNTER_KEY, this@Entregable3.counter)
            putLong(PREFERENCES_STEP_KEY, this@Entregable3.entregable3StepTimeText.text.toString().toLong())
            apply()
        }
    }

    private fun run () {
        this.timeout = entregable3StepTimeText.text.toString().toLong()

        entregable3StartStopButton.text = getString(R.string.stop)
        entregable3StepTimeText.isEnabled = false
        asyncTask = Entregable3IncrementCounterTask(this)
        asyncTask!!.execute()
    }

    private fun stop () {
        entregable3StartStopButton.text = getString(R.string.start)
        entregable3StepTimeText.isEnabled = true
        asyncTask?.cancel(true)
    }
}
