package com.collinbugash.swipeify.presentation.settings

import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.collinbugash.swipeify.R


// MAY NOT BE USED!!!!!!!!!!!!!!
@Composable
fun GenreButton(onClick: () -> Unit, genreName: String) {
    Button(
        modifier = Modifier
            .width(130.dp),
        onClick = onClick,
    ) {
        Text(
            text = genreName,
            textAlign = TextAlign.Center
        )
    }
}