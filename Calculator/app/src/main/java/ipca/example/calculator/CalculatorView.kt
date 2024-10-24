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
                label = "1",
                onClick = {})
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "2",
                onClick = {})
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "3",
                onClick = {})
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "+",
                isOperation = true,
                onClick = {})
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