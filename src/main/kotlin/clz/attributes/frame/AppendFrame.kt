package clz.attributes.frame

import clz.arrayToStr
import clz.u1

/*
* The frame type append_frame is represented by tags in the range [252-254].
* This frame type indicates that the frame has the same locals as the previous frame
*  except that k additional locals are defined, and that the operand stack is empty.
*  The value of k is given by the formula frame_type - 251.
*  The offset_delta value for the frame is given explicitly.
* */
class AppendFrame(override val frame_type: UByte) : StackMapFrame(StackMapFrameEnum.append_frame) {
    var k: u1 = 0
    lateinit var locals: Array<VerificationTypeInfo?> // locals[frame_type - 251];

    override fun toString(): String {
        return "AppendFrame(frame_type $frame_type  k $k  offset_delta $offset_delta " +
                " locals ${arrayToStr(locals, "locals")})"
    }
}