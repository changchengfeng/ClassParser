package clz.attributes

import clz.RA
import clz.constantpool.CpInfo
import clz.constantpool.CpInfoUtf8
import clz.toInt
import clz.u2
import clz.u2Array
import okio.BufferedSource
import okio.buffer
import okio.source
import java.io.ByteArrayInputStream

abstract class AttributeInfo(val type: AttributeType, val constant_pools: Array<CpInfo?>) {


    companion object {

        fun readAttributeInfo(
            attributes_count: u2,
            constant_pools: Array<CpInfo?>,
            buffer_: BufferedSource
        ): Array<AttributeInfo?> {
            val attributes = arrayOfNulls<AttributeInfo?>(attributes_count.toInt())
            for (it in 0 until attributes_count) {
                var attribute: AttributeInfo? = null
                val attribute_name_index = buffer_.readShort()
                val attribute_length = buffer_.readInt()
//                println("attribute_length = $attribute_length")
                val limit = buffer_.readByteArray(attribute_length.toLong())
                val source = ByteArrayInputStream(limit).source().buffer()
                val attributeType =
                    AttributeType.valueOf((constant_pools[attribute_name_index.toInt()] as CpInfoUtf8).data.utf8())
//                println("attributeType = $attributeType")
                when (attributeType) {
                    AttributeType.ConstantValue -> {
                        val index = source.readByteString(attribute_length.toLong()).toInt()
                        attribute = ConstantValueAttribute(
                            constant_pools[index]?.getData() ?: Any(), constant_pools
                        )

                    }

                    AttributeType.Code -> {
                        attribute = CodeAttribute.read(source,constant_pools)
                    }

                    AttributeType.StackMapTable -> {
                        attribute = StackMapTableAttribute.read(source,constant_pools)
                    }

                    AttributeType.Exceptions -> {
                        val numberOfExceptions = source.readShort()
                        val exceptions = u2Array(numberOfExceptions.toInt())
                        for (it in 0 until numberOfExceptions) {
                            exceptions[it] = source.readShort()
                        }
                        attribute = ExceptionsAttribute(exceptions,constant_pools)
                    }

                    AttributeType.InnerClasses -> {
                        attribute = InnerClassesAttribute.read(source,constant_pools)
                    }

                    AttributeType.EnclosingMethod -> {
                        val class_index = source.readShort()
                        val method_index = source.readShort()
                        attribute =
                            EnclosingMethodAttribute(class_index, method_index,constant_pools)
                    }

                    AttributeType.Synthetic -> {
                        attribute = SyntheticAttribute(constant_pools)
                    }

                    AttributeType.Signature -> {
                        val signature_index = source.readShort()
                        attribute = SignatureAttribute(signature_index,constant_pools)
                    }

                    AttributeType.SourceFile -> {
                        val sourceFileIndex = source.readShort()
                        attribute = SourceFileAttribute(sourceFileIndex,constant_pools)
                    }

                    AttributeType.SourceDebugExtension -> {
                        val debugExtensions = source.readByteArray()
                        attribute = SourceDebugExtensionAttribute(debugExtensions,constant_pools)
                    }

                    AttributeType.LineNumberTable -> {
                        attribute = LineNumberTableAttribute.read(source,constant_pools)
                    }

                    AttributeType.LocalVariableTable -> {
                        attribute = LocalVariableTableAttribute.read(source,constant_pools)
                    }

                    AttributeType.LocalVariableTypeTable -> {
                        attribute = LocalVariableTypeTableAttribute.read(source,constant_pools)
                    }

                    AttributeType.Deprecated -> {
                        attribute = DeprecatedAttribute(constant_pools)
                    }

                    AttributeType.RuntimeVisibleAnnotations -> {
                        val num_annotations = source.readShort()
                        val ras = arrayOfNulls<RA>(num_annotations.toInt())
                        for (it in 0 until num_annotations) {
                            ras[it] = AnnotationAttribute.readAnnotation(source,constant_pools)
                        }
                        attribute = RuntimeVisibleAnnotationsAttribute(ras,constant_pools)
                    }
                    AttributeType.RuntimeInvisibleAnnotations -> {
                        val num_annotations = source.readShort()
                        val ras = arrayOfNulls<RA>(num_annotations.toInt())
                        for (it in 0 until num_annotations) {
                            ras[it] = AnnotationAttribute.readAnnotation(source,constant_pools)
                        }
                        attribute = RuntimeInvisibleAnnotationsAttribute(ras,constant_pools)
                    }
                    AttributeType.RuntimeVisibleParameterAnnotations -> {
                        val num_parameters = source.readByte()
                        val parameterAnnotations =
                            arrayOfNulls<AnnotationAttribute.ParameterAnnotations>(num_parameters.toInt())
                        for (it in 0 until num_parameters) {
                            parameterAnnotations[it] = AnnotationAttribute.readParameterAnnotations(source,constant_pools)
                        }
                        attribute = RuntimeVisibleParameterAnnotationsAttribute(parameterAnnotations,constant_pools)
                    }
                    AttributeType.RuntimeInvisibleParameterAnnotations -> {
                        val num_parameters = source.readByte()
                        val parameterAnnotations =
                            arrayOfNulls<AnnotationAttribute.ParameterAnnotations>(num_parameters.toInt())
                        for (it in 0 until num_parameters) {
                            parameterAnnotations[it] = AnnotationAttribute.readParameterAnnotations(source,constant_pools)
                        }
                        attribute = RuntimeInvisibleParameterAnnotationsAttribute(parameterAnnotations,constant_pools)
                    }

                    AttributeType.RuntimeVisibleTypeAnnotations -> {

                        val num_annotations = source.readShort()
                        val TypeAnnotations = arrayOfNulls<AnnotationAttribute.TypeAnnotation>(num_annotations.toInt())
                        for (it in 0 until num_annotations) {
                            TypeAnnotations[it] = AnnotationAttribute.readTypeAnnotations(source,constant_pools)
                        }

                        attribute = RuntimeVisibleTypeAnnotationsAttribute(TypeAnnotations,constant_pools)

                    }
                    AttributeType.RuntimeInvisibleTypeAnnotations -> {
                        val num_annotations = source.readShort()
                        val TypeAnnotations = arrayOfNulls<AnnotationAttribute.TypeAnnotation>(num_annotations.toInt())
                        for (it in 0 until num_annotations) {
                            TypeAnnotations[it] = AnnotationAttribute.readTypeAnnotations(source,constant_pools)
                        }

                        attribute = RuntimeInvisibleTypeAnnotationsAttribute(TypeAnnotations,constant_pools)
                    }

                    AttributeType.AnnotationDefault -> {
                        val elementValue = AnnotationAttribute.readElementValue(source,constant_pools)
                        attribute = AnnotationDefaultAttribute(elementValue,constant_pools)
                    }

                    AttributeType.BootstrapMethods -> {
                        attribute = BootstrapMethodsAttribute.read(source,constant_pools)
                    }

                    AttributeType.MethodParameters -> {
                        val parameters_count = source.readByte()
                        val parameters = arrayOfNulls<Pair<u2, u2>>(parameters_count.toInt())
                        for (it in 0 until parameters_count) {
                            val name_index = source.readShort()
                            val access_flags = source.readShort()
                            parameters[it] = Pair(name_index, access_flags)
                        }
                        attribute = MethodParametersAttribute(parameters,constant_pools)
                    }


                    else -> {

                    }
                }
                attributes[it] = attribute
            }
            return attributes
        }

    }


}