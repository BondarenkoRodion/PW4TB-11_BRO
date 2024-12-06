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
//                    n1()
                    n2()
//                    n3()
                }
            }
        }
    }


    fun n1() {
        setContentView(R.layout.layout1)
        val resultField = findViewById<TextView>(R.id.result)

        val button = findViewById<Button>(R.id.calculate)
        val woc = 0.295
        val tvoc = 10.7
        val kaoc = woc * tvoc / 8760
        val kpmax = 43
        val kpoc = 1.2 * kpmax/8760
        val wdk = 2 * woc * (kaoc + kpoc)
        val wcv = 0.02
        val wdc = wdk + wcv


        button.setOnClickListener {

            if (wdk <wdc )
                resultField.text = "надійність двоколової системи є вищою"
            else
                resultField.text = "надійність одноколової системи є вищою"
        }
    }

    fun n2() {
        setContentView(R.layout.layout2)
        val resultField = findViewById<TextView>(R.id.result)
        val button = findViewById<Button>(R.id.calculate)



        button.setOnClickListener {

            val ZperpInput = findViewById<EditText>(R.id.Zperp)
            val ZperaInput = findViewById<EditText>(R.id.Zpera)
            val Zperp = ZperpInput.text.toString().toDoubleOrNull() ?: 0.0
            val Zpera = ZperaInput.text.toString().toDoubleOrNull() ?: 0.0

            val w = 0.01
            val tv = 0.045
            val PM = 5120.0
            val TM = 6451.0
            val kp = 0.004
            val MWneda = w * tv * PM * TM
            val MWnedp = kp * PM * TM
            val mZper = Zpera * MWneda + Zperp * MWnedp

            resultField.text = "Математичне сподівання збитків від переривання електропостачання" + mZper
        }
    }
}
