package clz.attributes

import clz.constantpool.CpInfo

class DeprecatedAttribute (constant_pools: Array<CpInfo?>): AttributeInfo(AttributeType.Deprecated,constant_pools) {
    override fun toString(): String {
        return "DeprecatedAttribute()"
    }
}