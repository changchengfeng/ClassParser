package clz.attributes

import clz.constantpool.CpInfo
import clz.u2

class EnclosingMethodAttribute(val class_index: u2, val method_index: u2, constant_pools: Array<CpInfo?>) :
    AttributeInfo(AttributeType.EnclosingMethod, constant_pools) {
    override fun toString(): String {
        return "EnclosingMethodAttribute(class_index = #${class_index} ${constant_pools[class_index.toInt()]},method_index = #${method_index} ${constant_pools[method_index.toInt()]})"
    }
}