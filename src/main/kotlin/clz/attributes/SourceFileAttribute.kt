package clz.attributes

import clz.constantpool.CpInfo
import clz.u2

class SourceFileAttribute (val sourceFileIndex: u2,constant_pools: Array<CpInfo?>) : AttributeInfo(AttributeType.SourceFile,constant_pools){
    override fun toString(): String {
        return "SourceFileAttribute(sourceFileIndex=${constant_pools[sourceFileIndex.toInt()]})"
    }
}

