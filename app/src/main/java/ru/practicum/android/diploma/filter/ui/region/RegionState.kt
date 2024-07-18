package ru.practicum.android.diploma.filter.ui.region

import ru.practicum.android.diploma.filter.domain.models.Region

sealed class RegionState {
    data object Loading : RegionState()
    data class Content(val regionsList: List<Region>) : RegionState()
    data object Error : RegionState()
}
