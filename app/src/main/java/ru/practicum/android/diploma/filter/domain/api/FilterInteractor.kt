package ru.practicum.android.diploma.filter.domain.api

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.filter.domain.models.Country
import ru.practicum.android.diploma.filter.domain.models.Filter
import ru.practicum.android.diploma.filter.domain.models.Region
import ru.practicum.android.diploma.filter.domain.models.Sector
import ru.practicum.android.diploma.search.domain.utils.ResponseData

interface FilterInteractor {
    fun saveFilter(filter: Filter)
    fun loadFilter(): Filter
    fun getCountries(): Flow<ResponseData<List<Country>>>
    fun getRegions(id: Int): Flow<ResponseData<List<Region>>>
    fun getSectors(): Flow<ResponseData<List<Sector>>>
}
