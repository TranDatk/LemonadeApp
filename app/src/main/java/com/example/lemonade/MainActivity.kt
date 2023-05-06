package com.example.lemonade

import android.os.Bundle
import android.service.autofill.OnClickAction
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    LemonadeApp()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadeApp() {
    LemonadeWithImageAndText(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    )
}

@Composable
fun LemonadeWithImageAndText(modifier: Modifier) {
    var statusOfImageResource by remember {
        mutableStateOf(1)
    }

    var statusOfLemonSqueeze by remember {
        mutableStateOf(1)
    }

    val imageResource = when (statusOfImageResource) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    val textString = when (statusOfImageResource) {
        1 -> R.string.Action1
        2 -> R.string.Action2
        3 -> R.string.Action3
        else -> R.string.Action4
    }

    Column(
        modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(id = textString))
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier.clickable(onClick = {
                if (statusOfImageResource in 1..3 && statusOfImageResource != 2)
                    statusOfImageResource++
                else if (statusOfImageResource == 2) {
                    if (statusOfLemonSqueeze > 0) {
                        statusOfLemonSqueeze--
                    } else {
                        statusOfImageResource++
                        statusOfLemonSqueeze = (1..2).random()
                    }
                } else
                    statusOfImageResource = 1
            })
        ) {
            Image(
                painter = painterResource(id = imageResource),
                contentDescription = stringResource(id = R.string.Lemon_Tree),
            )
        }
    }
}