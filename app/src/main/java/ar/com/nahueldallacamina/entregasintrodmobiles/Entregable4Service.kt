package ar.com.nahueldallacamina.entregasintrodmobiles

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.*
import android.widget.Toast

private const val EXTRA_NUMBER = "number"

class Entregable4Service : Service() {
    lateinit var looper: Looper
    lateinit var serviceHandler: Handler

    override fun onCreate() {
        val handlerThread = HandlerThread("Entregable4Service", Process.THREAD_PRIORITY_BACKGROUND)
        handlerThread.start()

        looper = handlerThread.looper
        serviceHandler = Handler(looper) { msg: Message ->
            val number = msg.data.getLong(EXTRA_NUMBER)

            Thread.sleep(3000)
            val intent = Intent(NUMBER_BROADCAST_SERVICE)
            intent.putExtra(EXTRA_NUMBER, number)
            sendBroadcast(intent)

            stopSelf(msg.arg1)
            true
        }

        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent == null) return START_NOT_STICKY

        val msg = serviceHandler.obtainMessage()
        msg.arg1 = startId
        msg.data = Bundle().apply {
            putLong(EXTRA_NUMBER, intent.getLongExtra(EXTRA_NUMBER, 0))
        }

        serviceHandler.sendMessage(msg)

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    companion object {
        const val NUMBER_BROADCAST_SERVICE = "ar.com.nahueldallacamina.entregasintrodmobiles.broadcast.NUMBER_BROADCAST_SERVICE"

        fun startActionSleepAndReturnNumber(context: Context, number: Long) {
            val intent = Intent(context, Entregable4Service::class.java).apply {
                putExtra(EXTRA_NUMBER, number)
            }
            context.startService(intent)
        }
    }
}
