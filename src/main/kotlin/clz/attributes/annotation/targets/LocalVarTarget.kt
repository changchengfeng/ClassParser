package clz.attributes.annotation.targets

import clz.arrayToStr
import clz.u2

class LocalVarTarget(val localVars: Array<Table?>) : TargetInfo(EnumTargetInfo.localvar_target) {
    class Table(val start_pc: u2, val length: u2, val index: u2) {
        override fun toString(): String {
            return """
                Table(
                    start_pc = $start_pc
                    length = $length
                    index = $index
                )
            """.trimIndent()
        }
    }

    override fun toString(): String {
        return """
            LocalVarTarget(
                localVars = 
                        ${arrayToStr(localVars, "localVars")}
            )
        """.trimIndent()
    }
}