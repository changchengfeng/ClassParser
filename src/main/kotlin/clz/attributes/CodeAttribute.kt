package clz.attributes

import clz.*
import clz.constantpool.CpInfo
import clz.methods.Opcodes
import clz.methods.OpcodesBodyType
import okio.ByteString.Companion.toByteString
import okio.BufferedSource
import java.lang.StringBuilder

class CodeAttribute(val max_stack: u2, val max_locals: u2,constant_pools: Array<CpInfo?>) :
    AttributeInfo(AttributeType.Code,constant_pools) {
    lateinit var codes: u1Array
    lateinit var exceptionTables: Array<ExceptionTable?>
    lateinit var attributes: Array<AttributeInfo?>

    class ExceptionTable(val start_pc: u2, val end_pc: u2, val handler_pc: u2, val catch_type: u2) {
        override fun toString(): String {
            return "ExceptionTable(start_pc=$start_pc, end_pc=$end_pc, handler_pc=$handler_pc, catch_type=$catch_type)"
        }
    }

    companion object : ReadAttributeAdapter {
        override fun read(source: BufferedSource,constant_pools: Array<CpInfo?>): AttributeInfo {
            val max_stack = source.readShort()
            val max_locals = source.readShort()
            val codeAttribute = CodeAttribute(max_stack, max_locals,constant_pools)
            val code_length = source.readInt()
            codeAttribute.codes = u1Array(code_length)
            for (it in 0 until code_length) {
                codeAttribute.codes[it] = source.readByte()
            }
            val exception_table_length = source.readShort()
            codeAttribute.exceptionTables = arrayOfNulls(exception_table_length.toInt())
            for (it in 0 until exception_table_length) {
                val start_pc = source.readShort()
                val end_pc = source.readShort()
                val handler_pc = source.readShort()
                val catch_type = source.readShort()
                codeAttribute.exceptionTables[it] =
                    ExceptionTable(
                        start_pc,
                        end_pc,
                        handler_pc,
                        catch_type
                    )
            }
            val attributesCount = source.readShort()
            codeAttribute.attributes = readAttributeInfo(
                attributesCount,
                constant_pools,
                source
            )
            return codeAttribute
        }

    }


    override fun toString(): String {
        return """
     CodeAttribute{
         max_stack = $max_stack
         max_locals = $max_locals
         codes = ${"\n"}${codesToStr()}
         exceptionTables =${"\n"}${arrayToStr(exceptionTables, "exceptionTables")}
         attributes = ${"\n"}${arrayToStr(attributes, "attributes")}
        }
     """
    }

    fun codesToStr(): String {
        val stringBuffer = StringBuilder()
        var it = 0
        var current = 0
        while (it < codes.size) {
            current = it
            stringBuffer.append("          code[${it}] = ${codes[it].toUByte()} ")
            val Opcode = Opcodes.valueOf(codes[it].toUByte())
            stringBuffer.append(Opcode)
            it++
            if (Opcode.type.size > 0) {
                for (index in 0 until Opcode.type.size) {
                    when (Opcode.type[index]) {
                        Opcodes.Type.VALUE_1 -> {
                            if (Opcode == Opcodes.newarray) {
                                stringBuffer.append(" args[#$index] = ${OpcodesBodyType.valueOf(codes[it].toUByte())}")
                            } else {
                                stringBuffer.append(" args[#$index] = ${codes[it]}")
                            }
                            it++
                        }
                        Opcodes.Type.VALUE_2 -> {
                            val toInt = byteArrayOf(codes[it], codes[it + 1]).toByteString().toInt()
                            stringBuffer.append(" args[#$index] = ${toInt}")
                            it += 2
                        }
                        Opcodes.Type.VALUE_4 -> {
                            val toInt =
                                byteArrayOf(codes[it], codes[it + 1], codes[it + 2], codes[it + 3]).toByteString()
                                    .toInt()
                            stringBuffer.append(" args[#$index] = ${toInt}")
                            it += 4
                        }
                        Opcodes.Type.CONSTANT_POOLS_1 -> {
                            stringBuffer.append(" args[#$index] = ${constant_pools[codes[it].toInt()]}")
                            it++
                        }
                        Opcodes.Type.CONSTANT_POOLS_2 -> {
                            val toInt = byteArrayOf(codes[it], codes[it + 1]).toByteString().toInt()
                            stringBuffer.append(" args[#$index] = ${constant_pools[toInt]}")
                            it += 2
                        }
                        Opcodes.Type.TABLES_WITCH -> {
                            val pad = (it % 4).let { if (it == 0) 0 else 4 - it }
                            it += pad
                            val offset_default = codes.readInt(it)  // offset_default 是当前指令的偏移量
                            it += 4
                            val lowValue = codes.readInt(it)
                            it += 4
                            val highValue = codes.readInt(it)
                            it += 4
                            stringBuffer.append(" pad $pad offset_default $offset_default lowValue $lowValue highValue $highValue \n\n")
                            for (vi in lowValue..highValue) {
                                val offfsets = codes.readInt(it)
                                stringBuffer.append("                jump[$vi] = offfsets ${offfsets} jumpto = ${current + offfsets} \n")
                                it += 4
                            }
                        }
                        Opcodes.Type.LOOKUP_SWITCH -> {
                            val pad = (it % 4).let { if (it == 0) 0 else 4 - it }
                            it += pad
                            val offset_default = codes.readInt(it)  // offset_default 是当前指令的偏移量
                            it += 4
                            val npairs = codes.readInt(it)
                            it += 4
                            stringBuffer.append(" pad $pad offset_default $offset_default  npairs $npairs \n\n")
                            for (vi in 0 until npairs) {
                                stringBuffer.append("                jump[$vi] = val ${codes.readInt(it)}")
                                it += 4
                                val offfsets = codes.readInt(it)
                                stringBuffer.append("  offsets ${offfsets} jumpto = ${current + offfsets} \n")
                                it += 4
                            }
                        }
                        Opcodes.Type.WIDE -> {
                            val toUByte = codes[it].toUByte()
                            if (toUByte == Opcodes.iinc.value) {
                                stringBuffer.append(
                                    " args[#$index] = ${Opcodes.iinc} :  index = ${
                                        byteArrayOf(
                                            codes[it + 1],
                                            codes[it + 2]
                                        ).toByteString().toInt()
                                    } const = ${byteArrayOf(codes[it + 3], codes[it + 4]).toByteString().toInt()}"
                                )
                                it += 5
                            } else {
                                stringBuffer.append(
                                    " args[#$index] = ${Opcodes.valueOf(toUByte)} :  index = ${
                                        byteArrayOf(
                                            codes[it + 1],
                                            codes[it + 2]
                                        ).toByteString().toInt()
                                    }}"
                                )
                                it += 3
                            }

                        }
                    }
                }
            }
            stringBuffer.append("\n")
        }
        return stringBuffer.toString()
    }
}