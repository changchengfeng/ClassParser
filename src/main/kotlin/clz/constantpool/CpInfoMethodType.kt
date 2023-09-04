package clz.constantpool

import clz.u2

class CpInfoMethodType : CpInfo(ConstantPool.CONSTANT_MethodType) {
    lateinit var data :String
    var descriptorIndex: u2 = 0

    override fun evaluate(constantPools: Array<CpInfo?>) {
        val  cpInfoDescriptor = constantPools[descriptorIndex.toInt()]
        if (cpInfoDescriptor is CpInfoUtf8){
            data = cpInfoDescriptor.data.utf8()
        }
    }

    override fun toString(): String {
        return "CpInfoMethodType(descriptorIndex = #$descriptorIndex ,data = $data)"
    }
}