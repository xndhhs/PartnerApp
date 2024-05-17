package ro.partnerapp.ui.partner_app.details_screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ro.partnerapp.interactors.PartnerAppUseCase

class PartnerDetailsViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val partnerAppUseCase: PartnerAppUseCase
) : ViewModel() {
    private val uiState: MutableStateFlow<DetailsUiState> = MutableStateFlow(DetailsUiState(null))
    val detailsUiState: StateFlow<DetailsUiState> = uiState.asStateFlow()


    init {
        viewModelScope.launch {
            val partnerId = savedStateHandle["partner_id"] ?: 0
            val selectedPartner =
                partnerAppUseCase.getPartnerList().firstOrNull { it.id == partnerId }

            uiState.update { currentState ->
                currentState.copy(
                    partnerModel = selectedPartner
                )
            }

        }
    }
}