package clz.interfaces

import clz.constantpool.CpInfo
import clz.u2

class Interface(val index: u2, val constant_pools: Array<CpInfo?>) {

    override fun toString(): String {
        return "Interface(index #$index ${constant_pools[index.toInt()]})"
    }
}