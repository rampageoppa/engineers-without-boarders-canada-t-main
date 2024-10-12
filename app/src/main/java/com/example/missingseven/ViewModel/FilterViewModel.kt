package com.example.missingseven.ViewModel

import androidx.lifecycle.ViewModel
import com.example.missingseven.Database.Entity.Country
import com.example.missingseven.Database.Entity.Player
import com.example.missingseven.Database.Repository.CountryRepository
import com.example.missingseven.Database.Repository.ItemRepository
import com.example.missingseven.Database.Repository.PlayerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.example.missingseven.Navigation.NavControl
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.missingseven.Database.Entity.reset
import com.example.missingseven.Database.Entity.resetFilter
import com.example.missingseven.Model.*
import com.example.missingseven.Navigation.Screen
import kotlinx.coroutines.launch

/***
 * view model class used for the water filter exercise
 */
@HiltViewModel
class FilterViewModel @Inject constructor(
    private val playerRepository: PlayerRepository,
    private val countryRepository: CountryRepository,
    private val itemRepository: ItemRepository
) : ViewModel() {
    //FilterTask which stores the current task
    lateinit var filterTask: TaskUiState.FilterTask

    //FilterStack which stores the filter stack
    lateinit var filterStack: FilterStack

    // current player
    lateinit var player: Player

    //the list of all countries
    lateinit var countries: List<Country>

    //store the playerUiState
    lateinit var playerUiState: PlayerUiState

    //store the navigation controller
    lateinit var navControl: NavControl

    //indicate whether setup tasks are completed
    val setupCompleted: MutableState<Boolean> = mutableStateOf(false)

    // store the key-value pair of (Iid, Count) for each item in the shop
    // count means how many items are selected in the shop
    val shopIidCountMap: MutableMap<Int, MutableState<Int>> = mutableMapOf()

    // store all items by the key-value pair of the items iid and the ItemUiState
    val allIIdItemsMap: MutableMap<Int, ItemUiState> = mutableMapOf()

    lateinit var items: List<ItemUiState>

    private val fetchCount = mutableStateOf(0)

    fun setup(control: NavControl, filter:TaskUiState.FilterTask){
        setupCompleted.value = false
        fetchCount.value = 0
        navControl = control
        filterTask = filter
        fetchPlayer()
        fetchCountries()
        fetchItems()
    }

    fun fetchPlayer(){
        viewModelScope.launch{
            playerRepository.getPlayers {
                player = it[0]
                fetchCallback()
            }
        }
    }

    fun fetchCompleted() = fetchCount.value == FETCH_COUNT

    private fun fetchCallback(){
        if (!fetchCompleted()){
            fetchCount.value += 1
        }
    }

    fun getParallelCountryList(): Pair<List<Country>, List<Country>>{
        return Pair(countries.filter { it.cid < 4 }, countries.filter { it.cid >= 4 })
    }

    fun fetchCountries(){
        viewModelScope.launch{
            countryRepository.getAllCountries {
                countries = it
                fetchCallback()
            }
        }
    }

    fun fetchItems() {
        viewModelScope.launch {
            itemRepository.getItems {
                for (item in it) {
                    shopIidCountMap[item.iid] = mutableStateOf(0)
                    allIIdItemsMap[item.iid] = ItemConverter.databaseEntityToUiState(item)
                }
                items = it.map { item -> ItemConverter.databaseEntityToUiState(item) }
                fetchCallback()
            }
        }
    }

    fun setupPlayerUiState(){
        getPlayerCountry()?.let {
            playerUiState = PlayerConverter.databaseEntityToUiState(player, it.name, it.instruction)
        }
    }

    fun setupStack(){
        val itemList = mutableListOf<ItemUiState?>()
        var currTop = 0
        for (i in 0 until FilterStack.MAX_LAYER){
            if (playerUiState.getLayerValueByIndex(i).value != -1){
                itemList.add(allIIdItemsMap[playerUiState.getLayerValueByIndex(i).value])
                currTop += 1
            } else {
                itemList.add(null)
            }
        }
        filterStack = FilterStack(
            itemList,
            mutableStateOf(currTop),
            neck = mutableStateOf(allIIdItemsMap[playerUiState.neck.value]),
            mouth = mutableStateOf(allIIdItemsMap[playerUiState.mouth.value])
        )

        setupCompleted.value = true
    }

    fun countrySelect(country: Country){
        player.cid = country.cid
        player.curr_money = country.money
        viewModelScope.launch {
            playerRepository.updatePlayer(player)
        }
        setupPlayerUiState()
        setupStack()
        navControl.navigate(Screen.Task.route, Screen.Filter.route)
    }

    fun getInstruction(): String{
        return playerUiState.instruction
    }

    fun openInstruction(){
        navControl.navigate(Screen.Filter.route, Screen.Instruction.route)
    }

    fun onUndoClick(){
        playerUiState = playerUiState.resetLayers(getPlayerCountry()?.money ?: 0)
        player = player.resetFilter(playerUiState.currMoney.value)
        setupStack()
        viewModelScope.launch {
            playerRepository.updatePlayer(player)
        }
    }

    fun navigateBack(){
        navControl.navigateBack()
    }

    fun getPriceMultiplier() = getPlayerCountry()?.priceMultiplier ?: 1

    fun addItem(item: ItemUiState){
        if (player.curr_money < item.getMultipliedPrice(getPriceMultiplier()) || filterStack.isFull()) return
        playerUiState.currMoney.value -= item.getMultipliedPrice(getPriceMultiplier())
        player.curr_money = playerUiState.currMoney.value
        filterStack.add(item)
        player.updatePlayerByIndex(filterStack.topIndex.value - 1, item.iid)
        viewModelScope.launch {
            playerRepository.updatePlayer(player)
        }
    }

    fun getPlayerCountry(): Country?{
        for (country in countries) {
            if (player.cid == country.cid){
                return country
            }
        }
        return null
    }

    fun onTestClicked(){
        filterStack.cleaned = true
        navControl.navigate(
            backRoute = Screen.Filter.route,
            destinationRoute = Screen.Test.route
        )
    }

    fun tryAnotherCountry(){
        setupCompleted.value = false
        player = reset()
        viewModelScope.launch {
            playerRepository.updatePlayer(player)
        }
        navigateBack()
    }

    companion object {
        const val FETCH_COUNT = 3
    }

}