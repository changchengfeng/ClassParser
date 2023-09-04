package clz.attributes.annotation

import clz.constantpool.CpInfo
import clz.u1

abstract class ElementValue(open val tag: u1, open val tagItem: TagItem, open val valueItem: ValueItem,val constant_pools: Array<CpInfo?>){
    override fun toString(): String {
        return """ElementValue(
                                tag = $tag
                                tagItem = $tagItem
                                valueItem = $valueItem
                               )
        """.trimIndent()
    }
}