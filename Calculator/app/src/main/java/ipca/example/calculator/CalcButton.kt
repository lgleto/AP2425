package ipca.example.calculator

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ipca.example.calculator.ui.theme.CalculatorTheme
import ipca.example.calculator.ui.theme.Orange
import ipca.example.calculator.ui.theme.Purple40

@Composable
fun CalcButton(modifier: Modifier = Modifier,
               label : String = "",
               isOperation : Boolean = false,
               onClick : (String)->Unit
) {
    Button(modifier = modifier
        .padding(4.dp)
        .aspectRatio(1f),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isOperation) Orange else Purple40
        ),
        onClick = {
            onClick(label)
        }) {
        Text(text = label,
            fontSize = 42.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun CalcButtonPreview() {
    CalculatorTheme {
        CalcButton(){}
    }
}