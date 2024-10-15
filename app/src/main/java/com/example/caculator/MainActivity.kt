package com.example.caculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.caculator.ui.theme.CaculatorTheme

import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var textMain: TextView
    private var operator: String = ""
    private var value1: Double = 0.0
    private var value2: Double = 0.0
    private var isNewOp: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main) // Đảm bảo bạn đang sử dụng đúng tên file XML

        textMain = findViewById(R.id.Textmain)

        val buttons = listOf(
            findViewById<Button>(R.id.btn0), findViewById<Button>(R.id.btn1), findViewById<Button>(R.id.btn2),
            findViewById<Button>(R.id.btn3), findViewById<Button>(R.id.btn4), findViewById<Button>(R.id.btn5),
            findViewById<Button>(R.id.btn6), findViewById<Button>(R.id.btn7), findViewById<Button>(R.id.btn8),
            findViewById<Button>(R.id.btn9)
        )

        buttons.forEach { button ->
            button.setOnClickListener {
                onNumberClick(button.text.toString())
            }
        }

        findViewById<Button>(R.id.btnplus).setOnClickListener { onOperatorClick("+") }
        findViewById<Button>(R.id.btnminus).setOnClickListener { onOperatorClick("-") }
        findViewById<Button>(R.id.btndiv).setOnClickListener { onOperatorClick("/") }
        findViewById<Button>(R.id.btnx).setOnClickListener { onOperatorClick("*") }

        findViewById<Button>(R.id.btnequal).setOnClickListener { onEqualClick() }
        findViewById<Button>(R.id.btnCE).setOnClickListener { onClearEntry() }
        findViewById<Button>(R.id.btnC).setOnClickListener { onClearAll() }
        findViewById<Button>(R.id.btnBS).setOnClickListener { onBackspace() }
        findViewById<Button>(R.id.btndot).setOnClickListener { onNumberClick(".") }
    }

    private fun onNumberClick(number: String) {
        if (isNewOp) {
            textMain.text = ""
            isNewOp = false
        }
        textMain.append(number)
    }

    private fun onOperatorClick(op: String) {
        value1 = textMain.text.toString().toDoubleOrNull() ?: 0.0
        operator = op
        isNewOp = true
    }

    private fun onEqualClick() {
        value2 = textMain.text.toString().toDoubleOrNull() ?: 0.0
        val result = when (operator) {
            "+" -> value1 + value2
            "-" -> value1 - value2
            "*" -> value1 * value2
            "/" -> if (value2 != 0.0) value1 / value2 else "Error"
            else -> 0.0
        }
        textMain.text = result.toString()
        isNewOp = true
    }

    private fun onClearEntry() {
        textMain.text = "0"
        isNewOp = true
    }

    private fun onClearAll() {
        textMain.text = "0"
        value1 = 0.0
        value2 = 0.0
        operator = ""
        isNewOp = true
    }

    private fun onBackspace() {
        if (textMain.text.isNotEmpty()) {
            textMain.text = textMain.text.dropLast(1)
        }
        if (textMain.text.isEmpty()) {
            textMain.text = "0"
        }
    }
}
