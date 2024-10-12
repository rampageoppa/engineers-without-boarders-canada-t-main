package com.example.missingseven.Screen

import androidx.compose.runtime.Composable
import com.example.missingseven.Component.*
import com.example.missingseven.Model.TaskUiState
import com.example.missingseven.ViewModel.TaskViewModel

/***
 * composable function for task screen
 */
@Composable
fun TaskScreen(
    viewModel: TaskViewModel
){
    TaskTemplate(
        content = {
            when (viewModel.getCurrentTask()){
                is TaskUiState.ReadingTask -> {
                    ReadingTaskBody(
                        task = viewModel.getCurrentTask() as TaskUiState.ReadingTask,
                        skipHandler = viewModel::navigateBack
                    )
                }
                is TaskUiState.MultipleChoiceTask -> {
                    MultipleChoiceTaskBody({index->viewModel.updateChooseHandler(index)},
                        viewModel.getCurrentTask() as TaskUiState.MultipleChoiceTask)
                }
                is TaskUiState.SlidingScaleTask -> {
                    SlidingScaleTaskBody({curr -> viewModel.slidingScaleTaskChangeHandler(curr)},
                        viewModel.getCurrentTask() as TaskUiState.SlidingScaleTask)
                }
                is TaskUiState.ShortAnswerTask -> {
                    ShortAnswerTaskBody({viewModel.shortAnswerSaveHandler()},
                        viewModel.getCurrentTask() as TaskUiState.ShortAnswerTask,
                        {value -> viewModel.shortAnswerTaskValueChangeHandler(value)},
                        submitHandler = {context -> viewModel.submitAnswerHandler(context) }
                    )
                }
                is TaskUiState.WelcomeTask -> {
                    WelcomeTaskBody(viewModel = viewModel)
                }
                is TaskUiState.LiteracyRateTask -> {
                    LiteracyRateTaskBody(
                        task = viewModel.getCurrentTask() as TaskUiState.LiteracyRateTask)
                }
                is TaskUiState.GlobalLiteracyRateTask -> {
                    GlobalLiteracyRateBody(
                        task = viewModel.getCurrentTask() as TaskUiState.GlobalLiteracyRateTask)
                }
                else -> {}
            }
        },
        nextHandler = {viewModel.onNextClicked()},
        backHandler = {viewModel.onBackClicked()},
        finishHandler = { viewModel.navigateBack()},
        taskUiState = viewModel.getCurrentTask()!!,
        shouldShowFirst = !viewModel.isFirstTask(),
        shouldShowLast = !viewModel.isLastTask()
    )
}