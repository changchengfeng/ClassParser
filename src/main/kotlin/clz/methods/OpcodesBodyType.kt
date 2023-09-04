package clz.methods

enum class OpcodesBodyType(val value: UByte) {
    T_BOOLEAN(4U),
    T_CHAR(5U),
    T_FLOAT(6U),
    T_DOUBLE(7U),
    T_BYTE(8U),
    T_SHORT(9U),
    T_INT(10U),
    T_LONG(11U);

    companion object {
        fun valueOf(value: UByte)=  values().find { it.value == value }!!

    }
}