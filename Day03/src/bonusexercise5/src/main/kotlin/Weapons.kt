import kotlin.reflect.full.superclasses

interface Firearm
interface Silent
interface Cold
interface Explosive


abstract class Weapon {
    abstract fun interaction(): String
}

class Pistol: Weapon(), Firearm {
    override fun interaction(): String {
        val interfaceList = this::class.superclasses.map{ it.simpleName }.dropLast(1)
        return "${this::class.simpleName}" +
                " (${interfaceList.joinToString(", ")})" +
                " - BANG!!!"
    }
}
class SniperRifle: Weapon(), Firearm {
    override fun interaction(): String {
        val interfaceList = this::class.superclasses.map{ it.simpleName }.dropLast(1)
        return "${this::class.simpleName}" +
                " (${interfaceList.joinToString(", ")})" +
                " - BA-BAH!!!"
    }
}
class SilencedPistol: Weapon(), Firearm, Silent {
    override fun interaction(): String {
        val interfaceList = this::class.superclasses.map{ it.simpleName }.dropLast(1)
        return "${this::class.simpleName}" +
                " (${interfaceList.joinToString(", ")})" +
                " - TUK!!!"
    }
}
class Shuriken: Weapon(), Cold, Silent {
    override fun interaction(): String {
        val interfaceList = this::class.superclasses.map{ it.simpleName }.dropLast(1)
        return "${this::class.simpleName}" +
                " (${interfaceList.joinToString(", ")})" +
                " - VZHUH!!!"
    }
}
class Katana: Weapon(), Cold, Silent {
    override fun interaction(): String {
        val interfaceList = this::class.superclasses.map{ it.simpleName }.dropLast(1)
        return "${this::class.simpleName}" +
                " (${interfaceList.joinToString(", ")})" +
                " - SPLASH!"
    }
}
class C4: Weapon(), Explosive {
    override fun interaction(): String {
        val interfaceList = this::class.superclasses.map{ it.simpleName }.dropLast(1)
        return "${this::class.simpleName}" +
                " (${interfaceList.joinToString(", ")})" +
                " - C4-BOOOM!!!!!!"
    }
}
class Grenade: Weapon(), Explosive {
    override fun interaction(): String {
        val interfaceList = this::class.superclasses.map{ it.simpleName }.dropLast(1)
        return "${this::class.simpleName}" +
                " (${interfaceList.joinToString(", ")})" +
                " - KA-BOOOM!!!!!!"
    }
}

/**
- Pistol, SniperRifle - implement Firearm interface,
- SilencedPistol - implement Firearm and Silent interfaces
- Shuriken, Katana - implement Cold and Silent interfaces
- C4, Grenade - implement Explosive interface
- Weapons have an interaction method, which prints a type of a gun, its interfaces and interaction sound
 */
