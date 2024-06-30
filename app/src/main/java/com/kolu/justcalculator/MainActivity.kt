package com.kolu.justcalculator

import android.icu.text.DecimalFormat
import android.os.Bundle
import android.widget.RelativeLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.kolu.justcalculator.databinding.ActivityMainBinding
import com.kolu.justcalculator.databinding.ButtonsLayoutBinding
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    private lateinit var  binding :ActivityMainBinding
    private lateinit var btnBinding: ButtonsLayoutBinding
    private val ADDITION = '+'
    private val SUBTRACTION = '-'
    private val MULTIPLICATION = '*'
    private val DIVISION = '/'
    private val PERCENT = '%'

    private var currentSymbol : Char? = null
    private var firstValue = Double.NaN
    private var secondValue: Double? = null

    private var decimalFormat = DecimalFormat("#.##########")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }

        btnBinding = ButtonsLayoutBinding.bind(binding.root.findViewById<RelativeLayout>(R.id.buttons_layout))

        btnBinding.btn0.setOnClickListener { binding.input.text = binding.input.text.toString() + "0" }
        btnBinding.btn1.setOnClickListener { binding.input.text = binding.input.text.toString() + "1" }
        btnBinding.btn2.setOnClickListener { binding.input.text = binding.input.text.toString() + "2" }
        btnBinding.btn3.setOnClickListener { binding.input.text = binding.input.text.toString() + "3" }
        btnBinding.btn4.setOnClickListener { binding.input.text = binding.input.text.toString() + "4" }
        btnBinding.btn5.setOnClickListener { binding.input.text = binding.input.text.toString() + "5" }
        btnBinding.btn6.setOnClickListener { binding.input.text = binding.input.text.toString() + "6" }
        btnBinding.btn7.setOnClickListener { binding.input.text = binding.input.text.toString() + "7" }
        btnBinding.btn8.setOnClickListener { binding.input.text = binding.input.text.toString() + "8" }
        btnBinding.btn9.setOnClickListener { binding.input.text = binding.input.text.toString() + "9" }
        btnBinding.btnDot.setOnClickListener {
            binding.output.text = decimalFormat.format(firstValue) + "."
            binding.input.text = null;
        }

        btnBinding.btnAdd.setOnClickListener {
            allCalculation()
            currentSymbol = '+'
            binding.output.text = decimalFormat.format(firstValue) + "+"
            binding.input.text = null;
        }
        btnBinding.btnMinus.setOnClickListener {
            allCalculation()
            currentSymbol = '-'
            binding.output.text = decimalFormat.format(firstValue) + "-"
            binding.input.text = null;
        }
        btnBinding.btnMultiply.setOnClickListener {
            allCalculation()
            currentSymbol = '*'
            binding.output.text = decimalFormat.format(firstValue) + "x"
            binding.input.text = null;
        }
        btnBinding.btnDevide.setOnClickListener {
            allCalculation()
            currentSymbol = '/'
            binding.output.text = decimalFormat.format(firstValue) + "÷"
            binding.input.text = null;
        }
        btnBinding.btnPercent.setOnClickListener {
            allCalculation()
            currentSymbol = '%'
            binding.output.text = decimalFormat.format(firstValue) + "%"
            binding.input.text = null;
        }
        btnBinding.btnEqual.setOnClickListener {
            allCalculation()
            binding.output.text = decimalFormat.format(firstValue)
            firstValue = Double.NaN
            binding.input.text = null;
        }
        btnBinding.btnClear.setOnClickListener {
            binding.output.text = null
            binding.input.text = null;
            firstValue = Double.NaN
        }
        btnBinding.btnOff.setOnClickListener { finish() }



    }
    private fun allCalculation(){
        if(!firstValue.isNaN()){

            secondValue = binding.input.text.toString().toDouble()
            binding.input.text = null

            when(currentSymbol){
                ADDITION -> firstValue += secondValue!!
                SUBTRACTION -> firstValue -= secondValue!!
                MULTIPLICATION -> firstValue *= secondValue!!
                DIVISION -> firstValue /= secondValue!!
                PERCENT -> firstValue %= secondValue!!
            }
        }else{
            try {
                firstValue = binding.input.text.toString().toDouble()
            }catch (_: Exception){}
        }
    }
}