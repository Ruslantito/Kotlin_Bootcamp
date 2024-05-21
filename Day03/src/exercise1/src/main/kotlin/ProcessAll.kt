class ProcessAll {
    fun processing() {
        val gunDram = RevolverDrum<Int>()

        gunDram.addOneElement(3)
        gunDram.addOneElement(54)
        gunDram.addOneElement(7)
        gunDram.addOneElement(2)
        gunDram.addOneElement(56)
        gunDram.addOneElement(4)

        println("1. Adding elements")
        println(gunDram)

        println()
        println("2. Scroll")
        gunDram.scroll()
        println(gunDram)

        println()
        println("3. Deletion")
        gunDram.shoot()
        gunDram.shoot()
        gunDram.shoot()
        gunDram.shoot()
        println(gunDram)

        println()
        println("4. Supply collection")
        val supplyCollection: MutableList<Int?> = mutableListOf(4, 6, 3, 22, 77, 43, 76, 5)
        println("Before:")
        println("Supply collection: $supplyCollection")

        println()
        println(gunDram)

        println()
        gunDram.addMissingElement(supplyCollection)
        println("After add operation performed:")
        println("Supply collection: ${supplyCollection.filterNotNull()}")

        println()
        println(gunDram)

        println()
        println("5. Extraction")
        val extractedElements = gunDram.unload(true)
        println("The extracted list: $extractedElements")
        println("size: ${extractedElements.size}")

        println()
        gunDram.scroll()
        println(gunDram)

        println()
        println("6. Supply collection 2:")
        val supplyCollection2: MutableList<Int?> = mutableListOf(77, 43, 76, 5)
        println("Before:")
        println("Supply collection: $supplyCollection2]")

        println()
        println(gunDram)

        println()
        gunDram.addMissingElement(supplyCollection2)
        println("After add operation performed:")
        println("Supply collection: ${supplyCollection2.filterNotNull()}")

        println()
        println(gunDram)

        println()
        println("7. Equals")
        println(gunDram)

        val gunDram2 = RevolverDrum<Int>()
        gunDram2.addOneElement(null)
        gunDram2.addOneElement(null)
        gunDram2.addOneElement(5)
        gunDram2.addOneElement(76)
        gunDram2.addOneElement(43)
        gunDram2.addOneElement(77)

        println()
        println(gunDram2)

        println()
        print("Result: ")
        if (gunDram == gunDram2) println("equals")
        else println("not equals")
    }
}