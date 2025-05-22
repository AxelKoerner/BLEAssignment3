package com.example.bleassignment3.gps

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.bleassignment3.ui.theme.BLEAssignment3Theme

class GpsPositioning : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BLEAssignment3Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GPSContent(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun GPSContent(modifier: Modifier) {

}
