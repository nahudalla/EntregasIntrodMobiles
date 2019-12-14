package ar.com.nahueldallacamina.entregasintrodmobiles

import android.app.IntentService
import android.content.Intent
import android.content.Context

private const val ACTION_SLEEP_AND_RETURN_NUMBER = "ar.com.nahueldallacamina.entregasintrodmobiles.action.SLEEP_AND_RETURN_NUMBER"
private const val EXTRA_NUMBER = "ar.com.nahueldallacamina.entregasintrodmobiles.extra.NUMBER"

class Entregable4IntentService : IntentService("Entregable4IntentService") {
    override fun onHandleIntent(intent: Intent?) {
        when (intent?.action) {
            ACTION_SLEEP_AND_RETURN_NUMBER -> {
                val number = intent.getLongExtra(EXTRA_NUMBER, 0)
                handleActionSleepAndReturnNumber(number)
            }
        }
    }

    private fun handleActionSleepAndReturnNumber(number: Long) {
        Thread.sleep(3000)
        val intent = Intent(NUMBER_BROADCAST_INTENT_SERVICE)
        intent.putExtra("number", number)
        sendBroadcast(intent)
    }

    companion object {
        const val NUMBER_BROADCAST_INTENT_SERVICE = "ar.com.nahueldallacamina.entregasintrodmobiles.broadcast.NUMBER_BROADCAST_INTENT_SERVICE"

        fun startActionSleepAndReturnNumber(context: Context, number: Long) {
            val intent = Intent(context, Entregable4IntentService::class.java).apply {
                action = ACTION_SLEEP_AND_RETURN_NUMBER
                putExtra(EXTRA_NUMBER, number)
            }
            context.startService(intent)
        }
    }
}
