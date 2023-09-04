package clz.attributes.frame

import clz.arrayToStr
import clz.u1

/*
*  The frame type same_locals_1_stack_item_frame is represented by tags in the range [64, 127].
*  This frame type indicates that the frame has exactly the same local variables as the previous frame
*  and that the operand stack has one entry.
*  The offset_delta value for the frame is given by the formula frame_type - 64.
*  The verification type of the one stack entry appears after the frame type.
*
* */
class SameLocals_1_StackItemFrame(override val frame_type: UByte) :
    StackMapFrame(StackMapFrameEnum.same_locals_1_stack_item_frame) {
    lateinit var stack: Array<VerificationTypeInfo?>

    override fun toString(): String {
        return "SameLocals_1_StackItemFrame(frame_type $frame_type   offset_delta $offset_delta " +
                "stack ${arrayToStr(stack, "stack")})"
    }
}