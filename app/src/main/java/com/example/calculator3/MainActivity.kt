package com.example.calculator3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button0.setOnClickListener { setTextFields("0") }
        button1.setOnClickListener { setTextFields("1") }
        button2.setOnClickListener { setTextFields("2") }
        button3.setOnClickListener { setTextFields("3") }
        button4.setOnClickListener { setTextFields("4") }
        button5.setOnClickListener { setTextFields("5") }
        button6.setOnClickListener { setTextFields("6") }
        button7.setOnClickListener { setTextFields("7") }
        button8.setOnClickListener { setTextFields("8") }
        button9.setOnClickListener { setTextFields("9") }
        comma.setOnClickListener { setTextFields(",") }
        plus.setOnClickListener { setTextFields("+") }
        minus.setOnClickListener { setTextFields("-") }
        multiply.setOnClickListener { setTextFields("*") }
        divide.setOnClickListener { setTextFields("/") }
        backspace.setOnClickListener {
            val str = math_operation.text.toString()
            if (str.isNotEmpty()){
                math_operation.text = str.substring(0,str.length - 1)

                resultBTN.text = ""
            }


        }

        equal.setOnClickListener {
            try {
                val ex = ExpressionBuilder(math_operation.text.toString()).build()
                val result = ex.evaluate()

                val longRes = result.toLong()
                if(result == longRes.toDouble()){
                    resultBTN.text = longRes.toString()
                }
                else {resultBTN.text = result.toString()}



            }
            catch(e:Exception){
                Log.d("Error", "Message ${e.message}")

            }
        }
    }

    fun setTextFields(str: String){

        math_operation.append(str)

        if (resultBTN.text != ""){
            math_operation.text = resultBTN.text
            math_operation.append(str)
            resultBTN.text = ""

        }
    }

}