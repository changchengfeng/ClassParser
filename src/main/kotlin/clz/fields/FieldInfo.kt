package clz.fields

import clz.AccessPropertyFlags
import clz.ClassFile
import clz.arrayToStr
import clz.attributes.AttributeInfo
import clz.constantpool.CpInfo
import okio.BufferedSource
import clz.u2

class FieldInfo(
    val access_flags: u2,
    val name_index: u2,
    val descriptor_index: u2,
    val attributes_count: u2,
    val buffer: BufferedSource,
    val constant_pools: Array<CpInfo?>
) {

    val attributes: Array<AttributeInfo?>

    init {
        attributes = AttributeInfo.readAttributeInfo(attributes_count, constant_pools, buffer)
    }

    override fun toString(): String {
        return """
            FieldInfo(
                access_flags $access_flags ${AccessPropertyFlags.getAccess(access_flags)}
                name_index $name_index ${constant_pools[name_index.toInt()]}
                descriptor_index $descriptor_index  ${constant_pools[descriptor_index.toInt()]}
                attributes ${ arrayToStr(attributes,"AttributeInfo")}
            )
        """.trimIndent()
    }
}