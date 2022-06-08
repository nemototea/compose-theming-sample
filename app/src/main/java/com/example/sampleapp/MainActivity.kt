package com.example.sampleapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.sampleapp.ui.theme.ColorPalette
import com.example.sampleapp.ui.theme.ColorPalette.Companion.toName
import com.example.sampleapp.ui.theme.ColorPalette.Companion.toPainter
import com.example.sampleapp.ui.theme.SampleAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            var showThemeDialog by remember {
                mutableStateOf(false)
            }
            var themeColor by remember {
                mutableStateOf(ColorPalette.LOID)
            }

            SampleAppTheme(themeColor) {
                Surface(modifier = Modifier.fillMaxSize(), color = SampleAppTheme.current.basic) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "SPY×FAMILY",
                            fontFamily = FontFamily.Serif,
                            fontSize = 48.sp,
                            modifier = Modifier.padding(bottom = 24.dp),
                            color = SampleAppTheme.current.highlight
                        )
                        Image(painter = SampleAppTheme.current.toPainter(), contentDescription = "")
                        Text(
                            text = SampleAppTheme.current.toName(),
                            modifier = Modifier.padding(vertical = 16.dp),
                            textDecoration = TextDecoration.Underline,
                            fontFamily = FontFamily.Serif,
                            color = SampleAppTheme.current.highlight
                        )
                        Button(
                            onClick = { showThemeDialog = true },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = SampleAppTheme.current.dark,
                                contentColor = Color.White
                            ),
                            content = { Text("Theme Colors") }
                        )
                    }

                    if (showThemeDialog) {
                        ThemeDialog {
                            it?.let { themeColor = it }
                            showThemeDialog = false
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ThemeDialog(
    onDismiss: (ColorPalette?) -> Unit
) {
    Dialog(onDismissRequest = { onDismiss(null) }) {
        SkinThemeDialogContent(onChangeSkinTheme = { onDismiss(it) })
    }
}

@Composable
private fun SkinThemeDialogContent(
    onChangeSkinTheme: (ColorPalette) -> Unit
) {
    Surface(
        modifier = Modifier.width(280.dp),
        shape = RoundedCornerShape(4.dp)
    ) {
        Column {
            Text(
                text = "Theme",
                modifier = Modifier
                    .height(64.dp)
                    .padding(horizontal = 8.dp)
                    .paddingFromBaseline(40.dp)
            )
            Column(Modifier.selectableGroup()) {
                ColorPalette.values().forEach { theme ->
                    SkinThemeDialogRow(
                        text = theme.toName(),
                        selected = (theme == SampleAppTheme.current),
                        onClick = {
                            onChangeSkinTheme(theme)
                        })
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun SkinThemeDialogRow(
    text: String,
    selected: Boolean = false,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .height(48.dp)
            .fillMaxWidth()
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            modifier = Modifier.padding(24.dp, 0.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                modifier = Modifier.size(24.dp),
                colors = RadioButtonDefaults.colors(
                    selectedColor = SampleAppTheme.current.basic
                ),
                selected = selected,
                onClick = onClick
            )
            Spacer(modifier = Modifier.width(32.dp))
            Text(text)
        }
    }
}
