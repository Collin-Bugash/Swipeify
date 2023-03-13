package com.collinbugash.swipeify.presentation.settings

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.collinbugash.swipeify.R

@Composable
fun ResetButton(onClick: () -> Unit){
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        Text(
            text = stringResource(id = R.string.reset),
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun previewButton() {
    ResetButton(onClick = {})
}