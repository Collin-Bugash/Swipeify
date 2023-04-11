package com.collinbugash.swipeify.presentation.settings

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.collinbugash.swipeify.R
import com.collinbugash.swipeify.presentation.viewmodel.SwipeifyViewModel
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun SettingsScreen(viewModel: SwipeifyViewModel){
    val context = LocalContext.current;

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "SETTINGS",
            textAlign = TextAlign.Center,
        )

        Divider(color = Color.White,
            thickness = 1.dp,
            modifier=Modifier
            .padding(horizontal = 10.dp, vertical = 10.dp))

        Column(

        ) {
            Row(modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically)
            {
                Text(text = "Select Genres")
                ResetButton(onClick =  { viewModel.resetGenres() })
            }
            Row(modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically)
            {
//                GenreButton(onClick = { /*TODO*/ }, genreName = stringResource(id = R.string.piano_genre))
//                GenreButton(onClick = { /*TODO*/ }, genreName = stringResource(id = R.string.pop_genre))
                Switch(
                    checked = viewModel.pianoGenre,
                    onCheckedChange = {viewModel.pianoGenreSelected()}
                )
                Switch(
                    checked = viewModel.popGenre,
                    onCheckedChange = {viewModel.popGenreSelected()}
                )
            }
        }
    }
}

@Preview
@Composable
fun settingPreview() {
    //If time fix preview
//    SettingsScreen(SwipeifyViewModel)
}