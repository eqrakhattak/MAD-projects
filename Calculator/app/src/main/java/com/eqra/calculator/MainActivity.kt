package com.eqra.calculator

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.eqra.calculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var lastNumeric: Boolean = false

    private var stateError: Boolean = false

    private var lastDot: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPref = getPreferences(Context.MODE_PRIVATE) ?: return
        val savedResult = sharedPref.getString(getString(R.string.saved_result), "")
        binding.textViewDisplay.text = savedResult

        initListeners()
    }

    private fun initListeners() {

        binding.buttonZero.setOnClickListener { onDigit(resources.getString(R.string._0)) }
        binding.buttonOne.setOnClickListener { onDigit(resources.getString(R.string._1)) }
        binding.buttonTwo.setOnClickListener { onDigit(resources.getString(R.string._2)) }
        binding.buttonThree.setOnClickListener { onDigit(resources.getString(R.string._3)) }
        binding.buttonFour.setOnClickListener { onDigit(resources.getString(R.string._4)) }
        binding.buttonFive.setOnClickListener { onDigit(resources.getString(R.string._5)) }
        binding.buttonSix.setOnClickListener { onDigit(resources.getString(R.string._6)) }
        binding.buttonSeven.setOnClickListener { onDigit(resources.getString(R.string._7)) }
        binding.buttonEight.setOnClickListener { onDigit(resources.getString(R.string._8)) }
        binding.buttonNine.setOnClickListener { onDigit(resources.getString(R.string._9)) }

        binding.buttonDiv.setOnClickListener { onOperator(resources.getString(R.string.div_sign)) }
        binding.buttonMultiplication.setOnClickListener { onOperator(resources.getString(R.string.multiplication_sign)) }
        binding.buttonSubstract.setOnClickListener { onOperator(resources.getString(R.string.substract_sign)) }
        binding.buttonPlus.setOnClickListener { onOperator(resources.getString(R.string.plus_sign)) }

        binding.buttonDot.setOnClickListener { onDecimalPoint() }

        binding.buttonEqual.setOnClickListener { onEqual() }

        binding.buttonClear.setOnClickListener { onClear() }
    }

    private fun onDigit(value: String) {

        if (stateError) {
            binding.textViewDisplay.text = value
            stateError = false
        } else {
            binding.textViewDisplay.append(value)
        }

        lastNumeric = true
    }

    private fun onDecimalPoint() {

        if (lastNumeric && !stateError && !lastDot) {
            binding.textViewDisplay.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    private fun onOperator(value: String) {

        if (lastNumeric && !stateError) {
            binding.textViewDisplay.append(value)
            lastNumeric = false
            lastDot = false
        }
    }

    private fun onClear() {

        this.binding.textViewDisplay.text = ""
        lastNumeric = false
        stateError = false
        lastDot = false

        val sharedPref = getPreferences(Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putString(getString(R.string.saved_result), "")
            apply()
        }
    }

    private fun onEqual() {

        if (lastNumeric && !stateError) {
            val txt = binding.textViewDisplay.text.toString()
            val expression = ExpressionBuilder(txt).build()

            try {
                val result = expression.evaluate()
                binding.textViewDisplay.text = result.toString()
                lastDot = true

                val sharedPref = getPreferences(Context.MODE_PRIVATE) ?: return
                with (sharedPref.edit()) {
                    putString(getString(R.string.saved_result), result.toString())
                    apply()
                }

            } catch (ex: ArithmeticException) {
                binding.textViewDisplay.text = resources.getString(R.string.error)
                stateError = true
                lastNumeric = false
            }
        }
    }
}