package clz.attributes.annotation

import clz.ClassFile
import clz.constantpool.CpInfo
import clz.u1
import clz.u2
import okio.BufferedSource

class ClassInfoElementValue(tag :u1,constant_pools: Array<CpInfo?>) :ElementValue(tag,TagItem.c,ValueItem.class_info_index,constant_pools){

    var class_info_index : u2 = 0


    companion object :ReadElementValueAdapter{
        override fun read(source: BufferedSource, tag :u1 ,constant_pools: Array<CpInfo?>): ElementValue {
            val classInfoElementValue = ClassInfoElementValue(tag,constant_pools)
            classInfoElementValue.class_info_index= source.readShort()
            return classInfoElementValue
        }
    }
    override fun toString(): String {
        return """ClassInfoElementValue(
                                         ${super.toString()}
                                         class_info_index = #${class_info_index} ${constant_pools[class_info_index.toInt()]}
                                         )
               """.trimIndent()
    }
}