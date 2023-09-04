package clz.attributes

import clz.ClassFile
import clz.constantpool.CpInfo
import clz.u2
import okio.BufferedSource

class InnerClassesAttribute(val data: Array<Inner?>,constant_pools: Array<CpInfo?>) : AttributeInfo(AttributeType.InnerClasses,constant_pools) {
    class Inner(
        val inner_class_info_index: u2,
        val outer_class_info_index: u2,
        val inner_name_index: u2,
        val inner_class_access_flags: u2,
        val constant_pools: Array<CpInfo?>

    ) {
        override fun toString(): String {
            return "\n      Inner(inner_class_info_index= #${inner_class_info_index} ${constant_pools[inner_class_info_index.toInt()]}\n" +
                    "       outer_class_info_index=#$outer_class_info_index ${constant_pools[outer_class_info_index.toInt()]} \n" +
                    "       inner_name_index=#$inner_name_index ${constant_pools[inner_name_index.toInt()]}\n" +
                    "       inner_class_access_flags=$inner_class_access_flags)"
        }
    }

    override fun toString(): String {
        return "InnerClassesAttribute(data=${data.contentToString()})"
    }

    companion object:ReadAttributeAdapter{
        override fun read(source: BufferedSource,constant_pools: Array<CpInfo?>): AttributeInfo {
            val number_of_classes = source.readShort()
            val inners = arrayOfNulls<Inner>(number_of_classes.toInt())
            for (it in 0 until number_of_classes) {
                val inner_class_info_index = source.readShort()
                val outer_class_info_index = source.readShort()
                val inner_name_index = source.readShort()
                val inner_class_access_flags = source.readShort()
                val inner = Inner(
                    inner_class_info_index,
                    outer_class_info_index,
                    inner_name_index,
                    inner_class_access_flags,
                    constant_pools
                )
                inners[it] = inner
            }
            return InnerClassesAttribute(inners,constant_pools)
        }

    }

}