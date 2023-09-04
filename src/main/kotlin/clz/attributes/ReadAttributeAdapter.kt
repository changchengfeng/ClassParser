package clz.attributes

import clz.constantpool.CpInfo
import okio.BufferedSource

interface ReadAttributeAdapter {
    fun read(source: BufferedSource,constant_pools: Array<CpInfo?>):AttributeInfo
}