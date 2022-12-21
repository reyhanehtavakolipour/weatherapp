package rtp.reyhanehtpour.weatherapp.ui.uiState


sealed class UiState {
    object LoadingUIState: UiState()
     open class SuccessUIState: UiState()
    open class ErrorUIState(val message: String): UiState()
    object EmptyState: UiState()
}





