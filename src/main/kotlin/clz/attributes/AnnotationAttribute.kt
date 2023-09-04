package clz.attributes

import clz.arrayToStr
import clz.attributes.annotation.*
import clz.attributes.annotation.path.TypePathKind
import clz.attributes.annotation.targets.*
import clz.constantpool.CpInfo
import clz.u1
import clz.u2
import okio.BufferedSource

open class AnnotationAttribute(type: AttributeType,constant_pools: Array<CpInfo?>) : AttributeInfo(type,constant_pools) {
    companion object {
        fun readAnnotation(source: BufferedSource,constant_pools: Array<CpInfo?>): Annotation {
            val type_index = source.readShort()
            val num_element_value_pairs = source.readShort()
            val element_value_pairs = arrayOfNulls<ElementValuePair?>(num_element_value_pairs.toInt())
            for (it in 0 until num_element_value_pairs) {
                val element_name_index = source.readShort()
                val element_value = readElementValue(source,constant_pools)
                element_value_pairs[it] = ElementValuePair(element_name_index, element_value,constant_pools)
            }
            return Annotation(type_index, element_value_pairs,constant_pools)
        }

        fun readParameterAnnotations(source: BufferedSource ,constant_pools: Array<CpInfo?>): ParameterAnnotations {
            val num_annotations = source.readShort()
            val annotations = arrayOfNulls<Annotation?>(num_annotations.toInt())
            for (it in 0 until num_annotations) {

                annotations[it] = readAnnotation(source,constant_pools)
            }
            return ParameterAnnotations(annotations)
        }

        fun readElementValue(source: BufferedSource,constant_pools: Array<CpInfo?>): ElementValue {
            val tag = source.readByte()
            val valueItem = ValueItem.getTagItemByValue(tag)
            var elementValue: ElementValue
            when (valueItem) {
                ValueItem.const_value_index -> {
                    elementValue = ConstElementValue.read(source, tag,constant_pools)
                }
                ValueItem.enum_const_value -> {
                    elementValue = EnumConstElementValue.read(source, tag,constant_pools)
                }
                ValueItem.class_info_index -> {
                    elementValue = ClassInfoElementValue.read(source, tag,constant_pools)

                }
                ValueItem.annotation_value -> {
                    elementValue = AnnotationElementValue.read(source, tag, constant_pools)
                }
                ValueItem.array_value -> {
                    elementValue = ArrayElementValue.read(source, tag,constant_pools)
                }
            }
            return elementValue
        }

        fun readTypeAnnotations(source: BufferedSource,constant_pools: Array<CpInfo?>): TypeAnnotation? {
            val target_type = source.readByte()
            val enumTargetInfo = EnumTargetInfo.getTargetInfoByValue(target_type)
            var targetInfo: TargetInfo
            when (enumTargetInfo) {
                EnumTargetInfo.type_parameter_target -> {
                    targetInfo = TypeParameterTarget(source.readByte())
                }
                EnumTargetInfo.supertype_target -> {
                    targetInfo = SuperTypeTarget(source.readShort())
                }
                EnumTargetInfo.type_parameter_bound_target -> {
                    targetInfo = TypeParameterBoundTarget(source.readByte(), source.readByte())
                }
                EnumTargetInfo.empty_target -> {
                    targetInfo = EmptyTarget()
                }
                EnumTargetInfo.method_formal_parameter_target -> {
                    targetInfo = MethodFormalParameterTarget(source.readByte())
                }
                EnumTargetInfo.throws_target -> {
                    targetInfo = ThrowsTarget(source.readShort())
                }
                EnumTargetInfo.localvar_target -> {
                    val table_length = source.readShort()
                    val tables = arrayOfNulls<LocalVarTarget.Table>(table_length.toInt())
                    for (it in 0 until table_length) {
                        val start_pc = source.readShort()
                        val length = source.readShort()
                        val index = source.readShort()
                        tables[it] = LocalVarTarget.Table(start_pc, length, index)
                    }
                    targetInfo = LocalVarTarget(tables)
                }
                EnumTargetInfo.catch_target -> {
                    targetInfo = CatchTarget(source.readShort())

                }
                EnumTargetInfo.offset_target -> {
                    targetInfo = OffsetTarget(source.readShort())

                }
                EnumTargetInfo.type_argument_target -> {
                    targetInfo = TypeArgumentTarget(source.readShort(), source.readByte())

                }
            }
            val kind = TypePathKind.getTypePathKindByValue(source.readByte())
            val type_argument_index = source.readByte()
            var type_path = TypePath(kind, type_argument_index)

            val type_index = source.readShort()
            val num_element_value_pairs = source.readShort()
            val element_value_pairs = arrayOfNulls<ElementValuePair?>(num_element_value_pairs.toInt())
            for (it in 0 until num_element_value_pairs) {
                val element_name_index = source.readShort()
                val element_value = readElementValue(source,constant_pools)
                element_value_pairs[it] = ElementValuePair(element_name_index, element_value,constant_pools)
            }
            return TypeAnnotation(target_type, targetInfo, type_path, type_index, element_value_pairs,constant_pools)

        }
    }


    open class Annotation(
        val type_index: u2,
        val element_value_pairs: Array<ElementValuePair?>,
        val constant_pools: Array<CpInfo?>
    ) {
        override fun toString(): String {
            return """Annotation(
                                 type_index = $type_index ${constant_pools[type_index.toInt()]}
                                 element_value_pairs =${arrayToStr(element_value_pairs, "ElementValuePair")}
                    )
                    """.trimIndent()
        }
    }

    class ElementValuePair(val element_name_index: u2, val value: ElementValue,val constant_pools: Array<CpInfo?>) {
        override fun toString(): String {
            return """ElementValuePair(
                                        element_name_index = #$element_name_index ${constant_pools[element_name_index.toInt()]}
                                        value = ${value}
                                      )
                """.trimIndent()
        }
    }

    class ParameterAnnotations(val data: Array<Annotation?>) {
        override fun toString(): String {
            return """
            ParameterAnnotations(
             data = 
             ${arrayToStr(data, "Annotation")}    
            )
        """.trimIndent()
        }
    }

    class TypeAnnotation(
        val target_type: u1,
        val target_info: TargetInfo,
        val path: TypePath,
        type_index: u2,
        element_value_pairs: Array<ElementValuePair?>,
        constant_pools: Array<CpInfo?>
    ) : Annotation(type_index, element_value_pairs,constant_pools) {
        override fun toString(): String {
            return """
            TypeAnnotation(
             target_type = $target_type
             target_info = $target_info
             type_index = $type_index
             path = $path
             element_value_pairs =
                    ${arrayToStr(element_value_pairs, "ElementValuePair")}
            )
        """.trimIndent()
        }
    }

    class TypePath(val kind: TypePathKind, val type_argument_index: u1) {

        override fun toString(): String {
            return """
                TypePath(
                    kind = $kind
                    type_argument_index = $type_argument_index
                )
            """.trimIndent()
        }
    }
}