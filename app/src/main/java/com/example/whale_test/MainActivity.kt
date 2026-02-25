package com.example.whale_test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.whale_test.features.information.presentation.InformationScreen
import com.example.whale_test.features.information.presentation.InformationState
import com.example.whale_test.features.information.presentation.getListPromotions
import com.example.whale_test.features.signup.signup.presentation.SignUpScreen
import com.example.whale_test.features.signup.signup.presentation.SignUpState
import com.example.whale_test.ui.theme.Whale_TestTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainScreen()
        }
    }
}

@Preview
@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    Whale_TestTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { _ ->

           /* InformationScreen(
                InformationState(getListPromotions()),
                onSelectPromotion = {},
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
            )*/

            SignUpScreen(
                state = SignUpState(),
                onBack = {},
                toNextScreen = {},
                modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background))

        }
    }
}

