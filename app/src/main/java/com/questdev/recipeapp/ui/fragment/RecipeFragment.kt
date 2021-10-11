package com.questdev.recipeapp.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeFragment : Fragment() {

    private var recipeId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        extractArguments()

        return ComposeView(requireContext()).apply {
            setContent {
                Text(
                    text = "Recipe Fragment",
                    modifier = Modifier.padding(16.dp),
                    style = TextStyle(fontSize = 21.sp)
                )
            }
        }
    }

    fun extractArguments() {
        arguments?.let {
            recipeId = it.getInt(KEY_RECIPE_ID)
        } ?: return
        Log.d(TAG, "The ID of the clicked recipe: $recipeId")

    }

    companion object {
        private val TAG = RecipeFragment::class.java.simpleName
        private const val KEY_RECIPE_ID = "recipeId"

        fun getRequiredArguments(recipeId: Int): Bundle {
            return Bundle().apply {
                putInt(KEY_RECIPE_ID, recipeId)
            }
        }
    }

}
