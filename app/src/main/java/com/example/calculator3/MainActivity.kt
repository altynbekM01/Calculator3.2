package com.example.calculator3

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.PackageManagerCompat.LOG_TAG
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        button_0.setOnClickListener { setTextFields("0", false) }
        button_1.setOnClickListener { setTextFields("1",false) }
        button_2.setOnClickListener { setTextFields("2",false) }
        button_3.setOnClickListener { setTextFields("3",false) }
        button_4.setOnClickListener { setTextFields("4",false) }
        button_5.setOnClickListener { setTextFields("5",false) }
        button_6.setOnClickListener { setTextFields("6",false) }
        button_7.setOnClickListener { setTextFields("7",false) }
        button_8.setOnClickListener { setTextFields("8",false) }
        button_9.setOnClickListener { setTextFields("9",false) }
        button_comma.setOnClickListener { setTextFields(".",true) }
        button_plus.setOnClickListener { setTextFields("+",true) }
        button_minus.setOnClickListener { setTextFields("-",true) }
        button_multiply.setOnClickListener { setTextFields("*",true) }
        button_divide.setOnClickListener { setTextFields("/",true) }
        button_backspase.setOnClickListener {
            val str = top_text.text.toString()
            if (str.isNotEmpty()){
                top_text.text = str.substring(0,str.length - 1)

                buttom_text.text = ""
            }


        }

        button_equal.setOnClickListener {
            try {
                val ex = ExpressionBuilder(top_text.text.toString()).build()
                val  result = ex.evaluate()

                val longRes = result.toLong()
                if(result == longRes.toDouble()){
                    buttom_text.text = longRes.toString()
                }
                else {buttom_text.text = result.toString()}



            }
            catch(e:Exception){
                Log.d("Error", "Message ${e.message}")

            }
        }
    }

    fun setTextFields(str: String, isOperation: Boolean){
        val distinct = top_text.text.toSet().toList();//для зашиты от нулей вначале
        if(top_text.text.isEmpty() && str == "0"){
            top_text.append(str)
        }


        else if((distinct.count() == 1 && distinct[0]=='0') && str == "0"){}


        else if(isOperation == true && (top_text.text.isEmpty() || top_text.text.last() == '*' || top_text.text.last() == '/' || top_text.text.last() == '+' || top_text.text.last() == '-' || top_text.text.last() == '.')){

        }
        else{ top_text.append(str)}

        if (buttom_text.text != ""){
            top_text.text = buttom_text.text
            top_text.append(str)
            buttom_text.text = ""

        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("top_text", top_text.text.toString())
        outState.putString("buttom_text", buttom_text.text.toString())
        
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        top_text.text = savedInstanceState.getString("top_text").toString()
        buttom_text.text = savedInstanceState.getString("buttom_text").toString()
        
    }
    

}

