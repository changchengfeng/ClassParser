package clz.attributes.annotation.targets

import clz.u1

enum class EnumTargetInfo {
    type_parameter_target,
    supertype_target,
    type_parameter_bound_target,
    empty_target,
    method_formal_parameter_target,
    throws_target,
    localvar_target,
    catch_target,
    offset_target,
    type_argument_target;

    companion object {
        fun getTargetInfoByValue(value: u1): EnumTargetInfo {
            when (value.toInt()) {
                0x00,0x01->{
                    return type_parameter_target
                }
                0x10->{
                    return supertype_target
                }
                0x11,0x12->{
                    return type_parameter_bound_target
                }
                0x13,0x14,0x15->{
                    return empty_target
                }
                0x16->{
                    return method_formal_parameter_target
                }
                0x17->{
                    return throws_target
                }
                0x40,0x41->{
                    return localvar_target
                }
                0x42->{
                    return catch_target
                }
                in 0x43..0x46 ->{
                    return offset_target
                }
                in 0x47..0x4B ->{
                    return type_argument_target
                }


            }
            throw IllegalArgumentException("getTargetInfoByValue with error tag $value")
        }
    }
}