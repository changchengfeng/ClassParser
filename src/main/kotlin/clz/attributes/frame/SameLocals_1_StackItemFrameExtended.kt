package clz.attributes.frame

import clz.arrayToStr
import clz.u1

/*
* The frame type same_locals_1_stack_item_frame_extended is represented by the tag 247.
*  This frame type indicates that the frame has exactly the same local variables as the previous frame
*  and that the operand stack has one entry.
*  The offset_delta value for the frame is given explicitly, unlike in the frame type same_locals_1_stack_item_frame.
*  The verification type of the one stack entry appears after offset_delta
* */
class SameLocals_1_StackItemFrameExtended(override val frame_type: UByte) :
    StackMapFrame(StackMapFrameEnum.same_locals_1_stack_item_frame_extended) {
    lateinit var stack: Array<VerificationTypeInfo?>

    override fun toString(): String {
        return "SameLocals_1_StackItemFrameExtended(frame_type $frame_type  offset_delta $offset_delta " +
                "stack ${arrayToStr(stack, "stack")})"
    }
}