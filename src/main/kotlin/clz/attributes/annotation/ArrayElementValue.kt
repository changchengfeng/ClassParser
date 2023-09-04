package clz.attributes.annotation

import clz.arrayToStr
import clz.attributes.AnnotationAttribute
import clz.constantpool.CpInfo
import clz.u1
import okio.BufferedSource

class ArrayElementValue(tag: u1,constant_pools: Array<CpInfo?>) : ElementValue(tag, TagItem.A, ValueItem.array_value,constant_pools) {

    var array_value: Array<ElementValue?> = arrayOf()

    companion object : ReadElementValueAdapter {
        override fun read(source: BufferedSource, tag: u1 ,constant_pools: Array<CpInfo?>): ElementValue {
            val arrayElementValue = ArrayElementValue(tag,constant_pools)
            val num_values = source.readShort()
            arrayElementValue.array_value = arrayOfNulls<ElementValue?>(num_values.toInt())
            for (it in 0 until num_values) {
                arrayElementValue.array_value[it] = AnnotationAttribute.readElementValue(source,constant_pools)
            }
            return arrayElementValue
        }
    }

    override fun toString(): String {
        return """ArrayElementValue(
                                    ${super.toString()}
                                    array_value = ${arrayToStr(array_value, "ElementValue")}
                                    )
        """.trimIndent()
    }
}