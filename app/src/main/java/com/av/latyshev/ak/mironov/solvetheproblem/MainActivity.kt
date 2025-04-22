package com.av.latyshev.ak.mironov.solvetheproblem

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.av.latyshev.ak.mironov.solvetheproblem.ui.theme.SolveTheProblemTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // enableEdgeToEdge()
        setContent {
            SolveTheProblemTheme {
                MainLayout()
            }
        }
    }
}

@Composable
fun MainLayout() {
    Column(Modifier.fillMaxSize()) {
        Text(
            text = "Всего решено примеров",
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
            modifier = Modifier.fillMaxWidth().padding(top = 50.dp)
        )
        Counter(
            0,
            30.sp
        )
        Row(Modifier.fillMaxWidth()) {
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.fillMaxWidth(0.5f)
            ) {
                Text(
                    text = "Правильно",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Counter(
                    0,
                    25.sp
                )
            }
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = "Неправильно",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Counter(
                    0,
                    25.sp
                )
            }
        }
        Text(
            text = "0.00%",
            fontSize = 40.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(bottom = 50.dp)
        )
        Row(Modifier.fillMaxWidth().padding(bottom = 50.dp), Arrangement.Center) {
            Text("00", Modifier.padding(horizontal = 10.dp), fontSize = 30.sp)
            Text("+", Modifier.padding(horizontal = 10.dp), fontSize = 30.sp)
            Text("00", Modifier.padding(horizontal = 10.dp), fontSize = 30.sp)
            Text("=", Modifier.padding(horizontal = 10.dp), fontSize = 30.sp)
            SomeTextField(Modifier.width(40.dp), "00")
        }
        SomeButton("Проверка")
        SomeButton("Старт")
    }
}

// TODO бесполезен, надо заменить
@Composable
fun Counter(value: Int, size: TextUnit) {
    Text(
        text = value.toString(),
        textAlign = TextAlign.Center,
        fontSize = size,
        modifier = Modifier.fillMaxWidth().padding(bottom = 50.dp)
    )
}

@Composable
fun SomeButton(text: String) {
    Row(Modifier.fillMaxWidth().padding(bottom = 50.dp), Arrangement.Center) {
        Button(onClick = {}, shape = RoundedCornerShape(10.dp)) {
            Text(text, fontSize = 25.sp)
        }
    }
}

@Composable
fun SomeTextField(
    modifier: Modifier,
    placeholderText: String
) {
    // в теории должен прокидывать значение
    var text by rememberSaveable { mutableStateOf("") }

    BasicTextField(
        value = text,
        onValueChange = { text = it },
        singleLine = true,
        textStyle = LocalTextStyle.current.copy(
            fontSize = 30.sp
        ),
        decorationBox = { innerTextField ->
            Row(
                modifier = modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(Modifier.weight(1f)) {
                    if (text.isEmpty()) {
                        Text(
                            text = placeholderText,
                            style = LocalTextStyle.current.copy(
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                                fontSize = 30.sp,
                                textDecoration = TextDecoration.Underline
                            )
                        )
                    }
                    innerTextField()
                }
            }
        }
    )
}

@Preview(showSystemUi = true)
@Composable
fun Preview() {
    SolveTheProblemTheme {
        MainLayout()
    }
}