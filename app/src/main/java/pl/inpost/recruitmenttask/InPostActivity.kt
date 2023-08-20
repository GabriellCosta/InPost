package pl.inpost.recruitmenttask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.graphics.Color
import dagger.hilt.android.AndroidEntryPoint
import pl.inpost.recruitmenttask.theme.InPostSystemUi

@AndroidEntryPoint
class InPostActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            InPostSystemUi(
                statusBarColor = Color(0xFFFFFFFF),
                navigationBarColor = Color(0xFFFFFFFF),
            )

            InPostApp()
        }
    }
}