package com.example.calculatorapp19092022

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.mariuszgromada.math.mxparser.Expression
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
    var lastNumberic:Boolean = false
    var lastDot:Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        clear_all?.setOnClickListener {
            tv_input?.text = ""

        }
        lastNumberic = false
        lastDot
    }

    fun onDigit(view: View) {
        var btnDigit:Button = view as Button
        var _text = tv_input?.text
        tv_input?.text = "${_text}${btnDigit.text}"
        lastNumberic = true
        lastDot = false
    }

    fun onDot(view: View) {
        if(lastNumberic && !lastDot){
            tv_input?.text = "${tv_input?.text}."
            lastNumberic = false
            lastDot = true
        }
    }

    fun onOperator(view: View) {
        var btnOperator:Button = view as Button
        var _text = tv_input?.text
        if(lastNumberic){
            tv_input?.text = "${_text}${btnOperator?.text}"
            lastNumberic = false
            lastDot = false
        }

    }

    fun onResult(view: View) {
        if(lastNumberic){
            try {
                val rs = Expression(tv_input?.text.toString().replace("x","*"))
                tv_input?.text = rs.calculate().toString()
            }catch (e:ArithmeticException){
                Toast.makeText(this,e.toString(),Toast.LENGTH_SHORT).show()
            }

        }
    }

    fun onDelete(view: View) {
        if(tv_input?.text!!.length>0)
            tv_input?.text = tv_input?.text!!.substring(0, tv_input?.text!!.length-1)
    }

    fun onOpen(view: View) {
        tv_input?.text = "${tv_input?.text}("
    }
    fun onClose(view: View) {
        tv_input?.text = "${tv_input?.text})"
    }
}