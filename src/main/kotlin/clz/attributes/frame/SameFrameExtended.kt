package clz.attributes.frame

import clz.u1

/*
* The frame type same_frame_extended is represented by the tag 251.
* This frame type indicates that the frame has exactly the same local variables as the previous frame
*  and that the operand stack is empty.
*  The offset_delta value for the frame is given explicitly, unlike in the frame type same_frame.
*
* */
class SameFrameExtended(override val frame_type: UByte) : StackMapFrame(StackMapFrameEnum.same_frame_extended) {
    override fun toString(): String {
        return "SameFrameExtended(frame_type $frame_type  offset_delta $offset_delta)"
    }
}