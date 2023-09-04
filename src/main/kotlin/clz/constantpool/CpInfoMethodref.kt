package clz.constantpool

import clz.u2

class CpInfoMethodref : CpInfo(ConstantPool.CONSTANT_Methodref) {
    lateinit var data: String
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

//        println("CpInfoMethodref evaluate cpInfoClass =  $cpInfoClass")

        val cpInfoNameAndType = constantPools[nameAndTypeIndex.toInt()]
        if (cpInfoNameAndType is CpInfoNameAndType) {
            if (!cpInfoNameAndType.isDataInitialized()) {
                cpInfoNameAndType.evaluate(constantPools)
            }
            this.data = this.data + cpInfoNameAndType.data
        }

//        println("CpInfoMethodref evaluate cpInfoNameAndType =  $cpInfoNameAndType")

    }

    override fun toString(): String {
        return "CpInfoMethodref(classIndex = #$classIndex nameAndTypeIndex = #$nameAndTypeIndex data = $data)"
    }

    fun isDataInitialized() = ::data.isInitialized

}