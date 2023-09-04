package clz.attributes.frame

import clz.u1

/*
*  The frame type same_frame is represented by tags in the range [0-63].
*  This frame type indicates that the frame has exactly the same local variables as the previous frame
*  and that the operand stack is empty. The offset_delta value for the frame is the value of the tag item, frame_type.
* */
class SameFrame( override val frame_type: UByte) : StackMapFrame(StackMapFrameEnum.same_frame) {

    override fun toString(): String {
        return "SameFrame(frame_type $frame_type  offset_delta $offset_delta)"
    }
}