package clz.attributes

import clz.arrayToStr
import clz.attributes.annotation.*
import clz.constantpool.CpInfo
import clz.u2
import okio.BufferedSource

class RuntimeInvisibleAnnotationsAttribute(val data: Array<Annotation?>,constant_pools: Array<CpInfo?>) :
    AnnotationAttribute(AttributeType.RuntimeInvisibleAnnotations,constant_pools) {
    override fun toString(): String {
        return """
            RuntimeInvisibleAnnotationsAttribute(
             data = 
             ${arrayToStr(data,"Annotation")}    
            )
        """.trimIndent()
    }
}