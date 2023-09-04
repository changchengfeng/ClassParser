package clz.attributes.annotation

import clz.ClassFile
import clz.constantpool.CpInfo
import clz.u1
import clz.u2
import okio.BufferedSource

class EnumConstElementValue(tag: u1, constant_pools: Array<CpInfo?>) :
    ElementValue(tag, TagItem.e, ValueItem.enum_const_value, constant_pools) {

    var type_name_index: u2 = 0
    var const_name_index: u2 = 0

    companion object : ReadElementValueAdapter {
        override fun read(source: BufferedSource, tag: u1, constant_pools: Array<CpInfo?>): ElementValue {
            val enumConstElementValue = EnumConstElementValue(tag, constant_pools)
            enumConstElementValue.type_name_index = source.readShort()
            enumConstElementValue.const_name_index = source.readShort()
            return enumConstElementValue
        }
    }

    override fun toString(): String {
        return """EnumConstElementValue(
                                        ${super.toString()}
                                        type_name_index = #${type_name_index} ${constant_pools[type_name_index.toInt()]}
                                        const_name_index = #${const_name_index} ${constant_pools[const_name_index.toInt()]}
                                       )
        """.trimIndent()
    }
}