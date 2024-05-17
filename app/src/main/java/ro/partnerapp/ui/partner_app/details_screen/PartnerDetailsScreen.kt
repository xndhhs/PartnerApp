package ro.partnerapp.ui.partner_app.details_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import org.koin.androidx.compose.getViewModel
import ro.partnerapp.R
import ro.partnerapp.model.PartnerModel
import ro.partnerapp.ui.theme.PartnerAppTheme

@Composable
fun PartnerDetailsScreen(viewModel: PartnerDetailsViewModel) {
    val state = viewModel.detailsUiState.collectAsState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
    ) {
        state.value.partnerModel?.let { PartnerPicture(partnerModel = it, imageSize = 200.dp) }
        Spacer(modifier = Modifier.height(16.dp))
        PartnerSubSection(stringResource(R.string.name), state.value.partnerModel?.name?: "")
        Spacer(modifier = Modifier.height(16.dp))
        PartnerSubSection(stringResource(R.string.description), state.value.partnerModel?.description ?: "")
    }
}

@Composable
fun PartnerSubSection(sectionLabel: String, sectionName: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Start
    ) {
        Text(text = sectionLabel, style = MaterialTheme.typography.bodyLarge)
        Text(text = sectionName, style = MaterialTheme.typography.titleLarge)
    }
}

@Composable
private fun PartnerPicture(partnerModel: PartnerModel, imageSize: Dp) {
    Card(
        border = BorderStroke(
            width = 2.dp,
            color = MaterialTheme.colorScheme.error
        ),
    ) {
        if (partnerModel.imageUrl?.isNotEmpty() == true) {
            AsyncImage(
                model = partnerModel.imageUrl,
                contentDescription = "Partner Image",
                modifier = Modifier.size(imageSize),
                contentScale = ContentScale.FillBounds,
            )
        } else {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.size(imageSize)
            ) {
                Text(
                    text = partnerModel.name?.substring(0, 1) ?: "",
                    style = MaterialTheme.typography.displaySmall,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PartnerDetailsScreenPreview() {
    PartnerAppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxWidth()
        ) {
            val viewModel = getViewModel<PartnerDetailsViewModel>()
            PartnerDetailsScreen(viewModel)
        }
    }
}