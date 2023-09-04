package clz.attributes

import clz.arrayToStr
import clz.attributes.annotation.*
import clz.constantpool.CpInfo
import clz.u2
import okio.BufferedSource

class RuntimeVisibleParameterAnnotationsAttribute(val data: Array<ParameterAnnotations?>,constant_pools: Array<CpInfo?>) :
    AnnotationAttribute(AttributeType.RuntimeVisibleParameterAnnotations,constant_pools) {
    override fun toString(): String {
        return """
            RuntimeVisibleParameterAnnotationsAttribute(
             data = 
             ${arrayToStr(data, "ParameterAnnotations")}    
            )
        """.trimIndent()
    }





}