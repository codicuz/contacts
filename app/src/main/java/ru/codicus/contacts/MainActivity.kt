package ru.codicus.contacts

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Greeting(name = "Android")

        }
    }

    @Composable
    fun Greeting(name: String) {
        Text(
            text = "Hello $name!"
        )
    }

    @Preview(showBackground = false, showSystemUi = true,
        device = "spec:parent=pixel_6,navigation=buttons", apiLevel = 34
    )
    @Composable
    fun GreetingPreview() {
        Greeting("Android")

    }
}