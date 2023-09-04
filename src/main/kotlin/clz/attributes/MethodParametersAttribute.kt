package clz.attributes

import clz.AccessPropertyFlags
import clz.arrayToStr
import clz.constantpool.CpInfo
import clz.u2

class MethodParametersAttribute(val data: Array<Pair<u2, u2>?>,constant_pools: Array<CpInfo?>) : // name_index,access_flags
    AttributeInfo(AttributeType.MethodParameters,constant_pools) {

    override fun toString(): String {
        val stringBuffer = StringBuilder()
        stringBuffer.append("data(")
        for (it in data.indices) {
            val name_index = data[it]?.first?.toInt()
            val nameValue =
            name_index?.let {
                constant_pools[it]
            }
            val access_flags = data[it]?.second
            val access_flagsValue =
                access_flags?.let {
                AccessPropertyFlags.getAccess(access_flags)
            }
            stringBuffer.append("       $data[${it}] = name_index $#$name_index $nameValue}\n")
            stringBuffer.append("       $data[${it}] = access_flags $#$access_flags $access_flagsValue}")
        }
        stringBuffer.append(")")
        return stringBuffer.toString()
    }

}