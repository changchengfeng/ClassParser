package clz.attributes

import clz.arrayToStr
import clz.constantpool.CpInfo

class RuntimeVisibleAnnotationsAttribute(val annotations: Array<Annotation?>,constant_pools: Array<CpInfo?>) :
    AnnotationAttribute(AttributeType.RuntimeVisibleAnnotations,constant_pools) {
    override fun toString(): String {
        return """RuntimeVisibleAnnotationsAttribute(
                                                     annotations = ${arrayToStr(annotations, "Annotation")}
                                                    )
            """.trimIndent()

    }
}