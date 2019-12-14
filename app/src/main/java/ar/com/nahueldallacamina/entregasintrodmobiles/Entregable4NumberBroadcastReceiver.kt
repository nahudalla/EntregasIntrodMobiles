package ar.com.nahueldallacamina.entregasintrodmobiles

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class Entregable4NumberBroadcastReceiver(handleNumber: (Long) -> Unit) : BroadcastReceiver() {
    val handleNumber = handleNumber

    override fun onReceive(context: Context, intent: Intent) {
        val number = intent.getLongExtra("number", 0)
        handleNumber(number)
    }
}
