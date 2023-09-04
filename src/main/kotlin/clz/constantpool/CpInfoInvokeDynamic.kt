package clz.constantpool

import clz.u2

class CpInfoInvokeDynamic : CpInfo(ConstantPool.CONSTANT_InvokeDynamic) {
    lateinit var data: String
    var bootstrapMethodAttrIndex: u2 = 0
    var nameAndTypeIndex: u2 = 0

    override fun evaluate(constantPools: Array<CpInfo?>) {
        val cpInfoNameAndType = constantPools[nameAndTypeIndex.toInt()]
        if (cpInfoNameAndType is CpInfoNameAndType){
            if (!cpInfoNameAndType.isDataInitialized()) {
                cpInfoNameAndType.evaluate(constantPools)
            }
            data = cpInfoNameAndType.toString()
        }
    }

    override fun toString(): String {
        return "CpInfoInvokeDynamic(bootstrapMethodAttrIndex = #$bootstrapMethodAttrIndex ,nameAndTypeIndex = #$nameAndTypeIndex ,data = $data)"
    }
}