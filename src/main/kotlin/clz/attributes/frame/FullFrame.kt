package clz.attributes.frame

import clz.arrayToStr
import clz.u1
import clz.u2

class FullFrame(override val frame_type: UByte) : StackMapFrame(StackMapFrameEnum.full_frame) {
    var number_of_locals: u2 = 0
    lateinit var locals: Array<VerificationTypeInfo?>
    var number_of_stack_items: u2 = 0
    lateinit var stack: Array<VerificationTypeInfo?>

    override fun toString(): String {
        return "FullFrame(frame_type $frame_type  offset_delta $offset_delta number_of_locals $number_of_locals \n" +
                "locals ${arrayToStr(locals, "locals")} \n" +
                "number_of_stack_items ${number_of_stack_items} \n" +
                "stack ${arrayToStr(stack, "stack")} \n)"
    }
}