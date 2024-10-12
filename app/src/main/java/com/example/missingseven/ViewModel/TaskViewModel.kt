package com.example.missingseven.ViewModel

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.missingseven.Database.*
import com.example.missingseven.Database.Entity.TaskType
import com.example.missingseven.Database.Repository.CountryRepository
import com.example.missingseven.Database.Repository.ItemRepository
import com.example.missingseven.Database.Repository.PlayerRepository
import com.example.missingseven.Database.Repository.TaskRepository
import com.example.missingseven.Model.TaskConverter
import com.example.missingseven.Model.TaskUiState
import com.example.missingseven.Navigation.NavControl
import com.example.missingseven.Navigation.Screen
import com.example.missingseven.Screen.sendMail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/***
 * view model class for the task screen
 */
@HiltViewModel
class TaskViewModel @Inject constructor(
    private val taskRepository: TaskRepository,
    private val countryRepository: CountryRepository,
    private val itemRepository: ItemRepository,
    private val playerRepository: PlayerRepository,
    private val preferenceManager: PrefManager,
    private val dataInitializer: DataInitializer
): ViewModel() {
    lateinit var navControl: NavControl
    private val currentTaskId = mutableStateOf(-1)
    private var taskListCount = 0
    val allFetched = mutableStateOf(false)
    private val insertCompleted = MutableLiveData(0)
    private val deleteCompleted = mutableStateOf(0)
    private val allTasks: MutableList<TaskType> = mutableListOf()
    private val allUiStates: MutableList<TaskUiState> = mutableListOf()

    lateinit var filterTask: TaskType.FilterTask
    lateinit var filterUiState: TaskUiState.FilterTask

    private fun dataInitialize(){
        if (!preferenceManager.getBoolean(BooleanPair.DataInitialized)){
            insertTasks()
        } else {
            initTasks()
        }
    }

    fun setup(navControl: NavControl){
        this.navControl = navControl
    }

    private fun variableReset(){
        currentTaskId.value = -1
        taskListCount = 0
        allFetched.value = false
        insertCompleted.value = 0
        deleteCompleted.value = 0
        allTasks.clear()
        allUiStates.clear()
        preferenceManager.putInt(PrefManager.CURR_TASK_ID, -1)
        preferenceManager.putBoolean(PrefManager.DATA_INITIALIZED, false)
        preferenceManager.putBoolean(PrefManager.SKIP, false)
    }

    private fun insertTasks(){
        viewModelScope.launch {
            taskRepository.insertAllReadingTasks(dataInitializer.getAllReadingTasks()){
                insertCallback()
            }
        }
        viewModelScope.launch {
            taskRepository.insertAllMultipleChoiceTasks(dataInitializer.getAllMultipleChoiceTasks()){
                insertCallback()
            }
        }
        viewModelScope.launch {
            taskRepository.insertAllSlidingScaleTasks(dataInitializer.getAllSlidingScaleTasks()){
                insertCallback()
            }
        }
        viewModelScope.launch {
            taskRepository.insertAllFilterTasks(dataInitializer.getFilterTasks()){
                insertCallback()
            }
        }
        viewModelScope.launch {
            taskRepository.insertAllShortAnswerTasks(dataInitializer.getShortAnswerTasks()){
                insertCallback()
            }
        }
        viewModelScope.launch {
            taskRepository.insertWelcomeTasks(dataInitializer.getWelcomeTask()){
                insertCallback()
            }
        }
        viewModelScope.launch {
            taskRepository.insertLRTasks(dataInitializer.getLiteracyRateTasks()){
                insertCallback()
            }
        }
        viewModelScope.launch {
            taskRepository.insertGLRTasks(dataInitializer.getGLRTasks()){
                insertCallback()
            }
        }
        viewModelScope.launch {
            countryRepository.insertAllCountries(dataInitializer.getAllCountries()){
                insertCallback()
            }
        }
        viewModelScope.launch {
            playerRepository.insertPlayers(dataInitializer.getAllPlayer()){
                insertCallback()
            }
        }
        viewModelScope.launch {
            itemRepository.insertAllItems(dataInitializer.getAllItem()){
                insertCallback()
            }
        }
    }

    private fun deleteTasks(){
        viewModelScope.launch {
            taskRepository.deleteReadingTasks { deleteCallback() }
        }

        viewModelScope.launch {
            taskRepository.deleteMultipleChoiceTasks { deleteCallback() }
        }

        viewModelScope.launch {
            taskRepository.deleteSlidingScaleTasks { deleteCallback() }
        }

        viewModelScope.launch {
            taskRepository.deleteFilterTasks { deleteCallback() }
        }

        viewModelScope.launch {
            taskRepository.deleteShortAnswerTasks { deleteCallback() }
        }
        viewModelScope.launch {
            taskRepository.deleteWelcomeTasks { deleteCallback() }
        }
        viewModelScope.launch {
            taskRepository.deleteLRTasks { deleteCallback() }
        }

        viewModelScope.launch {
            taskRepository.deleteGLRTasks { deleteCallback() }
        }

        viewModelScope.launch {
            countryRepository.deleteCountries { deleteCallback() }
        }

        viewModelScope.launch {
            itemRepository.deleteAllItems { deleteCallback() }
        }

        viewModelScope.launch {
            playerRepository.deleteAllPlayers { deleteCallback() }
        }
    }

    private fun deleteCallback(){
        deleteCompleted.value = deleteCompleted.value.plus(1)
        if (deleteCompleted.value == DataInitializer.INSERT_NUM){
            preferenceManager.putBoolean(PrefManager.IS_UNDER_RESETTING, false)
            dataInitialize()
        }
    }

    private fun insertCallback(){
        insertCompleted.value = insertCompleted.value?.plus(1)
        if (insertCompleted.value == DataInitializer.INSERT_NUM){
            preferenceManager.putBoolean(PrefManager.DATA_INITIALIZED, true)
            initTasks()
        }
    }

    private fun initTasks(){
        viewModelScope.launch {
            taskRepository.getReadingTasks {
                updateTasks(it)
            }
        }
        viewModelScope.launch {
            taskRepository.getMultipleChoiceTasks {
                updateTasks(it)
            }
        }
        viewModelScope.launch {
            taskRepository.getSlidingScaleTasks {
                updateTasks(it)
            }
        }
        viewModelScope.launch {
            taskRepository.getFilterTasks {
                updateTasks(it)
            }
        }
        viewModelScope.launch {
            taskRepository.getWelcomeTasks {
                updateTasks(it)
            }
        }
        viewModelScope.launch {
            taskRepository.getShortAnswerTasks {
                updateTasks(it)
            }
        }
        viewModelScope.launch {
            taskRepository.getLRTasks {
                updateTasks(it)
            }
        }

        viewModelScope.launch {
            taskRepository.getGLRTasks {
                updateTasks(it)
            }
        }
    }


    private fun updateTasks(list: List<TaskType>){
        if (taskListCount != TaskType.TASK_TYPE_NUM && !isUnderResetting()){
            safeCheckUpdate(list)
            if (taskListCount == TaskType.TASK_TYPE_NUM){
                allFetched.value = true
                allTasks.sortBy { it.tid }
                setTaskUiStates()
                checkSharedPreference()
                navControl.navigate(Screen.Home.route, Screen.Task.route)
            }
        }
    }

    private fun safeCheckUpdate(list: List<TaskType>){
        if (!allTasks.containsAll(list)){
            if (list[0] is TaskType.FilterTask){
                filterTask = list[0] as TaskType.FilterTask
                TaskConverter.databaseEntityToUiState(filterTask)?.let {
                    filterUiState = it as TaskUiState.FilterTask
                }
            } else {
                allTasks.addAll(list)
            }
            taskListCount += 1
        }
    }

    private fun isUnderResetting(): Boolean {
        return preferenceManager.getBoolean(BooleanPair.IsUnderResetting)
    }

    private fun checkSharedPreference() {
        val number = preferenceManager.getInt(IntPair.CurrTask)
        if (number == -1){
            currentTaskId.value  = allTasks[0].tid
        } else {
            for (task in allTasks){
                if (task.tid == number){
                    currentTaskId.value  = task.tid
                }
            }
        }
    }

    private fun putCurrTaskToSharedPreference(){
        preferenceManager.putInt(PrefManager.CURR_TASK_ID, currentTaskId.value)
    }

    private fun getCurrentTaskType(): TaskType? {
        getCurrentTask()?.let {
            return allTasks[it.tid]
        }
        return null
    }

    private fun setTaskUiStates(){
        allTasks.forEach {
            TaskConverter.databaseEntityToUiState(it)?.let { task -> allUiStates.add(task) }
        }
    }

    fun getCurrentTask(): TaskUiState? {
        return if (currentTaskId.value == -1) null else allUiStates[currentTaskId.value]
    }

    fun onNextClicked(){
        getCurrentTask()?.let {
            if (it.completed.value){
                if (it is TaskUiState.ReadingTask && it.isSpecial){
                    navControl.navigate(Screen.Task.route, Screen.Filter.route)
                } else {
                    currentTaskId.value += 1
                    putCurrTaskToSharedPreference()
                }
            }
        }
    }

    fun onFilterExitClicked(){
        navControl.navigateBack()
        navControl.navigateBack()
        currentTaskId.value += 1
        putCurrTaskToSharedPreference()
    }

    fun isLastTask(): Boolean{
        return currentTaskId.value == allTasks.last().tid
    }

    fun isFirstTask(): Boolean {
        return currentTaskId.value == 0 || (
                preferenceManager.getBoolean(BooleanPair.Skip) && currentTaskId.value == 9)
    }

    fun onBackClicked(){
        currentTaskId.value -= 1
        putCurrTaskToSharedPreference()
    }

    fun isResumeAble(): Boolean {
        return preferenceManager.getInt(IntPair.CurrTask) != -1
    }

    fun resume(){
        if (allFetched.value){
            navControl.navigate(Screen.Home.route, Screen.Task.route)
        } else {
            dataInitialize()
        }
    }


    fun startNewWorkshop(){
        variableReset()
        preferenceManager.putBoolean(PrefManager.IS_UNDER_RESETTING, true)
        deleteTasks()
    }

    fun updateChooseHandler(index: Int){
        (getCurrentTask() as TaskUiState.MultipleChoiceTask).apply {
            studentAnswerIndex.value = index
            completed.value = index == correctIndex
            (getCurrentTaskType() as TaskType.MultipleChoiceTask).let { task ->
                task.studentAnswerIndex = index
                task.completed = completed.value
                viewModelScope.launch {
                    taskRepository.updateMultipleChoiceTask(task)
                }
            }
        }
    }

    fun slidingScaleTaskChangeHandler(cur: Int){
        (getCurrentTask() as TaskUiState.SlidingScaleTask).apply {
            val prevCompleted = completed.value
            current.value = cur
            completed.value = (correct - offset) <=  cur && cur <= (correct + offset)
            if (completed.value != prevCompleted){
                (getCurrentTaskType() as TaskType.SlidingScaleTask).let { task ->
                task.current = cur
                task.completed = completed.value
                viewModelScope.launch {
                    taskRepository.updateSlidingScaleTask(task) }
                }
            }
        }
    }

    fun shortAnswerTaskValueChangeHandler(value: String){
        (getCurrentTask() as TaskUiState.ShortAnswerTask).apply {
            answer.value = value
            completed.value = false
        }
    }

    fun shortAnswerSaveHandler(){
        (getCurrentTask() as TaskUiState.ShortAnswerTask).apply {
            completed.value = true
            (getCurrentTaskType() as TaskType.ShortAnswerTask).let { task ->
                task.completed = true
                task.answer = answer.value
                viewModelScope.launch {
                    taskRepository.updateShortAnswerTask(task)
                }
            }
        }
    }

    fun submitAnswerHandler(context: Context){
        var text = "Student name: ${preferenceManager.getString(StringPair.Name)}\n\n"
        allTasks.filterIsInstance<TaskType.ShortAnswerTask>().forEach {
            text += "Question:\n${it.question}\nAnswer:\n${it.answer}\n\n"
        }
        preferenceManager.getString(StringPair.Email)?.let {
            sendMail(
                context = context,
                to = it,
                subject = "Water For The World Workshop Answers",
                text = text
            )
        }
    }

    fun welcomeDetailClicked(){
        navControl.navigate(Screen.Task.route, Screen.WorkshopContact.route)
    }

    fun loginClicked(){
        navControl.navigate(Screen.Task.route, Screen.Login.route)
    }

    fun navigateBack(){
        navControl.navigateBack()
    }

    fun loginHandler(email: String, name: String){
        preferenceManager.putString(PrefManager.EMAIL, email)
        preferenceManager.putString(PrefManager.NAME, name)
        navigateBack()
    }


    fun skip(){
        currentTaskId.value = 9
        preferenceManager.putInt(PrefManager.CURR_TASK_ID, 9)
        preferenceManager.putBoolean(PrefManager.SKIP, true)
    }
}
