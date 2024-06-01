
class ProcessAll {
    fun processing() {
        ex1()
    }

    private fun ex1() {
        println("---===    LET'S START!    ===---")
        println("---=== Creating of heroes ===---")

        val rambo = Rambo()
        val sniper = Sniper()
        val ninja = Ninja()

        println("---=== Creating of heroes weapons ===---")

        val pistol = Pistol()
        val rifle = SniperRifle()
        val silent = SilencedPistol()
        val shuriken = Shuriken()
        val katana = Katana()
        val c4 = C4()
        val grenade = Grenade()

        println("---=== Heroes having fun ===---")

        println()
        println("---=== RAMBO ===---")
        rambo.interaction(grenade)
        rambo.interaction(silent)
        rambo.interaction(rifle)
        rambo.interaction(katana)

        println()
        println("---=== SNIPER ===---")
        sniper.interaction(pistol)
        sniper.interaction(rifle)
        sniper.interaction(c4)
        sniper.interaction(katana)

        println()
        println("---=== NINJA ===---")
        ninja.interaction(c4)
        ninja.interaction(katana)
        ninja.interaction(silent)
        ninja.interaction(shuriken)
    }
}
