package clz.attributes.frame

import clz.u1

/*
*  The frame type chop_frame is represented by tags in the range [248-250].
*  This frame type indicates that the frame has the same local variables as the previous frame
*  except that the last k local variables are absent,
*  and that the operand stack is empty.
*  The value of k is given by the formula 251 - frame_type.
*  The offset_delta value for the frame is given explicitly.
*
* */
class ChopFrame(override val frame_type: UByte) : StackMapFrame(StackMapFrameEnum.chop_frame) {
    var k : u1 = 0

    override fun toString(): String {
        return "ChopFrame(frame_type $frame_type k $k  offset_delta $offset_delta)"
    }
}