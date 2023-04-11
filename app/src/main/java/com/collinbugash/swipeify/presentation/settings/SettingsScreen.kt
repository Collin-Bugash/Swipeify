package com.collinbugash.swipeify.presentation.settings

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.collinbugash.swipeify.presentation.viewmodel.SwipeifyViewModel

@SuppressLint("UnrememberedMutableState")
@Composable
fun SettingsScreen(viewModel: SwipeifyViewModel){
    val context = LocalContext.current;

    val genresSelected = viewModel.mGenresSelected.collectAsState()
    val playlists = viewModel.playlists

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
                ResetButton(onClick =  { viewModel.removeAllGenres() })
            }
            Row(modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically)
            {
                for(genre in playlists) {
                    val isSelected = genresSelected.value.contains(genre.second)
                    GenreButton(
                        text = genre.second,
                        isSelected = isSelected,
                        onSelectedChange = {genre: String -> viewModel.toggleGenreSelected(genre)},
                        modifier = Modifier.padding(4.dp)
                    )
                }
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