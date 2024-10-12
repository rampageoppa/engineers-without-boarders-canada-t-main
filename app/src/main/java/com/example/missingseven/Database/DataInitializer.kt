package com.example.missingseven.Database

import android.content.Context
import com.example.missingseven.Database.Entity.Country
import com.example.missingseven.Database.Entity.Item
import com.example.missingseven.Database.Entity.Player
import com.example.missingseven.Database.Entity.TaskType
import com.example.missingseven.R

/***
 * class to initialize different tables
 */
class DataInitializer constructor(
    private val context: Context
) {
    fun getAllReadingTasks(): List<TaskType.ReadingTask> = listOf(
        TaskType.ReadingTask(
            tid = 1,
            completed = true,
            header = context.getString(R.string.task0_header),
            content = context.getString(R.string.task0_content),
            isSpecial = false
        ),
        TaskType.ReadingTask(
            tid = 9,
            completed = true,
            header = context.getString(R.string.task9_header),
            content = context.getString(R.string.task9_content),
            isSpecial = true),
        TaskType.ReadingTask(
            tid = 10,
            completed = true,
            header = context.getString(R.string.task10_header),
            subtitle = context.getString(R.string.task10_subtitle),
            content = context.getString(R.string.task10_content),
            isSpecial = false
        )

    )

    fun getAllMultipleChoiceTasks(): List<TaskType.MultipleChoiceTask> = listOf(
        TaskType.MultipleChoiceTask(
            tid = 3,
            completed = false,
            header = context.getString(R.string.task3_header),
            options = listOf(
                context.getString(R.string.task3_option0),
                context.getString(R.string.task3_option1),
                context.getString(R.string.task3_option2),
                context.getString(R.string.task3_option3),
                context.getString(R.string.task3_option4),
            ),
            correctIndex = 4,
            studentAnswerIndex = -1,
            popup = context.getString(R.string.task3_popup)),
        TaskType.MultipleChoiceTask(
            tid = 7,
            completed = false,
            header = context.getString(R.string.task3_header),
            options = listOf(
                context.getString(R.string.task7_option0),
                context.getString(R.string.task7_option1),
                context.getString(R.string.task7_option2),
                context.getString(R.string.task7_option3),
                context.getString(R.string.task7_option4),
            ),
            correctIndex = 4,
            studentAnswerIndex = -1,
            popup = context.getString(R.string.task7_popup)
        )
    )

    fun getAllSlidingScaleTasks(): List<TaskType.SlidingScaleTask> = listOf(
        TaskType.SlidingScaleTask(
            tid = 2,
            completed = false,
            content = context.getString(R.string.task2_header),
            subtitle = context.getString(R.string.task2_subtitle),
            start = 0,
            end = 8000,
            offset = 50,
            unit = "M",
            correct = 800,
            current = 4000,
            tooSmallInfo = context.getString(R.string.tooShort),
            tooLargeInfo = context.getString(R.string.tooLarge),
            correctInfo = context.getString(R.string.task2_correctInfo),
        ),
        TaskType.SlidingScaleTask(
            tid = 6,
            completed = false,
            content = context.getString(R.string.task6_header),
            subtitle = context.getString(R.string.task6_subtitle),
            start = 0,
            end = 8000,
            offset = 50,
            unit = "M",
            correct = 1000,
            current = 4000,
            tooSmallInfo = context.getString(R.string.tooShort),
            tooLargeInfo = context.getString(R.string.tooLarge),
            correctInfo = context.getString(R.string.task6_correctInfo)
        )
    )

    fun getFilterTasks(): List<TaskType.FilterTask> = listOf(
        TaskType.FilterTask(
            tid = 99,
            completed = false,
            pid = -1)
    )

    fun getWelcomeTask(): List<TaskType.WelcomeTask> = listOf(
        TaskType.WelcomeTask(
            tid = 0,
            completed = true)
    )

    fun getLiteracyRateTasks(): List<TaskType.LiteracyRateTask> = listOf(
        TaskType.LiteracyRateTask(
            tid = 4,
            completed = true,
            header = context.getString(R.string.task4_header),
            subtitle = context.getString(R.string.task4_subtitle),
            CanadaRate = 99F,
            GermanyRate = 99F,
            GhanaRate = 79.04F,
            KenyaRate = 81.54F,
            KuwaitRate = 96.46F,
            MalawiRate = 62.14F,
            SouthAfricaRate = 95.02F
        )
    )

    fun getGLRTasks(): List<TaskType.GlobalLiteracyRateTask> = listOf(
        TaskType.GlobalLiteracyRateTask(
            tid = 5,
            completed = true,
            header = context.getString(R.string.task5_header),
            content = context.getString(R.string.task5_content),
            hyperlinkText = context.getString(R.string.our_world_in_data),
            hyperlink = context.getString(R.string.our_world_in_data_url)
        ),
        TaskType.GlobalLiteracyRateTask(
            tid = 8,
            completed = true,
            header = context.getString(R.string.task8_header),
            content = context.getString(R.string.task8_content),
            hyperlinkText = context.getString(R.string.our_world_in_data),
            hyperlink = context.getString(R.string.our_world_in_data_url)
        )

    )

    fun getShortAnswerTasks(): List<TaskType.ShortAnswerTask> = listOf(
        TaskType.ShortAnswerTask(
            tid = 11,
            completed = false,
            header = context.getString(R.string.task11_header),
            question = context.getString(R.string.task11_question),
            answer = "",
            isLast = false
        ),
        TaskType.ShortAnswerTask(
            tid = 12,
            completed = false,
            header = context.getString(R.string.task12_header),
            question = context.getString(R.string.task12_question),
            answer = "",
            isLast = false
        ),        TaskType.ShortAnswerTask(
            tid = 13,
            completed = false,
            header = context.getString(R.string.task13_header),
            question = context.getString(R.string.task13_question),
            answer = "",
            isLast = false
        ),        TaskType.ShortAnswerTask(
            tid = 14,
            completed = false,
            header = context.getString(R.string.task14_header),
            question = context.getString(R.string.task14_question),
            answer = "",
            isLast = true
        ),
    )

    fun getAllCountries(): List<Country> = listOf(
        Country(
            cid = 1,
            name = "Canada",
            money = 500,
            instruction = context.getString(R.string.instruction_developed)
        ),
        Country(
            cid = 2,
            name = "Canadian First Nations",
            money = 500,
            instruction = context.getString(R.string.instruction_developed),
            priceMultiplier = 5,
            multiplierHint = context.getString(R.string.cfn_hint)
        ),
        Country(
            cid = 3,
            name = "Kuwait",
            money = 400,
            instruction = context.getString(R.string.instruction_developed)
        ),
        Country(
            cid = 4,
            name = "South Africa",
            money = 65,
            instruction = context.getString(R.string.instruction_developing),
            hasDifficultyReading = true
        ),
        Country(
            cid = 5,
            name = "Ghana",
            money = 40,
            instruction = context.getString(R.string.instruction_developing),
            hasDifficultyReading = true
        ),
        Country(
            cid = 6,
            name = "Kenya",
            money = 30,
            instruction = context.getString(R.string.instruction_developing),
            hasDifficultyReading = true
        ),
        Country(
            cid = 7,
            name = "Malawi",
            money = 50,
            instruction = context.getString(R.string.instruction_Malawi),
            hasDifficultyReading = true
        )
    )
    fun getAllItem(): List<Item> = listOf(
        Item(
            iid = 0,
            name = "Fine Sand",
            quantity = 0,
            price = 20,
            strength = 3f,
            cleanedStrength = 4f,
            effectiveness = listOf(0.25f, 0.40f, 1.30f, 1.10f,1f,1f),
            cleanedEffectiveness = listOf(0.5f, 0.8f, 1.3f, 1.1f,1f,1f)),
        Item(
            iid = 1,
            name = "Coarse Sand",
            quantity = 0,
            price = 20,
            strength = 2.5f,
            cleanedStrength = 3f,
            effectiveness = listOf(0.3f, 0.4f, 1.2f, 1.1f,1f,1f),
            cleanedEffectiveness = listOf(0.6f, 0.8f, 1.2f, 1.1f,1f,1f)),
        Item(
            iid = 2,
            name = "Fine Gravel",
            quantity = 0,
            price = 10,
            strength = 2f,
            cleanedStrength = 2.25f,
            effectiveness = listOf(0.75f, 0.8f, 1f, 1.1f,1f,1f),
            cleanedEffectiveness = listOf(0.75f, 0.8f, 1f, 1.1f,1f,1f)),
        Item(
            iid = 3,
            name = "Coarse Gravel",
            quantity = 0,
            price = 10,
            strength = 1.25f,
            cleanedStrength = 1.3f,
            effectiveness = listOf(0.8f, 0.9f, 0.9f, 1f,1f,1f),
            cleanedEffectiveness = listOf(0.8f, 0.9f, 0.9f, 1f,1f,1f)),
        Item(
            iid = 4,
            name = "Cheesecloth",
            quantity = 0,
            price = 5,
            strength = 0.5f,
            cleanedStrength = 0.5f,
            effectiveness = emptyList(),
            cleanedEffectiveness = emptyList()
        ),
        Item(
            iid = 5,
            name = "Cotton",
            quantity = 0,
            price = 5,
            strength = 1f,
            cleanedStrength = 1f,
            effectiveness = emptyList(),
            cleanedEffectiveness = emptyList()
        ),
    )
    fun getAllPlayer(): List<Player> = listOf(
        Player(
            pid = 0,
            cid = -1,
            curr_money = 0,
            neck = -1,
            mouth = -1,
            layer0 = -1,
            layer1 = -1,
            layer2 = -1,
            layer3 = -1,
            layer4 = -1,
            layer5 = -1,
            layer6 = -1,
            layer7 = -1,
            mouthRubberBanded = false)
    )

    companion object {
        const val INSERT_NUM = 11
    }

}