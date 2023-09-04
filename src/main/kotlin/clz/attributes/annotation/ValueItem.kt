package clz.attributes.annotation

import clz.u1

enum class ValueItem {
    const_value_index,
    enum_const_value,
    class_info_index,
    annotation_value,
    array_value;

    companion object {
        fun getTagItemByValue(value: u1): ValueItem {
            when (value.toInt().toChar()) {
                TagItem.B.char,
                TagItem.C.char,
                TagItem.D.char,
                TagItem.F.char,
                TagItem.I.char,
                TagItem.J.char,
                TagItem.S.char,
                TagItem.Z.char,
                TagItem.s.char,
                -> {
                    return const_value_index
                }
                TagItem.e.char -> {
                    return enum_const_value
                }
                TagItem.c.char -> {
                    return class_info_index
                }
                TagItem.at.char -> {
                    return annotation_value
                }
                TagItem.A.char -> {
                    return array_value
                }

            }
            throw IllegalArgumentException("getTagItemByValue with error tag $value")
        }
    }
}