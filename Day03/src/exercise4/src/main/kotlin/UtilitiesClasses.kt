
fun <T: Bullet> RevolverDrum<T>.rearrangeBullets() {
    val elements = this.getDramElements()
    val dramSize = this.getDramSize()

    val newElements: MutableList<Bullet?> = mutableListOf()
    var countEmpty = 0
    for (i in 0..< dramSize) {
        val bullet = elements[this.pointer]
        if(bullet == null) countEmpty++
        if(bullet != null) {
            if(countEmpty > 1) {
                for (j in 0..< countEmpty) newElements.removeLast()
            }
            countEmpty = 0
        }
        newElements.add(bullet)
        this.pointer++
        this.recountPointerPosition()
    }
    for(i in newElements.size-1..< 6) newElements.add(null)
    this.setDramElements(newElements)
}
fun <T: Bullet> RevolverDrum<T>.scrollShoot() {
    this.scroll()
    this.shoot()
}
inline fun <reified T: Bullet> RevolverDrum<T>.extractBulletCreateNewDrum(): RevolverDrum<Bullet> {
    val newGunDrum = RevolverDrum<Bullet>()
    val list = this.unload(false)
    var elem: Bullet? = null
    for (i in 0..< list.size) {
        if(list[i] != null) {
            elem = list[i]
        }
    }
    newGunDrum.addMissingOneElement(elem)
    return newGunDrum
}
