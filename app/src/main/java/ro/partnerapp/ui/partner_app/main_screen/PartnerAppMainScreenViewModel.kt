package ro.partnerapp.ui.partner_app.main_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ro.partnerapp.interactors.PartnerAppUseCase
import ro.partnerapp.model.PartnerModel

class PartnerAppMainScreenViewModel(
    private val partnerAppUseCase: PartnerAppUseCase
) : ViewModel() {

    private val uiState: MutableStateFlow<MainUiState> = MutableStateFlow(
        MainUiState(
            partnerList = mutableListOf(),
            ratingsList = mutableListOf(),
            selectedPartner = null
        )
    )
    val mainUiState: StateFlow<MainUiState> = uiState.asStateFlow()

    init {
        getPartnerData()
    }

    fun getPartnerData() {
        viewModelScope.launch {
            val partnerList = partnerAppUseCase.getPartnerList().sortedByDescending { it.rating }
            val ratingsList = partnerList.groupBy { it.rating!! }.map {
                RatingsList(
                    rating = it.key,
                    partners = it.value
                )
            }
            println(ratingsList)
            if (partnerList.isEmpty()) {
                uiState.update { currentState ->
                    currentState.copy(
                        isServerError = true
                    )
                }
            } else {
                uiState.update { currentState ->
                    currentState.copy(
                        partnerList = partnerList,
                        ratingsList = ratingsList
                    )
                }
            }
        }
    }

    fun updateSelectedPartner(partner: PartnerModel) {
        uiState.update { currentState ->
            currentState.copy(
                selectedPartner = partner
            )
        }
    }

}