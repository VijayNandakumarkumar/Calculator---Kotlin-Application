package com.example.calculator

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Double.parseDouble
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvOne.setOnClickListener {
            appendOnExpression("1", true)
        }
        tvTwo.setOnClickListener {
            appendOnExpression("2", true)
        }
        tvThree.setOnClickListener {
            appendOnExpression("3", true)
        }
        tvFour.setOnClickListener {
            appendOnExpression("4", true)
        }
        tvFive.setOnClickListener {
            appendOnExpression("5", true)
        }
        tvSix.setOnClickListener {
            appendOnExpression("6", true)
        }
        tvSeven.setOnClickListener {
            appendOnExpression("7", true)
        }
        tvEight.setOnClickListener {
            appendOnExpression("8", true)
        }
        tvNine.setOnClickListener {
            appendOnExpression("9", true)
        }
        tvZero.setOnClickListener {
            appendOnExpression("0", true)
        }

        tvPlues.setOnClickListener {
            appendOnExpression("+", false)
        }
        tvMinus.setOnClickListener {
            appendOnExpression("-", false)
        }
        tvMul.setOnClickListener {
            appendOnExpression("*", false)
        }
        tvDiv.setOnClickListener {
            appendOnExpression("/", false)
        }
        tvOpen.setOnClickListener {
            appendOnExpression("(", false)
        }
        tvClose.setOnClickListener {
            appendOnExpression(")", false)
        }
        tvDot.setOnClickListener {
            appendOnExpression(".", false)
        }


        tvCe.setOnClickListener {
            tvExpression.text = ""
            tvResult.text = ""
        }

        tvBack.setOnClickListener {
            val string = tvExpression.text.toString()
            if(string.isNotEmpty()){
                tvExpression.text = string.substring(0, string.length - 1)
            }
            tvResult.text = ""
        }
        tvBack.setOnLongClickListener{
            tvExpression.text = ""
            tvResult.text = ""
            return@setOnLongClickListener true
        }
        tvEquals.setOnClickListener {
            try {
                var expression = ExpressionBuilder(tvExpression.text.toString()).build()
                var result = expression.evaluate()
                var longResult = result.toLong()
                if(result == longResult.toDouble()){
                    tvResult.text = longResult.toString()
                }
                else{
                    tvResult.text = result.toString()
                }
            }catch (e: Exception){
                tvResult.text = "Bad Expression"
                tvExpression.setTextColor(Color.parseColor("#FFFF5794"))
                tvResult.setTextColor(Color.parseColor("#FFFF5794"))
            }
        }
    }

    fun appendOnExpression(str: String, canClear: Boolean){
//        tvExpression.setTextColor(ContextCompat.getColor(context, R.color.numberButton))
//        tvResult.setTextColor(ContextCompat.getColor(applicationContext, R.color.numberButton)
        tvExpression.setTextColor(Color.parseColor("#455A64"))
        tvResult.setTextColor(Color.parseColor("#455A64"))

        var number = true
        try {
            val s = parseDouble(tvResult.text.toString())
        }
        catch (e: Exception){
            number=false
        }
        if(tvResult.text.isNotEmpty()){
            if (number)
                tvExpression.text = ""
            else{
                tvResult.text = ""
                tvExpression.text = ""
            }
        }
        if(canClear){
            tvResult.text = ""
            tvExpression.append(str)
        }
        else{
            tvExpression.append(tvResult.text)
            tvExpression.append(str)
            tvResult.text = ""
        }
    }
}
