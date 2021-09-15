package com.questdev.recipeapp.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.questdev.recipeapp.R

val Raleway = FontFamily(
    Font(R.font.raleway_bold, FontWeight.Bold),
    Font(R.font.raleway_medium, FontWeight.Medium),
    Font(R.font.raleway_regular, FontWeight.Normal)
)

// Set of Material typography styles to start with
val Typography = Typography(
    h1 = TextStyle(
        fontFamily = Raleway,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp
    ),
    h2 = TextStyle(
        fontFamily = Raleway,
        fontWeight = FontWeight.SemiBold,
        fontSize = 26.sp
    ),
    h3 = TextStyle(
        fontFamily = Raleway,
        fontWeight = FontWeight.Medium,
        fontSize = 24.sp
    ),
    h4 = TextStyle(
        fontFamily = Raleway,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp
    ),
    h5 = TextStyle(
        fontFamily = Raleway,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp
    ),
    h6 = TextStyle(
        fontFamily = Raleway,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    body1 = TextStyle(
        fontFamily = Raleway,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    body2 = TextStyle(
        fontFamily = Raleway,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = Raleway,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = Raleway,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    button = TextStyle(
        fontFamily = Raleway,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),
    caption = TextStyle(
        fontFamily = Raleway,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    overline = TextStyle(
        fontFamily = Raleway,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp
    )
)
