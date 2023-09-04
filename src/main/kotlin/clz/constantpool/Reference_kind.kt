package clz.constantpool

import clz.u1

enum class Reference_kind(val kind: u1) {
    REF_getField(1),
    REF_getStatic(2),
    REF_putField(3),
    REF_putStatic(4),
    REF_invokeVirtual(5),
    REF_invokeStatic(6),
    REF_invokeSpecial(7),
    REF_newInvokeSpecial(8),
    REF_invokeInterface(9);

    companion object {
        fun valueOf(value: u1) = values().find { it.kind == value }!!

    }
}