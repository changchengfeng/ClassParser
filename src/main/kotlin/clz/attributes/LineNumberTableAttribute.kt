package clz.attributes

import clz.arrayToStr
import clz.constantpool.CpInfo
import clz.u2
import okio.BufferedSource

class LineNumberTableAttribute(val data: Array<LineNumber?> ,constant_pools: Array<CpInfo?>) : AttributeInfo(AttributeType.LineNumberTable,constant_pools) {
    class LineNumber(
        val start_pc: u2,
        val line_number: u2
    ){
        override fun toString(): String {
            return "LineNumber(start_pc=$start_pc, line_number=$line_number)"
        }
    }

    override fun toString(): String {
        return "LineNumberTableAttribute(data=\n${arrayToStr(data,"LineNumber",5)})"
    }

    companion object:ReadAttributeAdapter{
        override fun read(source: BufferedSource,constant_pools: Array<CpInfo?>): AttributeInfo {
            val line_number_table_length = source.readShort()
            val lineNumberTables =
                arrayOfNulls<LineNumber>(line_number_table_length.toInt())
            for (it in 0 until line_number_table_length) {
                val start_pc = source.readShort()
                val line_number = source.readShort()
                val lineNumberTable = LineNumber(
                        start_pc,
                        line_number
                    )
                lineNumberTables[it] = lineNumberTable
            }
           return LineNumberTableAttribute(lineNumberTables,constant_pools)
        }
    }


}