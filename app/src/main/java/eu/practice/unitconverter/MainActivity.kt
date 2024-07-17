package eu.practice.unitconverter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.practice.unitconverter.ui.theme.UnitConverterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter(){
    var inputValue    by remember{ mutableStateOf("") }
    var  outputValue  by remember{ mutableStateOf("") }
    var  inputUnit    by remember{ mutableStateOf("Centimeters") }
    var  outputUnit   by remember{ mutableStateOf("Meters") }
    var   iExpanded   by remember{ mutableStateOf(false) }
    var   oExpanded   by remember{ mutableStateOf(false) }
    var  conversionFactor by remember { mutableStateOf(1.0) }
    var  oconversionFactor by remember { mutableStateOf(1.0) }

    val CustomTextStyle = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 35.sp,
        color = Color.Green
    )


    fun convertUnit(){
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = inputValueDouble * conversionFactor / oconversionFactor
        outputValue = result.toString()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // here all the elements of UI are stacked below each other
        Text(
            "Unit Converter",
            style =CustomTextStyle
        )

        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputValue, onValueChange = {
            inputValue = it
            convertUnit()
            // here goes what should happen, when the value of our outlinedTextField change
        }, label = { Text(text = "Enter value") })
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            // INPUT BOX
            Box {
                // INPUT BUTTON
                Button(onClick = { iExpanded = true }) {
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false }) {
                    DropdownMenuItem(text = { Text(text = "Centimeter") },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Centimeter"
                            conversionFactor = 1.0
                            convertUnit()
                        })
                    DropdownMenuItem(text = { Text(text = "Meter") }, onClick = {
                        iExpanded = false
                        inputUnit = "Meter"
                        conversionFactor = 100.0 // 1 Meter = 100 Centimeters
                        convertUnit()
                    })
                    DropdownMenuItem(text = { Text(text = "Feet") }, onClick = {
                        iExpanded = false
                        inputUnit = "Feet"
                        conversionFactor = 30.48 // 1 Foot = 30.48 Centimeters
                        convertUnit()
                    })
                    DropdownMenuItem(text = { Text(text = "Millimeter") }, onClick = {
                        iExpanded = false
                        inputUnit = "Millimeter"
                        conversionFactor = 0.1 // 1 Millimeter = 0.1 Centimeters
                        convertUnit()
                    })
                }
            }
            Spacer(modifier = Modifier.width(50.dp))
            // OUTPUT BOX
            Box {
                // OUTPUT BUTTON
                Button(onClick = { oExpanded = true }) {
                    Text(text = outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = false }) {
                    DropdownMenuItem(text = { Text(text = "Centimeter") }, onClick = {
                        oExpanded = false
                        outputUnit = "Centimeter"
                        oconversionFactor = 1.0
                        convertUnit()
                    })
                    DropdownMenuItem(text = { Text(text = "Meter") }, onClick = {
                        oExpanded = false
                        outputUnit = "Meter"
                        oconversionFactor = 100.0 // 1 Meter = 100 Centimeters
                        convertUnit()
                    })
                    DropdownMenuItem(text = { Text(text = "Feet") }, onClick = {
                        oExpanded = false
                        outputUnit = "Feet"
                        oconversionFactor = 30.48 // 1 Foot = 30.48 Centimeters
                        convertUnit()
                    })
                    DropdownMenuItem(text = { Text(text = "Millimeter") }, onClick = {
                        oExpanded = false
                        outputUnit = "Millimeter"
                        oconversionFactor = 0.1 // 1 Millimeter = 0.1 Centimeters
                        convertUnit()
                    })
                }
            }
        }
        Spacer(modifier = Modifier.width(16.dp))
        Text(

            text = "Result: $outputValue $outputUnit",
            modifier = Modifier.align(Alignment.CenterHorizontally) // Centers the text vertically within the Row
            , style =MaterialTheme.typography.headlineMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    Column {
        Text(text = "Result:")
        UnitConverter()
    }
}
