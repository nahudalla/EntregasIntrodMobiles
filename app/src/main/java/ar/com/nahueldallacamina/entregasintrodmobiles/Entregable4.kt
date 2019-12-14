package ar.com.nahueldallacamina.entregasintrodmobiles

import android.content.*
import android.os.Bundle
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_entregable4.*
import kotlin.properties.Delegates

class Entregable4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entregable4)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Entregable 4"

        setupIntentService(savedInstanceState)
        setupService(savedInstanceState)
        setupBoundService(savedInstanceState)
    }


    // INTENT SERVICE

    private var intentServiceReceivedNumbers : String by Delegates.observable("") { _, _, newValue ->
        entregable4IntentServiceText.text = newValue
    }

    private lateinit var intentServiceBroadcastReceiver : Entregable4NumberBroadcastReceiver

    private fun setupIntentService (savedInstanceState: Bundle?) {
        this.intentServiceReceivedNumbers = savedInstanceState?.getString("intentServiceText") ?: ""

        intentServiceBroadcastReceiver = Entregable4NumberBroadcastReceiver {
                number -> intentServiceReceivedNumbers += "$number "
        }
        registerReceiver(
            intentServiceBroadcastReceiver,
            IntentFilter(Entregable4IntentService.NUMBER_BROADCAST_INTENT_SERVICE)
        )

        entregable4IntentServiceButton.setOnClickListener {
            for (i in 0L..3L) {
                Entregable4IntentService.startActionSleepAndReturnNumber(this, i)
            }
        }
    }

    // SERVICE

    private var serviceReceivedNumbers : String by Delegates.observable("") { _, _, newValue ->
        entregable4ServiceText.text = newValue
    }

    private lateinit var serviceBroadcastReceiver : Entregable4NumberBroadcastReceiver

    private fun setupService (savedInstanceState: Bundle?) {
        this.serviceReceivedNumbers = savedInstanceState?.getString("serviceText") ?: ""

        serviceBroadcastReceiver = Entregable4NumberBroadcastReceiver {
                number -> serviceReceivedNumbers += "$number "
        }
        registerReceiver(
            serviceBroadcastReceiver,
            IntentFilter(Entregable4Service.NUMBER_BROADCAST_SERVICE)
        )

        entregable4ServiceButton.setOnClickListener {
            for (i in 0L..3L) {
                Entregable4Service.startActionSleepAndReturnNumber(this, i)
            }
        }
    }

    // BOUND SERVICE
    private var boundServiceText : String by Delegates.observable("") { _, _, newValue ->
        entregable4BoundServiceText.text = newValue
    }
    private lateinit var boundService : Entregable4BoundService
    private var bound = false
    private val boundServiceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            bound = false
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            boundService = (service as Entregable4BoundService.LocalBinder).getService()
            bound = true
        }
    }

    override fun onStart() {
        super.onStart()

        val intent = Intent(this, Entregable4BoundService::class.java)
        bindService(intent, boundServiceConnection, Context.BIND_AUTO_CREATE)
    }

    override fun onStop() {
        super.onStop()

        if (bound) {
            unbindService(boundServiceConnection)
            bound = false
        }
    }

    private fun setupBoundService (savedInstanceState: Bundle?) {
        this.boundServiceText = savedInstanceState?.getString("boundServiceText") ?: ""

        entregable4BoundServiceButton.setOnClickListener {
            if (bound) {
                this.boundServiceText = boundService.getRandomNumber().toString()
            }
        }
    }

    override fun onPause() {
        super.onPause()

        unregisterReceiver(this.intentServiceBroadcastReceiver)
        unregisterReceiver(this.serviceBroadcastReceiver)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString("intentServiceText", this.intentServiceReceivedNumbers)
        outState.putString("serviceText", this.serviceReceivedNumbers)
        outState.putString("boundServiceText", this.boundServiceText)
    }
}
