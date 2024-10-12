package com.example.missingseven
import com.example.missingseven.Database.DAO.CountryDAO
import com.example.missingseven.Database.Entity.Country
import com.example.missingseven.Database.PrefManager
import com.example.missingseven.Database.Repository.CountryRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever


class CountryRepositoryTest {

    private val countryDAO: CountryDAO = mock()
    private val prefManager: PrefManager = mock()
    private lateinit var countryRepository: CountryRepository
    @Before
    fun setup(){
        countryRepository = CountryRepository(countryDAO, prefManager)
        whenever(prefManager.getInt(any())).thenReturn(9)
    }

    @Test
    fun testGetCountry() = runBlocking {
        whenever(countryDAO.getAllCountries()).thenReturn(
            flow {
                emit(emptyList())
            }
        )
        var result: List<Country>? = null
        countryRepository.getAllCountries {
            result = it
        }
        assertEquals(emptyList<Country>(), result)
    }

    @Test
    fun testAddCountry() = runBlocking {
        var result = false
        countryRepository.insertAllCountries(emptyList()){
            result = true
        }
        assertEquals(true, result)
    }

    @Test
    fun testDeleteCountry() = runBlocking {
        var result = false
        countryRepository.deleteCountries {
            result = true
        }
        assertEquals(true, result)
    }
}