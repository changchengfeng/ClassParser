package clz.attributes

import clz.arrayToStr
import clz.constantpool.CpInfo
import clz.u2
import okio.BufferedSource

class LocalVariableTableAttribute(val data: Array<LocalVariableTable?>, constant_pools: Array<CpInfo?>) :
    AttributeInfo(AttributeType.LocalVariableTable, constant_pools) {
    class LocalVariableTable(
        val start_pc: u2,
        val length: u2,
        val name_index: u2,
        val descriptor_index: u2,
        val index: u2,
        val constant_pools: Array<CpInfo?>

    ) {
        override fun toString(): String {
            return "(start_pc=$start_pc,length=$length,name_index=$name_index ${constant_pools[name_index.toInt()]}, descriptor_index=$descriptor_index ${constant_pools[descriptor_index.toInt()]}, index=$index)"
        }
    }

    override fun toString(): String {
        return "LocalVariableTableAttribute(data=\n${arrayToStr(data, "LocalVariableTable", 5)})"
    }

    companion object : ReadAttributeAdapter {
        override fun read(source: BufferedSource, constant_pools: Array<CpInfo?>): AttributeInfo {
            val local_variable_table_length = source.readShort()
            val localVariableTables =
                arrayOfNulls<LocalVariableTable>(local_variable_table_length.toInt())
            for (it in 0 until local_variable_table_length) {
                val start_pc = source.readShort()
                val length = source.readShort()
                val name_index = source.readShort()
                val descriptor_index = source.readShort()
                val index = source.readShort()
                val localVariableTable = LocalVariableTable(
                    start_pc,
                    length,
                    name_index,
                    descriptor_index,
                    index,
                    constant_pools
                )
                localVariableTables[it] = localVariableTable
            }
            return LocalVariableTableAttribute(localVariableTables, constant_pools)
        }
    }

}