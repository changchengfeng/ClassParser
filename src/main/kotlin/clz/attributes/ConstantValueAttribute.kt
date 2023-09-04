package clz.attributes

import clz.constantpool.CpInfo

class ConstantValueAttribute (val data:Any,constant_pools: Array<CpInfo?>) : AttributeInfo(AttributeType.ConstantValue ,constant_pools){
    override fun toString(): String {
        return "ConstantValueAttribute(data = $data)"
    }
}