package clz.attributes.annotation

import clz.ClassFile
import clz.constantpool.CpInfo
import clz.u1
import clz.u2
import okio.BufferedSource

class ConstElementValue(tag :u1, tagItem: TagItem,constant_pools: Array<CpInfo?>) :ElementValue(tag,tagItem,ValueItem.const_value_index,constant_pools){

    var const_value_index : u2 = 0

    companion object :ReadElementValueAdapter{
        override fun read(source: BufferedSource,tag :u1,constant_pools: Array<CpInfo?>): ElementValue {
            val tagItemByValue = TagItem.getTagItemByValue(tag)
            val constElementValue = ConstElementValue(tag, tagItemByValue,constant_pools)
            constElementValue.const_value_index= source.readShort()
            return constElementValue
        }
    }
    override fun toString(): String {
        return """ConstElementValue(
                                    ${super.toString()}
                                    const_value_index = #${const_value_index} ${constant_pools[const_value_index.toInt()]}
                                    )
        """.trimIndent()
    }
}