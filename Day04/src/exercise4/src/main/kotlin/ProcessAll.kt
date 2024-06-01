import kotlin.reflect.KClass

class ProcessAll {
    fun processing() {
        ex1()
    }

    private fun ex1() {
        val player = Player()
        println("---=== RIFLE ===---")
        shootReloadProcess(player, RifleBullet::class)
        println()
        println("---=== REVOLVER ===---")
        shootReloadProcess(player, TwentyTwo::class)
    }

    private fun <T: Bullet> shootReloadProcess(player: Player, bullet: KClass<T>) {
        val weapon = if(bullet.simpleName == RifleBullet::class.simpleName) player.rifle else player.revolver
        while(true) {
            println()
            println("Inventory status:\n${player.inventory}")
            player.reload(bullet)
            println()
            println("Weapon status after reload:\n$weapon")
            if(weapon.getDramElements().count { it != null } == 0) break
            for (i in 0..5) weapon.shoot()
            println()
            println("Weapon status after shoot:\n$weapon")
            println("--------------------------")
        }
    }
}
