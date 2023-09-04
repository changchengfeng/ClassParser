package clz.attributes

import clz.constantpool.CpInfo
import clz.u2Array

class ExceptionsAttribute (val numberOfExceptions: u2Array,constant_pools: Array<CpInfo?>) : AttributeInfo(AttributeType.Exceptions,constant_pools){

    override fun toString(): String {
        return "ExceptionsAttribute(numberOfExceptions=${numberOfExceptions.contentToString()})"
    }
}