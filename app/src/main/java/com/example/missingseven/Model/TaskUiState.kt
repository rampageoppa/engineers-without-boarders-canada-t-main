package com.example.missingseven.Model

import androidx.compose.runtime.MutableState


import com.example.missingseven.Database.Entity.TaskType

/***
 * data class of the ui model for [TaskType]
 */
sealed class TaskUiState(
    open val tid: Int,
    open val completed: MutableState<Boolean>,
    open val header: String,
    open val subtitle: String = ""
) {
    data class ReadingTask(
        override val tid: Int,
        override val completed:  MutableState<Boolean>,
        override val header: String,
        override val subtitle: String,
        val content: String,
        val isSpecial: Boolean = false
    ): TaskUiState(tid, completed, header, subtitle)

    data class MultipleChoiceTask(
        override val tid: Int,
        override val completed: MutableState<Boolean>,
        override val header: String,
        val options: List<String>,
        val correctIndex: Int,
        val studentAnswerIndex: MutableState<Int>,
        val popup: String
    ): TaskUiState(tid, completed, header)

    data class SlidingScaleTask(
        override val tid: Int,
        override val completed: MutableState<Boolean>,
        val content: String,
        override val subtitle: String,
        val start: Int,
        val end: Int,
        val offset: Int,
        val unit: String,
        val correct: Int,
        val current: MutableState<Int>,
        val tooSmallInfo: String,
        val tooLargeInfo: String,
        val correctInfo: String
    ): TaskUiState(tid, completed, content, subtitle)

    data class FilterTask(
        override val tid: Int,
        override val completed: MutableState<Boolean>,
        val pid: Int
    ): TaskUiState(tid, completed, "")

    data class ShortAnswerTask(
        override val tid: Int,
        override val completed: MutableState<Boolean>,
        override val header: String,
        val question: String,
        val answer: MutableState<String>,
        val isLast: Boolean = false
    ): TaskUiState(tid, completed, header, question)

    data class LiteracyRateTask(
        override val tid: Int,
        override val completed: MutableState<Boolean>,
        override val header: String,
        override val subtitle: String,
        val CanadaRate: Float,
        val GermanyRate: Float,
        val GhanaRate: Float,
        val KenyaRate: Float,
        val KuwaitRate: Float,
        val MalawiRate: Float,
        val SouthAfricaRate: Float,
    ): TaskUiState(tid, completed, header, subtitle)

    data class GlobalLiteracyRateTask(
        override val tid: Int,
        override val completed: MutableState<Boolean>,
        override val header: String,
        val content: String,
        val image: Int,
        val hyperlinkText: String,
        val hyperlink: String
    ): TaskUiState(tid, completed, header)

    data class WelcomeTask(
        override val tid: Int,
        override val completed: MutableState<Boolean>,
        override val header: String
    ): TaskUiState(tid, completed, header)
}

fun TaskUiState.ReadingTask.skipAble() = tid == 10

