package clz.attributes.annotation.targets

import clz.u1
import clz.u2

class TypeArgumentTarget(val offset: u2, val type_argument_index: u1) :
    TargetInfo(EnumTargetInfo.type_parameter_target) {
}