package com.collinbugash.swipeify.presentation.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.collinbugash.swipeify.presentation.viewmodel.SwipeifyViewModel

@Composable
fun SettingsScreen(viewModel: SwipeifyViewModel){
    val context = LocalContext.current;

    val genresSelected = remember(viewModel.mGenresSelected) { viewModel.mGenresSelected }

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
                for(genre in viewModel.playlists) {
                    GenreButton(
                        text = genre.second,
                        isSelected = viewModel.isGenreSelected(genre.second),
                        onSelectedChange = { viewModel.toggleGenreSelected(genre.second) },
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