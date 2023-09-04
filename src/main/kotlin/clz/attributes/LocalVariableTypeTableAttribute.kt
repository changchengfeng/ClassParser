package clz.attributes

import clz.arrayToStr
import clz.constantpool.CpInfo
import clz.u2
import okio.BufferedSource

class LocalVariableTypeTableAttribute(val data: Array<LocalVariableTypeTable?>, constant_pools: Array<CpInfo?>) :
    AttributeInfo(AttributeType.LocalVariableTypeTable, constant_pools) {
    class LocalVariableTypeTable(
        val start_pc: u2,
        val length: u2,
        val name_index: u2,
        val signature_index: u2,
        val index: u2,
        val constant_pools: Array<CpInfo?>
    ) {
        override fun toString(): String {
            return "LocalVariableTypeTable( start_pc $start_pc length $length name_index #$name_index ${constant_pools[name_index.toInt()]} \n" +
                    "signature_index #$signature_index ${constant_pools[signature_index.toInt()]} index $index)"
        }
    }

    companion object : ReadAttributeAdapter {
        override fun read(source: BufferedSource, constant_pools: Array<CpInfo?>): AttributeInfo {
            val local_variable_type_table_length = source.readShort()
            val localVariableTypeTables =
                arrayOfNulls<LocalVariableTypeTable>(
                    local_variable_type_table_length.toInt()
                )
            for (it in 0 until local_variable_type_table_length) {
                val start_pc = source.readShort()
                val length = source.readShort()
                val name_index = source.readShort()
                val signature_index = source.readShort()
                val index = source.readShort()
                val localVariableTypeTable = LocalVariableTypeTable(
                    start_pc,
                    length,
                    name_index,
                    signature_index,
                    index,
                    constant_pools
                )
                localVariableTypeTables[it] = localVariableTypeTable
            }
            return LocalVariableTypeTableAttribute(localVariableTypeTables, constant_pools)
        }
    }


    override fun toString(): String {
        return "LocalVariableTypeTableAttribute( data = ${arrayToStr(data,"LocalVariableTypeTable ")})"
    }

//    override fun toString(): String {
//        val stringBuffer = StringBuilder()
//        stringBuffer.append("LocalVariableTypeTableAttribute(")
//        stringBuffer.append("data = ")
//        for (it in data.indices) {
//
//        }
//        stringBuffer.append(")")
//        return stringBuffer.toString()
//    }
}