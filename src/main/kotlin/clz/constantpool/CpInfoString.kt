package clz.constantpool

import clz.u2

class CpInfoString : CpInfo(ConstantPool.CONSTANT_String) {
    lateinit var data :String
    var index: u2 = 0
    override fun evaluate(constantPools: Array<CpInfo?>) {
        val cpInfo = constantPools[index.toInt()]
        if (cpInfo is CpInfoUtf8){
            data = cpInfo.data.utf8()
        }
    }
    override fun toString(): String {
        return "CpInfoString(index = #$index data = $data)"
    }

    fun isDataInitialized() = ::data.isInitialized

    override fun getData(): Any {
        return data
    }
}