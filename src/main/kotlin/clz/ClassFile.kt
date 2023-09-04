package clz

import clz.attributes.AttributeInfo
import clz.constantpool.*
import clz.fields.FieldInfo
import clz.interfaces.Interface
import clz.methods.MethodInfo
import okio.BufferedSource

class ClassFile(buffer: BufferedSource) {
    private val magic: u4
    private val minor_version: u2
    private val major_version: u2
    private val constant_pool_count: u2
    private val constant_pools: Array<CpInfo?>
    private val access_flags: u2
    private val this_class: u2
    private val super_class: u2
    private val interfaces_count: u2
    private val interfaces: Array<Interface?>
    private val fields_count: u2
    private val fields: Array<FieldInfo?>
    private val methods_count: u2
    private val methods: Array<MethodInfo?>
    private val attributes_count: u2
    private val attributes: Array<AttributeInfo?>


    init {
        magic = buffer.readInt()
        println("magic = ${magic.toByteString().hex()}")
        minor_version = buffer.readShort()
        println("minor_version = ${minor_version}")
        major_version = buffer.readShort()
        println("major_version = ${major_version}")
        constant_pool_count = buffer.readShort()
        println("constant_pool_count = ${constant_pool_count}")
        constant_pools = arrayOfNulls(constant_pool_count.toInt())

        var it = 1
        while (it < constant_pool_count) {
            var cpInfo: CpInfo? = null
            val tag = buffer.readByte()
            when (tag) {
                ConstantPool.CONSTANT_Utf8.tag -> {
                    cpInfo = CpInfoUtf8()
                    val length = buffer.readShort()
                    cpInfo.data = buffer.readByteString(length.toLong())
                }

                ConstantPool.CONSTANT_Integer.tag -> {
                    cpInfo = CpInfoInteger()
                    cpInfo.data = buffer.readInt()

                }

                ConstantPool.CONSTANT_Long.tag -> {
                    cpInfo = CpInfoLong()
                    cpInfo.data = buffer.readLong()
                    constant_pools[it] = cpInfo
                    it += 2
                    continue
                }

                ConstantPool.CONSTANT_Float.tag -> {
                    cpInfo = CpInfoFloat()
                    cpInfo.data = buffer.readFloat()
                }

                ConstantPool.CONSTANT_Double.tag -> {
                    cpInfo = CpInfoDouble()
                    cpInfo.data = buffer.readDouble()
                    constant_pools[it] = cpInfo
                    it += 2
                    continue
                }

                ConstantPool.CONSTANT_String.tag -> {
                    cpInfo = CpInfoString()
                    cpInfo.index = buffer.readShort()
                }

                ConstantPool.CONSTANT_NameAndType.tag -> {
                    cpInfo = CpInfoNameAndType()
                    cpInfo.nameIndex = buffer.readShort()
                    cpInfo.descriptorIndex = buffer.readShort()
                }

                ConstantPool.CONSTANT_Fieldref.tag -> {
                    cpInfo = CpInfoFieldref()
                    cpInfo.classIndex = buffer.readShort()
                    cpInfo.nameAndTypeIndex = buffer.readShort()
                }

                ConstantPool.CONSTANT_Methodref.tag -> {
                    cpInfo = CpInfoMethodref()
                    cpInfo.classIndex = buffer.readShort()
                    cpInfo.nameAndTypeIndex = buffer.readShort()
                }

                ConstantPool.CONSTANT_InterfaceMethodref.tag -> {
                    cpInfo = CpInfoInterfaceMethodref()
                    cpInfo.classIndex = buffer.readShort()
                    cpInfo.nameAndTypeIndex = buffer.readShort()
                }

                ConstantPool.CONSTANT_Class.tag -> {
                    cpInfo = CpInfoClass()
                    cpInfo.nameIndex = buffer.readShort()
                }

                ConstantPool.CONSTANT_MethodType.tag -> {
                    cpInfo = CpInfoMethodType()
                    cpInfo.descriptorIndex = buffer.readShort()
                }

                ConstantPool.CONSTANT_MethodHandle.tag -> {
                    cpInfo = CpInfoMethodHandle()
                    cpInfo.referenceKind = Reference_kind.valueOf(buffer.readByte())
                    cpInfo.referenceIndex = buffer.readShort()
                }

                ConstantPool.CONSTANT_Dynamic.tag -> {
                    cpInfo = CpInfoDynamic()
                    cpInfo.bootstrapMethodAttrIndex = buffer.readShort()
                    cpInfo.nameAndTypeIndex = buffer.readShort()
                }

                ConstantPool.CONSTANT_InvokeDynamic.tag -> {
                    cpInfo = CpInfoInvokeDynamic()
                    cpInfo.bootstrapMethodAttrIndex = buffer.readShort()
                    cpInfo.nameAndTypeIndex = buffer.readShort()
                }
            }
            constant_pools[it] = cpInfo
            it++
        }

        for (it in constant_pools) {
            it?.let {
                it.evaluate(constant_pools)
            }
        }

        for (it in constant_pools.indices) {
            it?.let {
                println("#$it ${constant_pools[it]}")
            }
        }

        access_flags = buffer.readShort()
        println("access_flags : ${AccessPropertyFlags.getAccess(access_flags)}")
        this_class = buffer.readShort()
        println("this_class : #${this_class} ${constant_pools[this_class.toInt()]}")
        super_class = buffer.readShort()
        println("super_class : #${super_class} ${constant_pools[super_class.toInt()]}")

        interfaces_count = buffer.readShort()
        println("interfaces_count = $interfaces_count")
        interfaces = arrayOfNulls(interfaces_count.toInt())
        for (it in 0 until interfaces_count) {
            val index = buffer.readShort()
            interfaces[it] = Interface(index,constant_pools)
        }
        for (it in interfaces.indices){
            it?.let {
                println("#$it ${interfaces[it]}" )
            }
        }

        fields_count = buffer.readShort()
        println("fields_count = $fields_count")
        fields = arrayOfNulls(fields_count.toInt())
        for (it in 0 until fields_count) {
            val accessFlags = buffer.readShort()
            val nameIndex = buffer.readShort()
            val descriptorIndex = buffer.readShort()
            val attributesCount = buffer.readShort()
            val fieldInfo = FieldInfo(accessFlags, nameIndex, descriptorIndex, attributesCount, buffer, constant_pools)
            fields[it] = fieldInfo
        }
        for (it in fields.indices){
            it?.let {
                println("#$it ${fields[it]}" )
            }
        }


        methods_count = buffer.readShort()
        println("methods_count = $methods_count")
        methods = arrayOfNulls(methods_count.toInt())
        for (it in 0 until methods_count) {
            val accessFlags = buffer.readShort()
            val nameIndex = buffer.readShort()
            val descriptorIndex = buffer.readShort()
            val attributesCount = buffer.readShort()
            val methodInfo =
                MethodInfo(accessFlags, nameIndex, descriptorIndex, attributesCount, buffer, constant_pools)
            methods[it] = methodInfo
        }

        for (it in methods.indices){
            it?.let {
                println("#$it ${methods[it]}" )
            }
        }

        attributes_count = buffer.readShort()
        println("attributes_count = $attributes_count")
        attributes = AttributeInfo.readAttributeInfo(attributes_count, constant_pools, buffer)
        for (it in attributes.indices){
            it?.let {
                println("#$it ${attributes[it]}" )
            }
        }

    }
}