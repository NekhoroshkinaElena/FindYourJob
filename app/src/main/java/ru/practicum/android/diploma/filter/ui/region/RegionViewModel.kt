package ru.practicum.android.diploma.filter.ui.region

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.filter.domain.api.FilterInteractor
import ru.practicum.android.diploma.search.domain.utils.ResponseData

class RegionViewModel(
    private val filterInteractor: FilterInteractor
) : ViewModel() {

    private val _screenState = MutableLiveData<RegionState>(RegionState.Loading)
    val screenState: LiveData<RegionState> = _screenState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            filterInteractor.getRegions(SELECTED_COUNTRY).collect { data ->
                when (data) {
                    is ResponseData.Data -> _screenState.postValue(RegionState.Content(data.value))
                    is ResponseData.Error -> _screenState.postValue(RegionState.Error)
                }
            }
        }
    }

    private companion object {
        // временное решение 113 - Россия, 16 - Беларусь
        const val SELECTED_COUNTRY = 113
    }

}
