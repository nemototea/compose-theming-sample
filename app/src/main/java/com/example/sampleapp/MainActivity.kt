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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.sampleapp.ui.theme.SampleAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            var showThemeDialog by remember {
                mutableStateOf(false)
            }

            SampleAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = SampleAppTheme.current.dark) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Image(painter = painterResource(id = R.drawable.anyadark), contentDescription = "")

                        Button(
                            onClick = { showThemeDialog = true },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = SampleAppTheme.current.basic,
                                contentColor = Color.White
                            ),
                            content = { Text("Theme Colors") }
                        )
                        Button(
                            modifier = Modifier.padding(top = 8.dp),
                            onClick = { showThemeDialog = true },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = SampleAppTheme.current.basic,
                                contentColor = Color.White
                            ),
                            content = { Text("Theme Colors") }
                        )
                    }

                    if (showThemeDialog) {
                        ThemeDialog(
                            onDismiss = { showThemeDialog = false },
                            onChangeSkinTheme = {
                                // TODO: テーマ切り替え処理
                                showThemeDialog = false
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ThemeDialog(
    onDismiss: (ColorPalette) -> Unit,
    onChangeSkinTheme: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        SkinThemeDialogContent(
            onChangeSkinTheme = {
                onChangeSkinTheme(it)
            }
        )
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
            Box(
                modifier = Modifier
                    .height(64.dp)
                    .padding(horizontal = 24.dp)
            ) {
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.high) {
                    ProvideTextStyle(MaterialTheme.typography.subtitle1) {
                        Text(text = stringResource(id = R.string.theme), Modifier.paddingFromBaseline(40.dp))
                    }
                }
            }
            Column(Modifier.selectableGroup()) {
                ColorPalette.values().forEach { theme ->
                    SkinThemeDialogRow(
                        text = theme.toColorName(),
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
