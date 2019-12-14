package ar.com.nahueldallacamina.entregasintrodmobiles

import android.os.AsyncTask
import java.lang.ref.WeakReference

class Entregable3IncrementCounterTask(entregable3: Entregable3) : AsyncTask<Void, Void, Unit>() {
    private val activityReference = WeakReference(entregable3)

    private val activity : Entregable3?
        get() {
            val activity = this.activityReference.get()

            if (activity == null || activity.isFinishing) return null

            return activity
        }

    override fun doInBackground(vararg params: Void?) {
        while (true) {
            with (activity) {
                if (this == null) return

                Thread.sleep(timeout)
            }

            this.publishProgress()
        }
    }

    override fun onProgressUpdate(vararg values: Void?) {
        super.onProgressUpdate(*values)

        with (activity) {
            if (this == null) return

            counter++
        }
    }
}
