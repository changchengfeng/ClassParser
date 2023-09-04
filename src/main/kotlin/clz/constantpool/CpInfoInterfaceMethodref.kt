package clz.constantpool

import clz.u2

class CpInfoInterfaceMethodref : CpInfo(ConstantPool.CONSTANT_InterfaceMethodref) {
    lateinit var data :String
    var classIndex: u2 = 0
    var nameAndTypeIndex: u2 = 0

    override fun evaluate(constantPools: Array<CpInfo?>) {
        val cpInfoClass = constantPools[classIndex.toInt()]
        if (cpInfoClass is CpInfoClass) {
            if (!cpInfoClass.isDataInitialized()) {
                cpInfoClass.evaluate(constantPools)
            }
            this.data = cpInfoClass.data + " . "
        }
        val cpInfoNameAndType = constantPools[nameAndTypeIndex.toInt()]
        if (cpInfoNameAndType is CpInfoNameAndType) {
            if (!cpInfoNameAndType.isDataInitialized()) {
                cpInfoNameAndType.evaluate(constantPools)
            }
            this.data = this.data + cpInfoNameAndType.data
        }
    }

    override fun toString(): String {
        return "CpInfoInterfaceMethodref(classIndex = #$classIndex nameAndTypeIndex = #$nameAndTypeIndex data = $data)"
    }

    fun isDataInitialized() = ::data.isInitialized

}