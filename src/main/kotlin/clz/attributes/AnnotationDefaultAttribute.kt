package clz.attributes

import clz.ClassFile
import clz.attributes.annotation.ElementValue
import clz.constantpool.CpInfo
import clz.u2

class AnnotationDefaultAttribute(val default_value: ElementValue,constant_pools: Array<CpInfo?>) : AttributeInfo(AttributeType.AnnotationDefault,constant_pools) {
    override fun toString(): String {
        return "AnnotationDefaultAttribute(default_value=${default_value})"
    }
}