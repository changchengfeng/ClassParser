package clz.attributes.annotation.path

import clz.u1

enum class TypePathKind(val value: u1) {
    ARRAY_TYPE(0),
    nested_TYPE(1),
    wildcard_parameterized_TYPE(2),
    argument_parameterized_TYPE(3);

    companion object {
        fun getTypePathKindByValue(value: u1): TypePathKind {
            return values().get(value.toInt())
        }
    }
}