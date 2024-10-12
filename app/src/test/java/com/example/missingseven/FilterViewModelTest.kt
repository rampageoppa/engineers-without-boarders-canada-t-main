package com.example.missingseven

import androidx.compose.runtime.mutableStateOf
import androidx.core.content.res.ResourcesCompat.ID_NULL
import com.example.missingseven.Database.DAO.CountryDAO
import com.example.missingseven.Database.DAO.ItemDAO
import com.example.missingseven.Database.DAO.PlayerDAO
import com.example.missingseven.Database.Entity.Country
import com.example.missingseven.Database.Entity.Item
import com.example.missingseven.Database.Entity.Player
import com.example.missingseven.Database.IntPair
import com.example.missingseven.Database.PrefManager
import com.example.missingseven.Database.Repository.CountryRepository
import com.example.missingseven.Database.Repository.ItemRepository
import com.example.missingseven.Database.Repository.PlayerRepository
import com.example.missingseven.Model.*
import com.example.missingseven.Navigation.NavControl
import com.example.missingseven.ViewModel.FilterViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.doNothing
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

/**
 * unit tests for filter view model
 */
@OptIn(ExperimentalCoroutinesApi::class)
class FilterViewModelTest {
    private val playerDAO: PlayerDAO = mock()
    private val countryDAO: CountryDAO = mock()
    private val itemDAO: ItemDAO = mock()

    private val prefManager: PrefManager = mock()
    private val playerRepository = PlayerRepository(prefManager, playerDAO)
    private val countryRepository = CountryRepository(countryDAO, prefManager)
    private val itemRepository = ItemRepository(prefManager, itemDAO)

    private lateinit var viewModel: FilterViewModel
    private val dispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup(){
        viewModel = FilterViewModel(
            playerRepository,
            countryRepository,
            itemRepository
        )
        Dispatchers.setMain(dispatcher)
        whenever(prefManager.getInt(IntPair.CurrTask)).thenReturn(9)
    }


    @Test
    fun testFilterViewModelSetup() = runBlocking {
        val player1 = Player(2, 222, 100, 10, 20, 11, 22, 33,
            44, 55, 66,77, 88, true)

        whenever(playerDAO.getAllPlayers()).thenReturn(
            flow {
                emit(listOf(player1))
            }
        )
        val control = NavControl(mock())
        val filter = TaskUiState.FilterTask(1, mutableStateOf(true),111)

        viewModel.setup(control, filter)

        assertEquals(control, viewModel.navControl)
        assertEquals(filter, viewModel.filterTask)
        assertEquals(viewModel.filterTask.tid, 1)
        assertEquals(viewModel.filterTask.completed.value, true)
        assertEquals(viewModel.filterTask.pid, 111)
    }

    @Test
    fun testFetchPlayer() = runBlocking{
        val player1 = Player(2, 222, 100, 10, 20, 11, 22, 33,
            44, 55, 66,77, 88, true)

        whenever(playerDAO.getAllPlayers()).thenReturn(
            flow {
                emit(listOf(player1))
            }
        )

        viewModel.fetchPlayer()

        assertEquals(viewModel.player, player1)
        assertEquals(viewModel.player.pid, 2)
        assertEquals(viewModel.player.cid, 222)
        assertEquals(viewModel.player.curr_money, 100)
        assertEquals(viewModel.player.neck, 10)
        assertEquals(viewModel.player.mouth, 20)
        assertEquals(viewModel.player.layer0, 11)
        assertEquals(viewModel.player.layer1, 22)
        assertEquals(viewModel.player.layer2, 33)
        assertEquals(viewModel.player.layer3, 44)
        assertEquals(viewModel.player.layer4, 55)
        assertEquals(viewModel.player.layer5, 66)
        assertEquals(viewModel.player.layer6, 77)
        assertEquals(viewModel.player.layer7, 88)
    }

    @Test
    fun testFetchCountries() = runBlocking {
        val country1 = Country(
            cid = 1,
            name = "Country 1",
            money = 1000,
            instruction = "Instruction 1"
        )
        val country2 = Country(
            cid = 2,
            name = "Country 2",
            money = 2000,
            instruction = "Instruction 2"
        )

        whenever(countryDAO.getAllCountries()).thenReturn(
            flow {
                emit(
                    listOf(country1, country2)
                )
            }
        )
        viewModel.fetchCountries()
        assertEquals(country1, viewModel.countries[0])
        assertEquals(country2, viewModel.countries[1])
    }

    @Test
    fun testFetchItems() = runBlocking {
        val item1 = Item(1, "item1", 10, 3, 5.0F,
            3.0F, listOf(0.8F, 0.5F), listOf(0.7F, 0.4F))
        val item2 = Item(2, "item2", 20, 10, 10.0F,
            10.0F, listOf(0.85F, 0.55F), listOf(0.75F, 0.45F))
        val item3 = Item(3, "item3", 30, 20, 15.0F,
            12.0F, listOf(0.4F, 0.2F), listOf(0.3F, 0.1F))


        whenever(itemDAO.getAllItems()).thenReturn(
            flow {
                emit(listOf(item1, item2, item3))
            }
        )
        viewModel.fetchItems()
        assertEquals(0, viewModel.shopIidCountMap[1]?.value)
        assertEquals(0, viewModel.shopIidCountMap[2]?.value)
        assertEquals(0, viewModel.shopIidCountMap[3]?.value)
        assertEquals(ItemConverter.databaseEntityToUiState(item1), viewModel.allIIdItemsMap[1])
        assertEquals(ItemConverter.databaseEntityToUiState(item2), viewModel.allIIdItemsMap[2])
        assertEquals(ItemConverter.databaseEntityToUiState(item3), viewModel.allIIdItemsMap[3])
        assertEquals(1, ItemConverter.databaseEntityToUiState(item1).iid)
        assertEquals(2, ItemConverter.databaseEntityToUiState(item2).iid)
        assertEquals(3, ItemConverter.databaseEntityToUiState(item3).iid)
        assertEquals("item1", ItemConverter.databaseEntityToUiState(item1).name)
        assertEquals("item2", ItemConverter.databaseEntityToUiState(item2).name)
        assertEquals("item3", ItemConverter.databaseEntityToUiState(item3).name)
        assertEquals(10, ItemConverter.databaseEntityToUiState(item1).quantity)
        assertEquals(20, ItemConverter.databaseEntityToUiState(item2).quantity)
        assertEquals(30, ItemConverter.databaseEntityToUiState(item3).quantity)
        assertEquals(3, ItemConverter.databaseEntityToUiState(item1).price)
        assertEquals(10, ItemConverter.databaseEntityToUiState(item2).price)
        assertEquals(20, ItemConverter.databaseEntityToUiState(item3).price)
        assertEquals(5.0F, ItemConverter.databaseEntityToUiState(item1).strength)
        assertEquals(10.0F, ItemConverter.databaseEntityToUiState(item2).strength)
        assertEquals(15.0F, ItemConverter.databaseEntityToUiState(item3).strength)
        assertEquals(3.0F, ItemConverter.databaseEntityToUiState(item1).cleanedStrength)
        assertEquals(10.0F, ItemConverter.databaseEntityToUiState(item2).cleanedStrength)
        assertEquals(12.0F, ItemConverter.databaseEntityToUiState(item3).cleanedStrength)
        assertEquals(listOf(0.8F, 0.5F), ItemConverter.databaseEntityToUiState(item1).effectiveness)
        assertEquals(listOf(0.85F, 0.55F), ItemConverter.databaseEntityToUiState(item2).effectiveness)
        assertEquals(listOf(0.4F, 0.2F), ItemConverter.databaseEntityToUiState(item3).effectiveness)
        assertEquals(listOf(0.7F, 0.4F), ItemConverter.databaseEntityToUiState(item1).cleanedEffectiveness)
        assertEquals(listOf(0.75F, 0.45F), ItemConverter.databaseEntityToUiState(item2).cleanedEffectiveness)
        assertEquals(listOf(0.3F, 0.1F), ItemConverter.databaseEntityToUiState(item3).cleanedEffectiveness)

    }


//    @Test
//    fun testSelectItem(){
//        val item1 = Item(1, "item1", 10, 3, 5.0F,
//            3.0F, listOf(0.8F, 0.5F), listOf(0.7F, 0.4F))
//        val item2 = Item(2, "item2", 20, 10, 10.0F,
//            10.0F, listOf(0.85F, 0.55F), listOf(0.75F, 0.45F))
//        val item3 = Item(3, "item3", 30, 20, 15.0F,
//            12.0F, listOf(0.4F, 0.2F), listOf(0.3F, 0.1F))
//        val player1 = Player(2, 222, 100, 10, 20, 11, 22, 33,
//            44, 55, 66,77, 88, true)
//
//        whenever(playerDAO.getAllPlayers()).thenReturn(
//            flow {
//                emit(listOf(player1))
//            }
//        )
//        val control = NavControl(mock())
//        val filter = TaskUiState.FilterTask(1, mutableStateOf(true),111)
//
//        viewModel.setup(control, filter)
//        assertEquals(viewModel.filterStack.add())
//    }

    @Test
    fun testGetPlayerCountrySuccess(){
        viewModel.countries = listOf(
            COUNTRY1, COUNTRY2, COUNTRY3
        )
        viewModel.player = PLAYER
        val result = viewModel.getPlayerCountry()
        assertEquals(COUNTRY2, result)
    }

    @Test
    fun testGetPlayerCountryFail(){
        viewModel.countries = listOf()
        viewModel.player = PLAYER
        val result = viewModel.getPlayerCountry()
        assertEquals(null, result)
    }


    @Test
    fun testSetupPlayerUiState(){
        viewModel.player = PLAYER
        viewModel.countries = listOf(COUNTRY2)
        viewModel.setupPlayerUiState()
        assertEquals(viewModel.playerUiState.pid, PLAYER_UI_STATE.pid)
        assertEquals(viewModel.playerUiState.cid, PLAYER_UI_STATE.cid)
        assertEquals(viewModel.playerUiState.currMoney.value, PLAYER_UI_STATE.currMoney.value)
        assertEquals(viewModel.playerUiState.countryName, PLAYER_UI_STATE.countryName)
        assertEquals(viewModel.playerUiState.instruction, PLAYER_UI_STATE.instruction)
    }

    @Test
    fun testGetParallelCountryList(){
        viewModel.countries = listOf(COUNTRY2, COUNTRY1, COUNTRY3)
        val result = viewModel.getParallelCountryList()
        assertEquals(listOf(COUNTRY2, COUNTRY1, COUNTRY3) to emptyList<Country>(), result)
    }

    @Test
    fun testOnTestClicked(){
        val navControl: NavControl = mock()
        viewModel.navControl = navControl
        doNothing().`when`(navControl).navigate(any(), any())
        viewModel.filterStack = FILTER_STACK
        viewModel.onTestClicked()
        assertEquals(viewModel.filterStack.cleaned, true)
    }

    @Test
    fun testTryAnotherCountry() = runBlocking {
        val navControl: NavControl = mock()
        viewModel.player = PLAYER
        viewModel.navControl = navControl
        whenever(playerDAO.updatePlayer(any())).then {  }
        doNothing().`when`(navControl).navigateBack()
        viewModel.tryAnotherCountry()
        assertEquals(false, viewModel.setupCompleted.value)
        assertEquals(0, viewModel.player.pid)
        assertEquals(-1, viewModel.player.cid)
        assertEquals(0, viewModel.player.curr_money)
        assertEquals(-1, viewModel.player.neck)
        assertEquals(-1, viewModel.player.mouth)
        assertEquals(-1, viewModel.player.layer0)
        assertEquals(-1, viewModel.player.layer1)
        assertEquals(-1, viewModel.player.layer2)
        assertEquals(-1, viewModel.player.layer3)
        assertEquals(-1, viewModel.player.layer4)
        assertEquals(-1, viewModel.player.layer5)
        assertEquals(-1, viewModel.player.layer6)
        assertEquals(false, viewModel.player.mouthRubberBanded)
    }

    @Test
    fun testAddItemWhenNoMoney(){
        viewModel.player = PLAYER
        viewModel.addItem(ITEM)
        assertEquals(0, viewModel.player.curr_money)
    }

    fun testAddItemWhenFilterFull(){
        viewModel.player = PLAYER
        viewModel.player.curr_money = 1000000
        viewModel.filterStack = FILTER_STACK
        viewModel.filterStack.topIndex.value = 8
        viewModel.addItem(ITEM)
        assertEquals(1000000, viewModel.player.curr_money)
    }



    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }

    companion object {
        val COUNTRY1 = Country(
            cid = 1,
            name = "c1",
            money = 10,
            instruction = "i1"
        )
        val COUNTRY2 = Country(
            cid = 2,
            name = "c2",
            money = 20,
            instruction = "i2"
        )
        val COUNTRY3 = Country(
            cid = 3,
            name = "c3",
            money = 30,
            instruction = "i3"
        )

        val PLAYER = Player(
            pid = 1,
            cid = 2,
            curr_money = 0,
            neck = -1,
            mouth = -1,
            layer1 = -1,
            layer2 = -1,
            layer3 = -1,
            layer4 = -1,
            layer5 = -1,
            layer6 = -1,
            layer0 = -1,
            layer7 = -1,
            mouthRubberBanded = true
        )
        val PLAYER_UI_STATE = PlayerUiState(
            pid = 1,
            cid = 2,
            currMoney = mutableStateOf(0),
            neck = mutableStateOf(-1),
            neckRubberBanded = true,
            mouth = mutableStateOf(-1),
            layer7 = mutableStateOf(-1),
            layer0 = mutableStateOf(-1),
            layer6 = mutableStateOf(-1),
            layer5 = mutableStateOf(-1),
            layer4 = mutableStateOf(-1),
            layer3 = mutableStateOf(-1),
            layer2 = mutableStateOf(-1),
            layer1 = mutableStateOf(-1),
            countryName = "c2",
            instruction = "i2"
        )
        val FILTER_STACK = FilterStack(
            itemList = mutableListOf(),
            topIndex = mutableStateOf(0),
            neck = mutableStateOf(null),
            mouth = mutableStateOf(null),
            cleaned = false
        )

        val ITEM = ItemUiState(
            iid = 1,
            name = "i1",
            quantity = 0,
            price = 10,
            img = ID_NULL,
            strength = 1f,
            cleanedStrength = 1f,
            effectiveness = emptyList(),
            cleanedEffectiveness = emptyList()
        )
    }
}