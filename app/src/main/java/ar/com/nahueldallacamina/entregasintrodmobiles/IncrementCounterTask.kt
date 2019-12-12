package ar.com.nahueldallacamina.entregasintrodmobiles

import android.os.AsyncTask

class IncrementCounterTask : AsyncTask<Long, Void, Long?>() {
    override fun doInBackground(vararg params: Long?): Long? {
        val nextNumber = params[0]?.plus(1)

        params[1]?.let { Thread.sleep(it) }

        return nextNumber
    }
}