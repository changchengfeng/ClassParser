package clz.constantpool

import clz.u2

class CpInfoDynamic : CpInfo(ConstantPool.CONSTANT_Dynamic) {
    lateinit var data: String
    var bootstrapMethodAttrIndex: u2 = 0
    var nameAndTypeIndex: u2 = 0

    override fun evaluate(constantPools: Array<CpInfo?>) {
        val cpInfoNameAndType = constantPools[nameAndTypeIndex.toInt()]
        if (cpInfoNameAndType is CpInfoNameAndType){
            if (!cpInfoNameAndType.isDataInitialized()) {
                cpInfoNameAndType.evaluate(constantPools)
            }
            data = cpInfoNameAndType.data
        }
    }

    override fun toString(): String {
        return "CpInfoDynamic(bootstrapMethodAttrIndex = #$bootstrapMethodAttrIndex ,nameAndTypeIndex = #$nameAndTypeIndex ,data = $data)"
    }
}