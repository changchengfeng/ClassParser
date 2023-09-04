package clz.attributes

import clz.arrayToStr
import clz.constantpool.CpInfo

class RuntimeInvisibleTypeAnnotationsAttribute(val data: Array<TypeAnnotation?> ,constant_pools: Array<CpInfo?>) :
    AnnotationAttribute(AttributeType.RuntimeInvisibleTypeAnnotations,constant_pools) {
    override fun toString(): String {
        return """
            RuntimeInvisibleTypeAnnotationsAttribute(
             data = 
             ${arrayToStr(data,"TypeAnnotation")}    
            )
        """.trimIndent()
    }
}