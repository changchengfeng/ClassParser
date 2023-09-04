package clz.attributes

import clz.arrayToStr
import clz.attributes.annotation.*
import clz.constantpool.CpInfo
import clz.u2
import okio.BufferedSource

class RuntimeInvisibleParameterAnnotationsAttribute(val data: Array<ParameterAnnotations?>,constant_pools: Array<CpInfo?>) :
    AnnotationAttribute(AttributeType.RuntimeInvisibleParameterAnnotations,constant_pools) {
    override fun toString(): String {
        return """
            RuntimeInvisibleParameterAnnotationsAttribute(
             data = 
             ${arrayToStr(data,"Annotation")}    
            )
        """.trimIndent()
    }
}