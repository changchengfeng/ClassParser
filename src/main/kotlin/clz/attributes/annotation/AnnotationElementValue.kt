package clz.attributes.annotation

import clz.attributes.AnnotationAttribute
import clz.constantpool.CpInfo
import clz.u1
import okio.BufferedSource

class AnnotationElementValue(tag: u1,constant_pools: Array<CpInfo?>) : ElementValue(tag,TagItem.at,ValueItem.annotation_value,constant_pools) {

    var annotation_value : AnnotationAttribute.Annotation? =null

    companion object :ReadElementValueAdapter{
        override fun read(source: BufferedSource, tag :u1,constant_pools: Array<CpInfo?>): ElementValue {
            val annotationElementValue = AnnotationElementValue(tag,constant_pools)
            annotationElementValue.annotation_value= AnnotationAttribute.readAnnotation(source,constant_pools)
            return annotationElementValue
        }
    }

    override fun toString(): String {
        return """AnnotationElementValue(
                                         ${super.toString()}
                                         annotation_value = ${annotation_value}
                                         )
        """.trimIndent()
    }
}