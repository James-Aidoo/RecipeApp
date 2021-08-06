package com.questdev.recipeapp.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.questdev.recipeapp.R
import com.questdev.recipeapp.ui.theme.RecipeAppTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RecipeAppTheme {

    }
}