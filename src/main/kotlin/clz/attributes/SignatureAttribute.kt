package clz.attributes

import clz.constantpool.CpInfo
import clz.u2

class SignatureAttribute (val signatureIndex: u2,constant_pools: Array<CpInfo?>) : AttributeInfo(AttributeType.Signature,constant_pools){
    override fun toString(): String {
        return "SignatureAttribute(signatureIndex=#${signatureIndex} ${constant_pools[signatureIndex.toInt()]})"
    }
}