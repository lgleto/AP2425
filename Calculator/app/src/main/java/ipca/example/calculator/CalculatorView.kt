package ipca.example.calculator


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ipca.example.calculator.ui.theme.CalculatorTheme
import ipca.example.calculator.ui.theme.Orange
import ipca.example.calculator.ui.theme.Pink40
import ipca.example.calculator.ui.theme.Purple40
import ipca.example.calculator.ui.theme.PurpleGrey40

@Composable
fun CalculatorView(modifier: Modifier = Modifier) {

    var display by remember { mutableStateOf("0") }
    var operation by remember { mutableStateOf("") }
    var operator by remember { mutableStateOf(0.0) }
    var userIsTyping by remember { mutableStateOf(false) }

    fun getDisplay() : Double{
        return display.toDouble()
    }

    fun setDisplay(value : Double){
        if (value % 1 == 0.0){
            display = value.toInt().toString()
        }else {
            display = value.toString()
        }
    }

    val onClickOperation : (String) -> Unit = { op ->
        userIsTyping = false

        when(operation){
            "+"->{
                setDisplay(operator + getDisplay())
            }
            "-"->{
                setDisplay(operator - getDisplay())
            }
            "/"->{
                setDisplay(operator / getDisplay())
            }
            "*"->{
                setDisplay(operator * getDisplay())
            }
        }
        operator = getDisplay()
        operation = op
    }

    val onClickNumber : (String) -> Unit = { number ->
        if (userIsTyping) {
            if (display == "0" && number != ".") {
                display = number
            }else if(number == "." ) {
                if(!display.contains(".")) {
                    display += number
                }
            }else {
                display += number
            }
        }else{
            display = number
        }
        userIsTyping = true
    }





    Column(modifier = modifier
        .fillMaxSize()) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = display,
            textAlign = TextAlign.End,
            fontSize = 64.sp)
        Row{
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "9",
                onClick = onClickNumber)
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "8",
                onClick = onClickNumber)
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "7",
                onClick = onClickNumber)
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "+",
                isOperation = true,
                onClick = onClickOperation)
        }
        Row{
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "6",
                onClick = onClickNumber)
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "5",
                onClick = onClickNumber)
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "4",
                onClick = onClickNumber)
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "-",
                isOperation = true,
                onClick = onClickOperation)
        }
        Row{
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "1",
                onClick = onClickNumber)
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "2",
                onClick = onClickNumber)
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "3",
                onClick = onClickNumber)
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "*",
                isOperation = true,
                onClick = onClickOperation)
        }
        Row{
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "0",
                onClick = onClickNumber)
            CalcButton(
                modifier = Modifier.weight(1f),
                label = ".",
                onClick = onClickNumber)
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "=",
                isOperation = true,
                onClick = onClickOperation)
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "/",
                isOperation = true,
                onClick = onClickOperation)
        }

    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorPreview(){
    CalculatorTheme {
        CalculatorView()
    }
}