package data

class ProcessAll {
    fun processing() {
        val menu = ProcessingMenu()
        menu.processing()

        val processingFC = ProcessingFilterCompany(menu.vacancy)
        processingFC.dataProcessing()
    }
}