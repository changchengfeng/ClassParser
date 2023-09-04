package clz.attributes

import clz.arrayToStr
import clz.constantpool.CpInfo

class RuntimeVisibleTypeAnnotationsAttribute(val data: Array<TypeAnnotation?> ,constant_pools: Array<CpInfo?>) :
    AnnotationAttribute(AttributeType.RuntimeVisibleTypeAnnotations,constant_pools) {
    override fun toString(): String {
        return """
            RuntimeVisibleTypeAnnotationsAttribute(
             data = 
             ${arrayToStr(data,"TypeAnnotation")}    
            )
        """.trimIndent()
    }
}