package clz

enum class ArrayType(val value: Int) {
    no_body(0),
    index_body(1),
    index_const_body(2),
    sipush_body(3),
    bipush_body(4),
    newarray_body(5),
    indexbyte_1_2_body(6),
    branchbyte1_2_body(7),
    branchbyte1_4_body(8),
    invokeinterface_body(9),
    invokedynamic_body(10),
    multianewarray_body(11),
    wide_body(12),
    tableswitch_body(13),
    lookupswitch_body(14),
    index_v2_body(15)
}