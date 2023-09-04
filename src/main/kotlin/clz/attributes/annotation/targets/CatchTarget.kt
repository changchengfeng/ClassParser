package clz.attributes.annotation.targets

import clz.u2

class CatchTarget(val exception_table_index: u2) : TargetInfo(EnumTargetInfo.type_parameter_target) {
}