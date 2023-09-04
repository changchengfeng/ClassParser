package clz.attributes.frame

import okio.BufferedSource
import clz.u1
import clz.u2

abstract class StackMapFrame(val type: StackMapFrameEnum) {

    abstract val frame_type: UByte
    var offset_delta: u2 = 0

    companion object {
        fun getByTag(tag: VerificationTypeInfoEnum, source: BufferedSource): VerificationTypeInfo? {
            when (tag) {
                VerificationTypeInfoEnum.Object_variable_info -> {
                    return ObjectVariableVerificationTypeInfo(source.readShort())
                }

                VerificationTypeInfoEnum.Uninitialized_variable_info -> {
                    return UninitializedVariableInfo(source.readShort())
                }

                else -> {
                    return VerificationTypeInfo(tag)
                }
            }
            return null
        }
    }

    override fun toString(): String {
        return "StackMapFrame(type $type offset_delta $offset_delta)"
    }

    open class VerificationTypeInfo(val typeInfo: VerificationTypeInfoEnum){
        override fun toString(): String {
            return "VerificationTypeInfo(typeInfo $typeInfo)"
        }
    }

    class ObjectVariableVerificationTypeInfo(val cpool_index: u2) :
        VerificationTypeInfo(VerificationTypeInfoEnum.Object_variable_info) {
        override fun toString(): String {
            return "ObjectVariableVerificationTypeInfo(cpool_index $cpool_index)"
        }
    }

    class UninitializedVariableInfo(val offset: u2) :
        VerificationTypeInfo(VerificationTypeInfoEnum.Uninitialized_variable_info) {
        override fun toString(): String {
            return "UninitializedVariableInfo(offset $offset)"
        }
    }

}