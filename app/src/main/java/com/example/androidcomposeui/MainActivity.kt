package com.example.androidcomposeui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.androidcomposeui.ui.theme.AndroidComposeUITheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import  androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidComposeUITheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(14.dp),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TrueFalseGame()
                }
            }
        }
    }
}

@Composable
fun TrueFalseGame(modifier: Modifier = Modifier) {
    var questions = listOf("1+1 = 3", "12-100 = 0", "1+1=2", "7+7=7", "19-10=9", "22-9=30")
    var answers = listOf(false, false, true, false, true, false)

    var score by remember {
        mutableStateOf(0)
    }

    var currentQuestionIndex by remember {
        mutableStateOf(0)

    }
    var showCorrectResult by remember {
        mutableStateOf(false)
    }

    var showWrongResult by remember {
        mutableStateOf(false)
    }

    var showNextQuestionButton by remember {
        mutableStateOf(false)
    }

    var showAnswersOptionsRow by remember {
        mutableStateOf(true)
    }

    Column(
        modifier = modifier.padding(14.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = questions[currentQuestionIndex],
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp
        )



        if (showWrongResult) {
            WrongAnswer("Wrong")
        }
        if (showCorrectResult) {
            CorrectAnswer("Correct")
        }

        Text(text = "Score: $score", fontSize = 50.sp)
        if (showNextQuestionButton)
            Button(onClick = {
                if (currentQuestionIndex == questions.size - 1) {
                    currentQuestionIndex = 0
                    score = 0
                } else
                    currentQuestionIndex++
                showNextQuestionButton = false
                showAnswersOptionsRow = true
                showCorrectResult = false
                showWrongResult = false

            }) {
                Text(text = "Next Question")
            }
        if (showAnswersOptionsRow)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                Alignment.Bottom
            ) {

                Button(modifier = Modifier.weight(1f),
                    onClick = {
                        val isCorrect = true == answers[currentQuestionIndex]
                        if (isCorrect) {
                            score++
                            showCorrectResult = true
                            showNextQuestionButton = true
                            showAnswersOptionsRow = false
                            showWrongResult = false
                        } else {
                            showWrongResult = true
                            showNextQuestionButton = false
                        }
                    }) {
                    Text(text = "True")
                }
                Spacer(modifier = Modifier.width(50.dp))

                Button(modifier = Modifier.weight(1f),
                    onClick = {
                        val isCorrect = false == answers[currentQuestionIndex]
                        if (isCorrect) {
                            score++
                            showCorrectResult = true
                            showNextQuestionButton = true
                            showAnswersOptionsRow = false
                            showWrongResult = false
                        } else {
                            showWrongResult = true
                            showNextQuestionButton = false

                        }
                    }) {
                    Text(text = "False")
                }
            }
    }

}

@Composable
fun CorrectAnswer(text: String, modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .size(150.dp)
    ) {
        Image(modifier = Modifier.fillMaxSize(),painter = painterResource(id = R.drawable.correct), contentDescription = "")
        Text(
            text = text,
            modifier = Modifier.align(Alignment.Center)
        )
    }

}

@Composable
fun WrongAnswer(text: String, modifier: Modifier = Modifier) {

    Box(
        modifier = Modifier
            .size(150.dp),
    ) {
       Image(modifier = Modifier.fillMaxSize(), painter = painterResource(id = R.drawable.wrong), contentDescription = "")
        Text(
            text = text,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}