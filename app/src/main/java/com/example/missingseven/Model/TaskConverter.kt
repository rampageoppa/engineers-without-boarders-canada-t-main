package com.example.missingseven.Model

import androidx.compose.runtime.mutableStateOf
import com.example.missingseven.Database.Entity.TaskType
import com.example.missingseven.R

/***
 * static class to convert database model to ui model for [TaskType]
 */
class TaskConverter {

    companion object {

        fun databaseEntityToUiState(taskType: TaskType?): TaskUiState?{
            return taskType.run {
                when (this){
                    is TaskType.ReadingTask -> {
                        TaskUiState.ReadingTask(
                            tid,
                            mutableStateOf(completed),
                            header,
                            subtitle,
                            content,
                            isSpecial
                        )
                    }
                    is TaskType.MultipleChoiceTask -> {
                        TaskUiState.MultipleChoiceTask(
                            tid,
                            mutableStateOf(completed),
                            header,
                            options,
                            correctIndex,
                            mutableStateOf(studentAnswerIndex),
                            popup
                        )
                    }
                    is TaskType.SlidingScaleTask -> {
                        TaskUiState.SlidingScaleTask(
                            tid,
                            mutableStateOf(completed),
                            content,
                            subtitle,
                            start,
                            end,
                            offset,
                            unit,
                            correct,
                            mutableStateOf(current),
                            tooSmallInfo,
                            tooLargeInfo,
                            correctInfo
                        )
                    }
                    is TaskType.FilterTask -> {
                        TaskUiState.FilterTask(
                            tid,
                            mutableStateOf(completed),
                            pid
                        )
                    }
                    is TaskType.ShortAnswerTask -> {
                        TaskUiState.ShortAnswerTask(
                            tid,
                            mutableStateOf(completed),
                            header,
                            question,
                            mutableStateOf(answer),
                            isLast
                        )
                    }
                    is TaskType.WelcomeTask -> {
                        TaskUiState.WelcomeTask(
                            tid,
                            mutableStateOf(completed),
                            "Welcome!"
                        )
                    }
                    is TaskType.LiteracyRateTask -> {
                        TaskUiState.LiteracyRateTask(
                            tid,
                            mutableStateOf(completed),
                            header,
                            subtitle,
                            CanadaRate,
                            GermanyRate,
                            GhanaRate,
                            KenyaRate,
                            KuwaitRate,
                            MalawiRate,
                            SouthAfricaRate
                        )
                    }
                    is TaskType.GlobalLiteracyRateTask -> {
                        TaskUiState.GlobalLiteracyRateTask(
                            tid,
                            mutableStateOf(completed),
                            header,
                            content,
                            getGLRImageId(tid),
                            hyperlinkText,
                            hyperlink
                        )
                    }
                    else -> null
                }
            }
        }

        private fun getGLRImageId(tid: Int): Int {
            return when (tid){
                5 -> R.drawable.glrm
                8 -> R.drawable.gep
                else -> R.drawable.glrm
            }
        }
    }
}