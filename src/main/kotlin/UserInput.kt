import java.util.Scanner

class UserInput {

    private val scanner = Scanner(System.`in`)

    fun getText(title: String): String {
        println(title)
        while (true) {
            val consoleData = scanner.nextLine()
            if (consoleData.isNotEmpty()) return consoleData
        }
    }

    fun selectNumberMenu(countItems: Int): Int {
        println(Texts.BR)
        println(Texts.MENU_SELECT)
        val inputData = scanner.nextLine()
        if (isInteger(inputData)) {
            val index = Integer.parseInt(inputData)
            if (index in 0..countItems) {
                return index
            } else throw Exception(Texts.MENU_WRONG_NUMBER)
        } else throw Exception(Texts.MENU_WRONG_INPUT)
    }

    fun exitNote() {

        println(Texts.NOTE_ENTER_ANY_TEXT_FOR_EXIT)
        scanner.nextLine()
    }

    private fun isInteger(s: String): Boolean {
        return try {
            s.toInt()
            true
        } catch (e: NumberFormatException) {
            false
        }
    }
}