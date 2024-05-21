
abstract class Character<T : Weapon> {
    abstract fun interaction(weapon: T)
}

class Rambo : Character<Weapon>() {
    override fun interaction(weapon: Weapon) {
        println("${this::class.simpleName} attacks with a ${weapon.interaction()}")
    }
}
class Sniper : Character<Weapon>() {
    override fun interaction(weapon: Weapon) {
        when (weapon) {
            is Firearm -> println("${this::class.simpleName} attacks with a ${weapon.interaction()}")
            else -> println("${this::class.simpleName} can't attack with a ${weapon::class.simpleName}!")
        }
    }
}
class Ninja : Character<Weapon>() {
    override fun interaction(weapon: Weapon) {
        when (weapon) {
            is Cold, is Silent -> println("${this::class.simpleName} attacks with a ${weapon.interaction()}")
            else -> println("${this::class.simpleName} can't attack with a ${weapon::class.simpleName}!")
        }
    }
}
/*
- Rambo can interact with any weapon
- Sniper can interact with weapons like Firearm
- Ninja can only interact with certain Cold and Silent weapons
- Characters can attack with their weapons, calling the "interact" method
*/
