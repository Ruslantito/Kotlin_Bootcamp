import kotlin.reflect.KClass

class Player(
    val inventory: Inventory<Bullet> = Inventory(),
    val revolver: RevolverDrum<PistolBullet> = RevolverDrum(),
    val rifle: RevolverDrum<RifleBullet> = RevolverDrum(),
) {
    init {
        inventory.add(TwentyTwo::class.simpleName!!, MutableList(10) {TwentyTwo()})
        inventory.add(ThreeEighty::class.simpleName!!, MutableList(7) {ThreeEighty()})
        inventory.add(FortyFive::class.simpleName!!, MutableList(44) {FortyFive()})
        inventory.add(RifleBullet::class.simpleName!!, MutableList(12) {RifleBullet()})
    }

    fun <T: Bullet> reload(caliber: KClass<T>): MutableList<Bullet?>? {
        val collectionAmmo = inventory.collection[caliber.simpleName]
        if (collectionAmmo != null && collectionAmmo.size > 0) {
            if(caliber.simpleName == RifleBullet::class.simpleName) {
                rifle.ejectAllShotDamp()
                rifle.addMissingElements(collectionAmmo)
            } else {
                revolver.ejectAllShotDamp()
                revolver.addMissingElements(collectionAmmo)
            }
            return collectionAmmo
        }
        return null
    }
}

data class Inventory<T: Bullet>(
    var collection: MutableMap<String, MutableList<T?>> = mutableMapOf(),
) {
    fun process() {
        println("process...")
    }
    fun add(key: String, value: MutableList<T?>) {
        collection[key] = value
    }
    fun delete(key: String) {
        collection.remove(key)
    }
}
