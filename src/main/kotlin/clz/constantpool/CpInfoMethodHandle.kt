package clz.constantpool

import clz.u2

class CpInfoMethodHandle : CpInfo(ConstantPool.CONSTANT_MethodHandle) {
    lateinit var data: String
    lateinit var referenceKind: Reference_kind
    var referenceIndex: u2 = 0

    override fun evaluate(constantPools: Array<CpInfo?>) {
        data = referenceKind.toString()
        val cpInfoReference = constantPools[referenceIndex.toInt()]

        if (cpInfoReference is CpInfoFieldref){
            if (!cpInfoReference.isDataInitialized()) {
                cpInfoReference.evaluate(constantPools)
            }
            data = data+" . "+cpInfoReference.data

        }else if (cpInfoReference is CpInfoMethodref){
            if (!cpInfoReference.isDataInitialized()) {
                cpInfoReference.evaluate(constantPools)
            }
            data = data+" . "+cpInfoReference.data
        }else if (cpInfoReference is CpInfoInterfaceMethodref){
            if (!cpInfoReference.isDataInitialized()) {
                cpInfoReference.evaluate(constantPools)
            }
            data = data+" . "+cpInfoReference.data
        }
    }

    override fun toString(): String {
        return "CpInfoMethodHandle(referenceKind = $referenceKind ,referenceIndex = #$referenceIndex data = $data)"
    }
}