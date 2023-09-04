package clz.constantpool

import clz.u2
import java.nio.charset.Charset

class CpInfoClass : CpInfo(ConstantPool.CONSTANT_Class) {
    lateinit var data :String
    var nameIndex: u2 = 0
    override fun evaluate(constantPools: Array<CpInfo?>) {
        val cpInfoName = constantPools[nameIndex.toInt()]
        if (cpInfoName is CpInfoUtf8) {
            data = cpInfoName.data.string(Charset.forName("utf-8"))
        }
    }

    fun isDataInitialized() = ::data.isInitialized

    override fun toString(): String {
        return "CpInfoClass(data = $data)"
    }
}