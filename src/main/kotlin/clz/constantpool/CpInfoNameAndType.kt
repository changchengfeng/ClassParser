package clz.constantpool

import clz.u2

class CpInfoNameAndType : CpInfo(ConstantPool.CONSTANT_NameAndType) {
    lateinit var data :String
    var nameIndex: u2 = 0
    var descriptorIndex: u2 = 0

    override fun evaluate(constantPools: Array<CpInfo?>) {
        val cpInfoName = constantPools[nameIndex.toInt()]
        if (cpInfoName is CpInfoUtf8){
            data = cpInfoName.data.utf8()+" # "
        }
        val cpInfoDescriptor = constantPools[descriptorIndex.toInt()]
        if (cpInfoDescriptor is CpInfoUtf8){
            data = data + cpInfoDescriptor.data.utf8()
        }
    }

    override fun toString(): String {
        return "CpInfoNameAndType(nameIndex = #$nameIndex, descriptorIndex = #$descriptorIndex, data = $data )"
    }

    fun isDataInitialized() = ::data.isInitialized
}