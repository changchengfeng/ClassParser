package clz.attributes.frame

import clz.u1

enum class VerificationTypeInfoEnum(val tag: u1) {
    Top_variable_info(0),
    Integer_variable_info(1),
    Float_variable_info(2),
    Long_variable_info(4),
    Double_variable_info(3),
    Null_variable_info(5),
    UninitializedThis_variable_info(6),
    Object_variable_info(7),
    Uninitialized_variable_info(8);

    companion object {
        fun getByTag(tag: u1) = values().find { it.tag == tag }
    }
}