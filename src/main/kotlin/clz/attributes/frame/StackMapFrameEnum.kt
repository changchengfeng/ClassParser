package clz.attributes.frame

enum class StackMapFrameEnum {
    same_frame, /* 0-63 */
    same_locals_1_stack_item_frame, /* 64-127 */
    same_locals_1_stack_item_frame_extended, /* 247 */
    chop_frame, /* 248-250 */
    same_frame_extended, /* 251 */
    append_frame, /* 252-254 */
    full_frame  /* 255 */
}