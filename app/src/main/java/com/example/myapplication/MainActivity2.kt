package com.example.myapplication
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.EditText
import android.widget.Spinner
import android.widget.ArrayAdapter



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize the Spinner with operations
        val operations = arrayOf("Addition", "Subtraction", "Multiplication", "Division", "Modulus")
        val spinner: Spinner = findViewById(R.id.operationsSpinner)
        spinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, operations)

        val calculateButton: Button = findViewById(R.id.calculateButton)
        calculateButton.setOnClickListener {
            performCalculation()
        }
    }

    private fun performCalculation() {
        val operandOneEditText: EditText = findViewById(R.id.operandOneEditText)
        val operandTwoEditText: EditText = findViewById(R.id.operandTwoEditText)
        val resultTextView: TextView = findViewById(R.id.resultTextView)
        val spinner: Spinner = findViewById(R.id.operationsSpinner)

        val operandOne = operandOneEditText.text.toString().toDoubleOrNull()
        val operandTwo = operandTwoEditText.text.toString().toDoubleOrNull()

        if (operandOne == null || operandTwo == null) {
            resultTextView.text = "Please enter valid numbers."
            return
        }

        val result = when (spinner.selectedItem.toString()) {
            "Addition" -> operandOne + operandTwo
            "Subtraction" -> operandOne - operandTwo
            "Multiplication" -> operandOne * operandTwo
            "Division" -> if (operandTwo != 0.0) operandOne / operandTwo else null
            "Modulus" -> operandOne % operandTwo
            else -> null
        }

        if (result == null && spinner.selectedItem.toString() == "Division") {
            resultTextView.text = "Divide by Zero not allowed."
        } else {
            resultTextView.text = "Result: $result"
        }
    }
}
