package ar.com.nahueldallacamina.entregasintrodmobiles

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import java.util.*

class Entregable4BoundService : Service() {
    private val binder = LocalBinder()
    private val generator = Random()

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    inner class LocalBinder : Binder() {
        fun getService (): Entregable4BoundService {
            return this@Entregable4BoundService
        }
    }

    fun getRandomNumber () : Int {
        return generator.nextInt(100)
    }
}
