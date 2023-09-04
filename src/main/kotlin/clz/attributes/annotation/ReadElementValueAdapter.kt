package clz.attributes.annotation

import clz.constantpool.CpInfo
import clz.u1
import okio.BufferedSource

interface ReadElementValueAdapter {
    fun read(source: BufferedSource,tag:u1,constant_pools: Array<CpInfo?>):ElementValue
}