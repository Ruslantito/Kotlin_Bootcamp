package data

import Company
import Vacancy

data class ProcessingMenu(
    val vacancy: MutableMap<String, String> = mutableMapOf()
) {
    private var allData = ""
    fun processing() {
        selectFieldOfActivity()
        println()

        print(allData)
        selectProfession()
        println()

        print(allData)
        selectLevelOfCandidate()
        println()

        print(allData)
        selectSalaryLevel()
        println()

        println(allData)
        println("The list of suitable vacancies:")
        println()
    }

    private fun plusData(dataBefore: String, newData:String): String {
        return dataBefore.plus(newData).plus(". ")
    }

    private fun selectFieldOfActivity() {
        println("Select a field of activity:")
        ActivityType.entries.forEach { println("${it.ordinal + 1}. ${it.Name}") }
        println()

        val selected = chooseItemNumber(ActivityType.entries.size)
        val fieldOfActivity = ActivityType.entries[selected - 1].Name
        vacancy[Company::fieldOfActivity.name] = fieldOfActivity
        allData = plusData(allData, fieldOfActivity)
    }

    private fun selectProfession() {
        println("Select a profession:")
        ProfessionType.entries.forEach { println("${it.ordinal + 1}. ${it.Name}") }
        println()

        val selected = chooseItemNumber(ProfessionType.entries.size)
        val profession = ProfessionType.entries[selected - 1].Name
        vacancy[Vacancy::profession.name] = profession
        allData = plusData(allData, profession)
    }

    private fun selectLevelOfCandidate() {
        println("Select the level of a candidate:")
        LevelType.entries.forEach { println("${it.ordinal + 1}. ${it.Name}") }
        println()

        val selected = chooseItemNumber(LevelType.entries.size)
        val level = LevelType.entries[selected - 1].Name
        vacancy[Vacancy::level.name] = level
        allData = plusData(allData, level)
    }

    private fun selectSalaryLevel() {
        println("Select a salary level:")
        SalaryType.entries.forEach { println("${it.ordinal + 1}. ${it.Name}") }
        println()

        val selected = chooseItemNumber(SalaryType.entries.size)
        val salary = SalaryType.entries[selected - 1].Name
        vacancy[Vacancy::salary.name] = salary
        allData = plusData(allData, salary)
    }

    private fun chooseItemNumber(maxItemNumber: Int): Int {
        var selected = readNum()
        if(selected > maxItemNumber) {
            println("Wrong number. Please try again!")
            selected = chooseItemNumber(maxItemNumber)
        }
        return selected
    }

    private fun readNum(): Int {
        val numb = try {
            readln().toInt()
        } catch(e: NumberFormatException) {
            println("Couldn't parse a number. Please, try again")
            readNum()
        }
        return numb
    }
}
