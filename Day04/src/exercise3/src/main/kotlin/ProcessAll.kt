class ProcessAll {
    fun processing() {
        ex1()
    }

    private fun ex1() {
        println("1.")
        println("String, Int - ${compareObjByTypes(Int, String)}")
        println("String, String - ${compareObjByTypes(String, String)}")

        println()
        println("2.")
        val convListAtoB = ConvertListAtoB()
        val listFirst: MutableList<First> = mutableListOf()
        listFirst.add(First("Alex"))
        listFirst.add(First("John"))
        listFirst.add(First("Peter"))
        println("Map List<First>: $listFirst")
        println("to List<Second>: ${convListAtoB.convertListsAtoB(listFirst)}")

        println()
        println("3.")
        open class Animal {
            open fun voice() = println("Animal!")
        }
        class Dog: Animal() {
            override fun voice() = println("Dog!")
        }
        class Cat: Animal() {
            override fun voice() = println("Cat!")
        }
        val animal: Animal = Dog()
        println( animal.isObjectIsOfCertainType<Dog>())
        println( animal.isObjectIsOfCertainType<Cat>())
        println( animal.isObjectIsOfCertainType<Animal>())

        println()
        println("4.")
        val delegAndPlus = DelegateAndPlus()
        val str = "The delegate will replace all spaces with underscores"
        delegAndPlus.description = str
        println("Before: $str")
        println("After: ${delegAndPlus.description}")

        println()
        println("5.")
        delegAndPlus.logs = "Hello"
        val newProp = delegAndPlus.logs

        println()
        println("6.")
        println("The result of subtraction is ${chooseOperator("SUBTRACTION")?.invoke(10, 5)}")

        println()
        println("7.")
        val gunDram = RevolverDrum<FortyFive>()
        gunDram.addOneElement(FortyFive())
        gunDram.addOneElement(FortyFive())
        gunDram.addOneElement(null)
        gunDram.addOneElement(null)
        gunDram.addOneElement(FortyFive())
        gunDram.addOneElement(FortyFive())
        println("Before:\n$gunDram")

        gunDram.rearrangeBullets()
        println()
        println("After:\n$gunDram")

        println()
        println("8.")
        gunDram.addMissingElements(mutableListOf(FortyFive(), FortyFive(), FortyFive()))
        println("Scrolled:\n$gunDram")

        println()
        gunDram.scrollShoot()
        println("Shot:\n$gunDram")

        println()
        println("9.")
        println("Before:\n$gunDram")

        println()
        val newGunDram = gunDram.extractBulletCreateNewDrum()
        println("After:\n$gunDram")

        println()
        println("New drum:\n$newGunDram")
    }
}