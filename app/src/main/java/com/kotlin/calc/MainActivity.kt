package com.kotlin.calc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        }

    private var lastNumeric : Boolean = false
    private var lastDot : Boolean = false

    fun onDigit(view: View){
        val inputText2 = findViewById<TextView>(R.id.inputText2)
        inputText2.append((view as Button).text)
        lastNumeric = true
    }

    fun clearAll(view: View){
        val inputText2 = findViewById<TextView>(R.id.inputText2)
        val inputText1 = findViewById<TextView>(R.id.inputText1)
        inputText2.text = ""
        inputText1.text = ""
        lastDot = false
        lastNumeric = false

    }
    fun onDecimalPoint(view: View){
        if(lastNumeric && !lastDot){
            val inputText2 = findViewById<TextView>(R.id.inputText2)
            inputText2.append(".")
            lastNumeric = false
            lastDot = true
        }
    }
    fun onOperator(view: View){
        val inputText2 = findViewById<TextView>(R.id.inputText2)
        if (lastNumeric && !isOperatorAdded(inputText2.text.toString())){
            inputText2.append((view as Button).text)
            lastNumeric = false
            lastDot = false
        }
    }
    private fun isOperatorAdded(value:String) :Boolean{
        return if (value.startsWith("-")){
            false
        }else{
            value.contains("/") || value.contains("*") || value.contains("-") || value.contains("+")
        }
    }
    fun onBackspace(view: View){
        val inputText2 = findViewById<TextView>(R.id.inputText2)
        if (inputText2.text.isNotBlank()){
            inputText2.text = inputText2.text.subSequence(0,inputText2.text.length-1)
        }
    }
    fun onEquals(view: View){
        val inputText2 = findViewById<TextView>(R.id.inputText2)
        val inputText1 = findViewById<TextView>(R.id.inputText1)
        inputText1.text = inputText2.text
        if(lastNumeric){
            if(inputText2.text.contains("-")){
                val splitValue = inputText2.text.split("-")
                val num1 = splitValue[0]
                val num2 = splitValue[1]
                inputText2.text = (num1.toDouble()-num2.toDouble()).toString()
            }
            else if(inputText2.text.contains("+")){
                val splitValue = inputText2.text.split("+")
                val num1 = splitValue[0]
                val num2 = splitValue[1]
                inputText2.text = (num1.toDouble()+num2.toDouble()).toString()
            }
            else if(inputText2.text.contains("*")){
                val splitValue = inputText2.text.split("*")
                val num1 = splitValue[0]
                val num2 = splitValue[1]
                inputText2.text = (num1.toDouble()*num2.toDouble()).toString()
            }
            else{
                val splitValue = inputText2.text.split("/")
                val num1 = splitValue[0]
                val num2 = splitValue[1]
                inputText2.text = (num1.toDouble()/num2.toDouble()).toString()
            }

        }
    }
}