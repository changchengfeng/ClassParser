package clz.attributes

import clz.constantpool.CpInfo

class SyntheticAttribute(constant_pools: Array<CpInfo?>): AttributeInfo(AttributeType.Synthetic,constant_pools) {
    override fun toString(): String {
        return "SyntheticAttribute()"
    }
}