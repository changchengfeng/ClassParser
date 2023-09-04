package clz.attributes

import clz.constantpool.CpInfo
import okio.ByteString.Companion.toByteString
import clz.u1Array

class SourceDebugExtensionAttribute(val debugExtensions: u1Array,constant_pools: Array<CpInfo?>) : AttributeInfo(AttributeType.SourceFile,constant_pools){
    override fun toString(): String {
        return "SourceDebugExtensionAttribute(debugExtensions=${debugExtensions.toByteString().utf8()})"
    }
}