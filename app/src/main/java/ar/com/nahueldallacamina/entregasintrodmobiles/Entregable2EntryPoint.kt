package ar.com.nahueldallacamina.entregasintrodmobiles

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_entregable1.*
import kotlinx.android.synthetic.main.activity_entregable2_entry_point.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.properties.Delegates

class Entregable2EntryPoint : AppCompatActivity() {
    companion object {
        const val DO_OPERATION_REQUEST = 1
    }

    private var operationResult : Double by Delegates.observable(0.0) { _, _, newValue ->
        entregable2Result.text = newValue.toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entregable2_entry_point)

        entregable2ButtonPlus.setOnClickListener {
            this.goToOperandsForm(Entregable2OperandsForm.OPERATION_SUM)
        }

        entregable2ButtonSubtract.setOnClickListener {
            this.goToOperandsForm(Entregable2OperandsForm.OPERATION_SUBTRACTION)
        }

        entregable2ButtonMultiply.setOnClickListener {
            this.goToOperandsForm(Entregable2OperandsForm.OPERATION_MULTIPLICATION)
        }

        entregable2ButtonDivide.setOnClickListener {
            this.goToOperandsForm(Entregable2OperandsForm.OPERATION_DIVISION)
        }
    }

    fun goToOperandsForm (operation : String) {
        val intent = Intent(this, Entregable2OperandsForm::class.java)
        intent.putExtra(Entregable2OperandsForm.OPERATION_KEY, operation)
        startActivityForResult(intent, DO_OPERATION_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == DO_OPERATION_REQUEST) {
            if (resultCode != Activity.RESULT_OK) {
                entregable2Result.text = getString(R.string.errorText)
            } else {
                operationResult =
                    data!!.extras!![Entregable2OperandsForm.OPERATION_RESULT_KEY] as Double
            }

            return
        }

        super.onActivityResult(requestCode, resultCode, data)
    }
}
