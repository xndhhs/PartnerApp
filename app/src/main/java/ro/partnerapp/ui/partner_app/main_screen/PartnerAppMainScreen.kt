package ro.partnerapp.ui.partner_app.main_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.getViewModel
import ro.partnerapp.R
import ro.partnerapp.model.PartnerModel
import ro.partnerapp.ui.partner_app.details_screen.PartnerSubSection
import ro.partnerapp.ui.theme.PartnerAppTheme

@Composable
fun PartnerAppMainScreen(
    viewModel: PartnerAppMainScreenViewModel,
    onPartnerSelected: (PartnerModel) -> Unit,
    onTryAgain: () -> Unit
) {
    val state = viewModel.mainUiState.collectAsState().value
    if (state.isServerError) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = stringResource(R.string.server_error),
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.error
            )
            Button(onClick = { onTryAgain.invoke() }) {
                Text(text = stringResource(R.string.try_again))
            }
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 24.dp, horizontal = 8.dp)
        ) {
            state.ratingsList.forEach {
                item { RatingsHeader(it.rating) }
                items(
                    count = it.partners.size,
                    contentType = { PartnerModel::class.java }
                ) { index ->
                    PartnerItemView(it.partners[index]) {
                        onPartnerSelected.invoke(it.partners[index])
                    }
                }
            }
        }
    }
}

@Composable
private fun RatingsHeader(rating: Int) {
    Spacer(modifier = Modifier.height(16.dp))
    Text(
        text = String.format(stringResource(R.string.rating), rating),
        style = MaterialTheme.typography.labelLarge
    )
    Divider(
        thickness = 0.75.dp,
        color = MaterialTheme.colorScheme.onSurface,
        modifier = Modifier.padding(vertical = 4.dp)
    )
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
fun PartnerItemView(partnerModel: PartnerModel, navigationCallback: () -> Unit) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navigationCallback.invoke() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            PartnerSubSection(stringResource(R.string.name), partnerModel.name ?: "")
            Spacer(modifier = Modifier.height(8.dp))
            PartnerSubSection(stringResource(R.string.description), partnerModel.description ?: "")
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
}


@Preview(showBackground = true)
@Composable
fun PartnerAppMainScreenPreview() {
    PartnerAppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxWidth()
        ) {
            val viewModel = getViewModel<PartnerAppMainScreenViewModel>()
            PartnerAppMainScreen(viewModel, {}, {})
        }
    }
}