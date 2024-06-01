class ProcessAll {
    fun processing() {
        ex1()
    }

    private fun ex1() {
        val bullet22001 = TwentyTwo()
        val bullet22002 = TwentyTwo()
        val bullet22003 = TwentyTwo()
        val bullet22004 = TwentyTwo()
        val bullet22005 = TwentyTwo()
        val bullet22006 = TwentyTwo()

        bullet22002.dampStatus = true
        bullet22003.shootStatus = true
        bullet22004.loadStatus = true

        val gunDram22 = RevolverDrum<TwentyTwo>()

        println()
        println("1. Adding elements")
        println("bullet22001 load status: ${gunDram22.addOneElement(bullet22001)} ")
        println("bullet22002 load status: ${gunDram22.addOneElement(bullet22002)} ")
        println("bullet22003 load status: ${gunDram22.addOneElement(bullet22003)} ")
        println("bullet22004 load status: ${gunDram22.addOneElement(bullet22004)} ")
        println("bullet22005 load status: ${gunDram22.addOneElement(bullet22005)} ")
        println("bullet22006 load status: ${gunDram22.addOneElement(bullet22006)} ")

        println()
        println("2. Drum check")
        println(gunDram22)

        println()
        println("3. Shoot first")
        gunDram22.shoot()
        gunDram22.shoot()
        gunDram22.shoot()
        gunDram22.shoot()
        gunDram22.shoot()
        gunDram22.shoot()

        println()
        println("4. Shoot second")
        gunDram22.shoot()
        gunDram22.shoot()
        gunDram22.shoot()
        gunDram22.shoot()
        gunDram22.shoot()
        gunDram22.shoot()

        println()
        println("5. Collections")

        val bullet45001 = FortyFive()
        val bullet45002 = FortyFive()
        val bullet45003 = FortyFive()
        val bullet45004 = FortyFive()
        val bullet45005 = FortyFive()
        val bullet45006 = FortyFive()

        bullet45002.dampStatus = true
        bullet45003.shootStatus = true
        bullet45004.loadStatus = true

        val supplyCollection:  MutableList<FortyFive?> = mutableListOf()
        supplyCollection.add(bullet45001)
//        supplyCollection.add(bullet45002)
        supplyCollection.add(bullet45003)
        supplyCollection.add(bullet45004)
        supplyCollection.add(bullet45005)
        supplyCollection.add(bullet45006)

        val supplyCollection2: MutableList<FortyFive?> = mutableListOf()
        supplyCollection2.add(bullet45001)
        supplyCollection2.add(bullet45002)

        val gunDram45  = RevolverDrum<FortyFive>()
        val gunDram452 = RevolverDrum<FortyFive>()

        gunDram45.addMissingElement(supplyCollection)
        gunDram452.addMissingElement(supplyCollection2)

        println()
        println("Drum check")
        println(gunDram45)
        println()
        println(gunDram452)

        gunDram45.scroll()
        gunDram452.scroll()

        println()
        gunDram45.shoot()
        gunDram45.shoot()
        gunDram45.shoot()
        gunDram45.shoot()
        gunDram45.shoot()
        gunDram45.shoot()

        println()
        gunDram452.shoot()
        gunDram452.shoot()
        gunDram452.shoot()
        gunDram452.shoot()
        gunDram452.shoot()
        gunDram452.shoot()

        println()
        gunDram45.unload(true)
        gunDram452.unload(true)
        println("Drum check")
        println(gunDram45)
        println()
        println(gunDram452)

        "- Check that normal bullets shoot and damp not." +
        "- Try to add one bullet to different drums. " +
          "The first addition should return true, the second - false" +

        "- Try to add one bullet to different collections, " +
          "and then add it to different drums" +
        "- Try to shoot with a damp and shot bullets. " +
          "The output should be \"A damp one\" and \"A shot one\""
    }
}