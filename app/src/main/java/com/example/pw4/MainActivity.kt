package com.example.pw4

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.pw4.ui.theme.PW4Theme
import kotlin.math.sqrt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PW4Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
                    n1()
//                    n2()
//                    n3()
                }
            }
        }
    }


    fun n1() {
        setContentView(R.layout.layout1)
        val resultField = findViewById<TextView>(R.id.result)

        val UnomInput = findViewById<EditText>(R.id.Unom)
        val IkInput = findViewById<EditText>(R.id.Ik)
        val tfInput = findViewById<EditText>(R.id.tf)
        val TpInput = findViewById<EditText>(R.id.TP)
        val SmInput = findViewById<EditText>(R.id.SM)
        val TmInput = findViewById<EditText>(R.id.TM)
        val button = findViewById<Button>(R.id.calculate)



        button.setOnClickListener {


            val Unom = UnomInput.text.toString().toDoubleOrNull() ?: 0.0
            val Ik = IkInput.text.toString().toDoubleOrNull() ?: 0.0
            val tf = tfInput.text.toString().toDoubleOrNull() ?: 0.0
            val Tp = TpInput.text.toString().toDoubleOrNull() ?: 0.0
            val Sm = SmInput.text.toString().toDoubleOrNull() ?: 0.0
            val Tm = TmInput.text.toString().toDoubleOrNull() ?: 0.0


            val jek = 1.4
            val Ct = 92.0

            val Im = (Sm / 2) / (sqrt(3.0) * Unom)
            val Impa = 2 * Im

            val sek = Im / jek

            val smin = (Ik * sqrt(tf)) / Ct

            resultField.text = "$Impa, $sek, $smin"
        }
    }

    fun n2() {
        setContentView(R.layout.layout2)
        val resultField = findViewById<TextView>(R.id.result)

        val UCHInput = findViewById<EditText>(R.id.UCH)
        val SKInput = findViewById<EditText>(R.id.SK)
        val UKInput = findViewById<EditText>(R.id.UK)
        val SHOMTInput = findViewById<EditText>(R.id.SHOMT)
        val button = findViewById<Button>(R.id.calculate)



        button.setOnClickListener {
            val UCH = UCHInput.text.toString().toDoubleOrNull() ?: 0.0
            val SK = SKInput.text.toString().toDoubleOrNull() ?: 0.0
            val UK = UKInput.text.toString().toDoubleOrNull() ?: 0.0
            val SHOMT = SHOMTInput.text.toString().toDoubleOrNull() ?: 0.0


            val Xc = UCH * UCH / SK
            val XT = (UK / 100) * (UCH * UCH / SHOMT)
            val xS = Xc + XT
            val Ip0 = UCH / (sqrt(3.0) * xS)

            resultField.text = "Початкове діюче значення струму трифазного КЗ $Ip0"
        }
    }

    fun n3() {
        setContentView(R.layout.layout3)
        val resultField = findViewById<TextView>(R.id.result)
        val button = findViewById<Button>(R.id.calculate)
        val SnomInput = findViewById<EditText>(R.id.Snom)
        val UBHInput = findViewById<EditText>(R.id.UBH)


        button.setOnClickListener {
            val UKmax = 11.1
            val UBH = UBHInput.text.toString().toDoubleOrNull() ?: 0.0
            val SHOMT = SnomInput.text.toString().toDoubleOrNull() ?: 0.0
            val XT = (UKmax * UBH * UBH) / (100 * SHOMT)
            val Rw = 10.65
            val XCH = 24.02
            val Xw = XCH + XT
            val Rwmin = 34.88
            val Xcmin = 65.68
            val Xwmin = Xcmin + XT

            val UHH = 11.0
            val kpr = UHH * UHH / (UBH * UBH)

            val RwH = Rw * kpr
            val XwH = Xw * kpr

            val RwHmin = Rwmin * kpr
            val XwHmin = Xwmin * kpr

            val l = 12.37
            val R0 = 0.64
            val X0 = 0.363

            val Rl = l * R0
            val Xl = l * X0

            val RSH = Rl + RwH
            val XSH = Xl + XwH

            val ZSH = sqrt(RSH * RSH + XSH * XSH)

            val RSHmin = Rl + RwHmin
            val XSHmin = Xl + XwHmin

            val ZSHmin = sqrt(RSHmin * RSHmin + XSHmin * XSHmin)

            val I3lH = UHH * 1000 / (sqrt(3.0) * ZSH)
            val I2lH = I3lH * (sqrt(3.0) / 2)
            val I3lHmin = UHH * 1000 / (sqrt(3.0) * ZSHmin)
            val I2lHmin = I3lHmin * (sqrt(3.0) / 2)


            resultField.text = "I3л.н $I3lH, I2л.н $I2lH, I3л.н.min $I3lHmin, I2л.н.min $I2lHmin"
        }
    }
}
