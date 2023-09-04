package clz.attributes

import clz.arrayToStr
import clz.attributes.frame.*
import clz.constantpool.CpInfo
import okio.BufferedSource

class StackMapTableAttribute(val stackMapFrames: Array<StackMapFrame?>, constant_pools: Array<CpInfo?>) :
    AttributeInfo(AttributeType.StackMapTable, constant_pools) {
    companion object : ReadAttributeAdapter {
        override fun read(source: BufferedSource, constant_pools: Array<CpInfo?>): AttributeInfo {
            val number_of_entries = source.readShort()
            val stackMapFrames = arrayOfNulls<StackMapFrame>(number_of_entries.toInt())
            for (it in 0 until number_of_entries) {
                val frame_type = source.readByte().toUByte()
                when (frame_type) {
                    in 0u..63u -> {
                        stackMapFrames[it] = SameFrame(frame_type).also { it.offset_delta = it.frame_type.toShort() }
                    }

                    in 64u..127u -> {
                        val sameLocalsStackItemFrame = SameLocals_1_StackItemFrame(frame_type).also {
                            it.offset_delta = (it.frame_type - 64u).toShort()
                        }
                        val tag = VerificationTypeInfoEnum.getByTag(source.readByte())
                        val verificationTypeInfo = tag?.let {
                            StackMapFrame.getByTag(tag, source)
                        }
                        sameLocalsStackItemFrame.stack = arrayOf(verificationTypeInfo)
                        stackMapFrames[it] = sameLocalsStackItemFrame

                    }

                    247.toUByte() -> {
                        val sameLocalsStackItemFrameExtended =
                            SameLocals_1_StackItemFrameExtended(frame_type)
                        sameLocalsStackItemFrameExtended.offset_delta = source.readShort()
                        val tag = VerificationTypeInfoEnum.getByTag(source.readByte())
                        val verificationTypeInfo = tag?.let {
                            StackMapFrame.getByTag(tag, source)
                        }
                        sameLocalsStackItemFrameExtended.stack = arrayOf(verificationTypeInfo)
                        stackMapFrames[it] = sameLocalsStackItemFrameExtended
                    }

                    in 248u..250u -> {
                        val chopFrame = ChopFrame(frame_type).also { it.k = (251u - it.frame_type).toByte() }
                        chopFrame.offset_delta = source.readShort()
                        stackMapFrames[it] = chopFrame

                    }

                    251.toUByte() -> {
                        val sameFrameExtended = SameFrameExtended(frame_type)
                        sameFrameExtended.offset_delta = source.readShort()
                        stackMapFrames[it] = sameFrameExtended


                    }

                    in 252u..254u -> {
                        val appendFrame = AppendFrame(frame_type).also { it.k = (it.frame_type - 251u).toByte() }
                        appendFrame.offset_delta = source.readShort()
                        val verificationTypeInfos =
                            arrayOfNulls<StackMapFrame.VerificationTypeInfo?>(appendFrame.k.toInt())
                        for (it in 0 until appendFrame.k) {
                            val tag = VerificationTypeInfoEnum.getByTag(source.readByte())
                            verificationTypeInfos[it] = tag?.let {
                                StackMapFrame.getByTag(tag, source)
                            }

                        }
                        appendFrame.locals = verificationTypeInfos
                        stackMapFrames[it] = appendFrame

                    }

                    255.toUByte() -> {
                        val fullFrame = FullFrame(frame_type)
                        fullFrame.offset_delta = source.readShort()
                        fullFrame.number_of_locals = source.readShort()
                        var verificationTypeInfos =
                            arrayOfNulls<StackMapFrame.VerificationTypeInfo?>(fullFrame.number_of_locals.toInt())
                        for (it in 0 until fullFrame.number_of_locals) {
                            val tag = VerificationTypeInfoEnum.getByTag(source.readByte())
                            verificationTypeInfos[it] = tag?.let {
                                StackMapFrame.getByTag(tag, source)
                            }

                        }
                        fullFrame.locals = verificationTypeInfos

                        fullFrame.number_of_stack_items = source.readShort()
                        verificationTypeInfos =
                            arrayOfNulls<StackMapFrame.VerificationTypeInfo?>(fullFrame.number_of_stack_items.toInt())
                        for (it in 0 until fullFrame.number_of_stack_items) {
                            val tag = VerificationTypeInfoEnum.getByTag(source.readByte())
                            verificationTypeInfos[it] = tag?.let {
                                StackMapFrame.getByTag(tag, source)
                            }

                        }
                        fullFrame.stack = verificationTypeInfos
                        stackMapFrames[it] = fullFrame

                    }
                }
            }
            return StackMapTableAttribute(stackMapFrames, constant_pools)
        }

    }

    override fun toString(): String {
        return "StackMapTableAttribute(stackMapFrames ${arrayToStr(stackMapFrames, "stackMapFrames")})"
    }
}