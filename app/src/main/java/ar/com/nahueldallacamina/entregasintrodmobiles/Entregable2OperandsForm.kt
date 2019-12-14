package ar.com.nahueldallacamina.entregasintrodmobiles

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_entregable2_operands_form.*
import java.lang.Double.parseDouble
import java.lang.NumberFormatException
import java.util.function.BiFunction

class Entregable2OperandsForm : AppCompatActivity() {
    companion object {
        const val OPERATION_KEY = "OPERATION"
        const val OPERATION_SUM = "SUM"
        const val OPERATION_SUBTRACTION = "SUBTRACTION"
        const val OPERATION_MULTIPLICATION = "MULTIPLICATION"
        const val OPERATION_DIVISION = "DIVISION"
        const val OPERATION_RESULT_KEY = "RESULT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entregable2_operands_form)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Entregable 2: Operandos"

        setOperationText()
        entregable2ButtonResult.setOnClickListener { returnOperationFromIntent() }
    }

    private fun setOperationText () {
        when (intent.extras!![OPERATION_KEY]) {
            OPERATION_SUM -> entregable2OperationText.text = getString(R.string.suma)
            OPERATION_SUBTRACTION -> entregable2OperationText.text = getString(R.string.resta)
            OPERATION_MULTIPLICATION -> entregable2OperationText.text = getString(R.string.multiplicacion)
            OPERATION_DIVISION -> entregable2OperationText.text = getString(R.string.division)
            else -> cancelOperation()
        }
    }

    private fun returnOperationFromIntent () {
        when (intent.extras!![OPERATION_KEY]) {
            OPERATION_SUM -> returnOperationResult { a, b -> a + b }
            OPERATION_SUBTRACTION -> returnOperationResult { a, b -> a - b }
            OPERATION_MULTIPLICATION -> returnOperationResult { a, b -> a * b }
            OPERATION_DIVISION -> returnOperationResult { a, b -> a / b }
            else -> cancelOperation()
        }
    }

    private fun returnOperationResult (function: (Double, Double) -> Double) {
        val operand1 : Double
        val operand2 : Double

        try {
            operand1 = parseDouble(entregable2Operand1.text.toString())
            operand2 = parseDouble(entregable2Operand2.text.toString())
        } catch (e : NumberFormatException) {
            Toast.makeText(this, "Operandos inválidos", Toast.LENGTH_LONG).show()
            return
        }

        val resultIntent = Intent()
        resultIntent.putExtra(OPERATION_RESULT_KEY, function(operand1, operand2))
        setResult(RESULT_OK, resultIntent)
        finish()
    }

    private fun cancelOperation () {
        setResult(Activity.RESULT_CANCELED)
        finish()
    }
}
