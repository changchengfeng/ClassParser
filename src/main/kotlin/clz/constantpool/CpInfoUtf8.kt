package clz.constantpool

import okio.ByteString

class CpInfoUtf8 : CpInfo(ConstantPool.CONSTANT_Utf8) {
    lateinit var data :ByteString

    override fun toString(): String {
        return "CpInfoUtf8(data = ${data.utf8()})"
    }

    fun isDataInitialized() = ::data.isInitialized

    override fun getData(): Any {
        return data
    }
}