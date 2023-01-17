class App {
    private val menuList = mutableListOf<Menu>()

    fun start() {

        createMenu()

        while (true) {
            showMenuItems(Texts.ARCHIVE_LIST, Texts.EXIT, menuList)

            try {
                val indexMenu = UserInput().selectNumberMenu(menuList.size)

                if (indexMenu == menuList.size) {
                    println(Texts.BYE)
                    break
                }

                menuList[indexMenu].handler()

            } catch (e: Exception) {
                println(e.message)
            }
        }
    }

    private fun createMenu() {

        menuList.add(Menu(Texts.ARCHIVE_CREATE) {
            val archiveName = UserInput().getText(Texts.ARCHIVE_ENTER_TITLE)
            val archiveScreen = Archive(archiveName)

            val archiveScreenItems = Menu(Texts.NOTE_CREATE) {
                val userInput = UserInput()
                val noteTitle = userInput.getText(Texts.NOTE_ENTER_TITLE)
                val noteText = userInput.getText(Texts.NOTE_ENTER_TEXT)
                val note = Note(noteTitle, noteText)
                println(Texts.NOTE_CREATED)

                note.handler = {
                    println(Texts.BR)
                    println(Texts.NOTE + " \"${note.title}\":")
                    println(note.text)
                    println(Texts.BR)
                    UserInput().exitNote()
                    archiveScreen.handler()
                }

                archiveScreen.subMenu.add(note)

                archiveScreen.handler()
            }

            archiveScreen.subMenu.add(archiveScreenItems)

            archiveScreen.handler = {

                showMenuItems(
                    "${Texts.NOTE_LIST} \"${archiveScreen.title}\"",
                    Texts.ARCHIVE_EXIT,
                    archiveScreen.subMenu
                )

                try {
                    val indexMenu = UserInput().selectNumberMenu(archiveScreen.subMenu.size)
                    if (indexMenu != archiveScreen.subMenu.size) {
                        archiveScreen.subMenu[indexMenu].handler()
                    }
                } catch (e: Exception) {
                    println(e.message)
                    archiveScreen.handler()
                }
            }

            menuList.add(menuList.size, archiveScreen)
            println(Texts.ARCHIVE_CREATED)
        })
    }

    private fun showMenuItems(title: String, exit: String, list: List<Menu>) {
        println(Texts.BR)
        println(title)
        list.forEachIndexed { index, menu -> println("$index. ${menu.title}") }
        println("${list.size}. $exit")
    }
}
