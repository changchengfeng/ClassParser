package clz.methods


val VALUE_1_BYTE = arrayOf(Opcodes.Type.VALUE_1)
val VALUE_2_BYTE = arrayOf(Opcodes.Type.VALUE_2)
val CONSTANT_POOLS_2_VALUE_1_BYTE = arrayOf(Opcodes.Type.CONSTANT_POOLS_2, Opcodes.Type.VALUE_1)
val VALUE_4_BYTE = arrayOf(Opcodes.Type.VALUE_4)
val CONSTANT_POOLS_1_BYTE = arrayOf(Opcodes.Type.CONSTANT_POOLS_1)
val CONSTANT_POOLS_2_BYTE = arrayOf(Opcodes.Type.CONSTANT_POOLS_2)
val ARRAY_2_CONSTANT_POOLS_2 = arrayOf(Opcodes.Type.CONSTANT_POOLS_2, Opcodes.Type.CONSTANT_POOLS_2)

/*
*  有两个位置
* 一个是操作数栈 栈顶
* 本地变量表 index
* */
enum class Opcodes(val value: UByte, val type: Array<Type> = arrayOf()/* 默认参数个数为 0 */) {

    /*
    *   Constans
    *
    *  把常量推到栈顶
    * */
    nop(0x00u), // do nothing
    aconst_null(0x01u), // Push the null object reference onto the operand stack.

    /*Push the int constant <i> (-1, 0, 1, 2, 3, 4 or 5) onto the operand stack.*/
    iconst_m1(0x02u),
    iconst_0(0x03u),
    iconst_1(0x04u),
    iconst_2(0x05u),
    iconst_3(0x06u),
    iconst_4(0x07u),
    iconst_5(0x08u),

    // Push the long constant <l> (0 or 1) onto the operand stack.
    lconst_0(0x09u),
    lconst_1(0x0au),

    // Push the float constant <f> (0.0, 1.0, or 2.0) onto the operand stack.
    fconst_0(0x0bu),
    fconst_1(0x0cu),
    fconst_2(0x0du),

    //Push the double constant <d> (0.0 or 1.0) onto the operand stack.
    dconst_0(0x0eu),
    dconst_1(0x0fu),

    // The immediate byte is sign-extended to an int value. That value is pushed onto the operand stack.
    bipush(0x10u, VALUE_1_BYTE),

    /* The immediate unsigned byte1 and byte2 values are assembled into an intermediate short,
     * where the value of the short is (byte1 << 8) | byte2. The intermediate value is then sign-extended to an int value.
     * That value is pushed onto the operand stack. */
    sipush(0x11u, VALUE_2_BYTE),

    /*
    * The index is an unsigned byte that must be a valid index into the run-time constant pool of the current class (§2.6).
    *  The run-time constant pool entry at index either must be a run-time constant of type
    *   int or float, or a reference to a string literal, or a symbolic reference to a class, method type, or method handle
    * */
    ldc(0x12u, CONSTANT_POOLS_1_BYTE),

    /*
    * The unsigned indexbyte1 and indexbyte2 are assembled into an unsigned 16-bit index into the run-time constant pool of the current class (§2.6),
    * where the value of the index is calculated as (indexbyte1 << 8) | indexbyte2. The index must be a valid index into the run-time constant pool of the current class.
    * The run-time constant pool entry at the index either must be a run-time constant of type int or float, or a reference to a string literal, or a symbolic reference to a class, method type, or method handle
    * */
    ldc_w(0x13u, CONSTANT_POOLS_2_BYTE),

    /*
    * The unsigned indexbyte1 and indexbyte2 are assembled into an unsigned 16-bit index into the run-time constant pool of the current class (§2.6),
    *  where the value of the index is calculated as (indexbyte1 << 8) | indexbyte2. The index must be a valid index into the run-time constant pool of the current class.
    *  The run-time constant pool entry at the index must be a run-time constant of type long or double (§5.1). The numeric value of that run-time constant is pushed onto the operand stack as a long or double, respectively.
    * */
    ldc2_w(0x14u, CONSTANT_POOLS_2_BYTE),

    /*
    *   Loads
    *
    *  读取本地变量表的值 ,并推到栈顶
    * */

    /*
    * The index is an unsigned byte that must be an index into the local variable array of the current frame (§2.6).
    *  The local variable at index must contain an int. The value of the local variable at index is pushed onto the operand stack
    * */
    iload(0x15u, VALUE_1_BYTE),
    lload(0x16u, VALUE_1_BYTE),
    fload(0x17u, VALUE_1_BYTE),
    dload(0x18u, VALUE_1_BYTE),
    aload(0x19u, VALUE_1_BYTE),

    /*
    * The <n> must be an index into the local variable array of the current frame (§2.6).
    * The local variable at <n> must contain an int. The value of the local variable at <n> is pushed onto the operand stack.
    * */
    iload_0(0x1au),
    iload_1(0x1bu),
    iload_2(0x1cu),
    iload_3(0x1du),

    /*
    * Both <n> and <n>+1 must be indices into the local variable array of the current frame (§2.6).
    * The local variable at <n> must contain a long. The value of the local variable at <n> is pushed onto the operand stack.
    * */
    lload_0(0x1eu),
    lload_1(0x1fu),
    lload_2(0x20u),
    lload_3(0x21u),

    /*
    * The <n> must be an index into the local variable array of the current frame (§2.6).
    *  The local variable at <n> must contain a float. The value of the local variable at <n> is pushed onto the operand stack.
    * */
    fload_0(0x22u),
    fload_1(0x23u),
    fload_2(0x24u),
    fload_3(0x25u),

    /*
    * Both <n> and <n>+1 must be indices into the local variable array of the current frame (§2.6).
    * The local variable at <n> must contain a double. The value of the local variable at <n> is pushed onto the operand stack.
    * */
    dload_0(0x26u),
    dload_1(0x27u),
    dload_2(0x28u),
    dload_3(0x29u),

    /*
    * The <n> must be an index into the local variable array of the current frame (§2.6).
    * The local variable at <n> must contain a reference. The objectref in the local variable at <n> is pushed onto the operand stack.
    * */
    aload_0(0x2au),
    aload_1(0x2bu),
    aload_2(0x2cu),
    aload_3(0x2du),

    /*
    * 从数组中读取数据并推到栈顶
    *
    *
     Operand Stack
..., arrayref, index →
..., value
    * */

    /*
    * The arrayref must be of type reference and must refer to an array whose components are of type int.
    * The index must be of type int. Both arrayref and index are popped from the operand stack.
    * The int/long/float/double/reference/boolean/char/short value in the component of the array at index is retrieved and pushed onto the operand stack.
    * */
    iaload(0x2eu),
    laload(0x2fu),
    faload(0x30u),
    daload(0x31u),
    aaload(0x32u), // Load reference from array
    baload(0x33u),
    caload(0x34u),
    saload(0x35u),

    /*
    * Stores
    *
    * 把栈顶数据存储到本地变量表中
    * */


    /*
    Operand Stack
..., value →
...
    * */

    istore(0x36u, VALUE_1_BYTE),
    lstore(0x37u, VALUE_1_BYTE),
    fstore(0x38u, VALUE_1_BYTE),
    dstore(0x39u, VALUE_1_BYTE),

    /*
    * The index is an unsigned byte that must be an index into the local variable array of the current frame.
    * The objectref on the top of the operand stack must be of type returnAddress or of type reference. It is popped from the operand stack, and the value of the local variable at index is set to objectref.
    * */
    astore(0x3au, VALUE_1_BYTE), // Store reference into local variable


    /*
    * The <n> must be an index into the local variable array of the current frame (§2.6).
    * The value on the top of the operand stack must be of type int. It is popped from the operand stack,
    * and the value of the local variable at <n> is set to value.
    * */
    istore_0(0x3bu),
    istore_1(0x3cu),
    istore_2(0x3du),
    istore_3(0x3eu),

    /*
    * Both <n> and <n>+1 must be indices into the local variable array of the current frame (§2.6).
    * The value on the top of the operand stack must be of type long. It is popped from the operand stack,
    *  and the local variables at <n> and <n>+1 are set to value.
    * */
    lstore_0(0x3fu),
    lstore_1(0x40u),
    lstore_2(0x41u),
    lstore_3(0x42u),

    /*
    * The <n> must be an index into the local variable array of the current frame (§2.6).
    * The value on the top of the operand stack must be of type float.
    * It is popped from the operand stack and undergoes value set conversion (§2.8.3), resulting in value'. The value of the local variable at <n> is set to value'.
    * */
    fstore_0(0x43u),
    fstore_1(0x44u),
    fstore_2(0x45u),
    fstore_3(0x46u),

    /*
    * Both <n> and <n>+1 must be indices into the local variable array of the current frame (§2.6).
    * The value on the top of the operand stack must be of type double.
    * It is popped from the operand stack and undergoes value set conversion (§2.8.3), resulting in value'.
    * The local variables at <n> and <n>+1 are set to value'.
    * */
    dstore_0(0x47u),
    dstore_1(0x48u),
    dstore_2(0x49u),
    dstore_3(0x4au),

    /*
    * The <n> must be an index into the local variable array of the current frame (§2.6).
    * The objectref on the top of the operand stack must be of type returnAddress or of type reference.
    * It is popped from the operand stack, and the value of the local variable at <n> is set to objectref.
    *
    *
    An astore_<n> instruction is used with an objectref of type returnAddress when implementing the finally clauses of the Java programming language (§3.13).
    An aload_<n> instruction (§aload_<n>) cannot be used to load a value of type returnAddress from a local variable onto the operand stack.
    This asymmetry with the corresponding astore_<n> instruction is intentional.
    Each of the astore_<n> instructions is the same as astore with an index of <n>, except that the operand <n> is implicit.
    * */
    astore_0(0x4bu),
    astore_1(0x4cu),
    astore_2(0x4du),
    astore_3(0x4eu),

    /*
     从栈顶中取值，把栈顶数据 value 存储到 数组对象 arrayref 索引为 index 中
    *
     Operand Stack
..., arrayref, index, value →
...
    * */

    /*
    * int/long/float/double/reference/boolean/char/short
    * */
    iastore(0x4fu),
    lastore(0x50u),
    fastore(0x51u),
    dastore(0x52u),
    aastore(0x53u),
    bastore(0x54u),
    castore(0x55u),
    sastore(0x56u),

    /*
    *  Stack
    *
    * 处理操作数栈
    * */


    /*
     Operand Stack
..., value →
...
Pop the top value from the operand stack.
    * */
    pop(0x57u),

    /*
    Operand Stack
Form 1:
..., value2, value1 →
...
where each of value1 and value2 is a value of a category 1 computational type (§2.11.1).
Form 2:
..., value →
...
where value is a value of a category 2 computational type (§2.11.1).
    *
Pop the top one or two values from the operand stack.
    * */
    pop2(0x58u),


    /*
     Operand Stack
..., value →
..., value, value

    Duplicate the top value on the operand stack and push the duplicated value onto the operand stack.
    * */
    dup(0x59u),

    /*
    *
Operand Stack
..., value2, value1 →
..., value1, value2, value1
*
* Duplicate the top value on the operand stack and insert the duplicated value two values down in the operand stack.
    * */
    dup_x1(0x5au),

    /*
    *
Operand Stack
Form 1:
..., value3, value2, value1 →
..., value1, value3, value2, value1
Form 2:
..., value2, value1 →
..., value1, value2, value1
*
* Duplicate the top value on the operand stack and insert the duplicated value two or three values down in the operand stack.
    * */
    dup_x2(0x5bu),

    /*
Operand Stack
Form 1:
..., value2, value1 →
..., value2, value1, value2, value1
Form 2:
..., value →
..., value, value

Duplicate the top one or two values on the operand stack and push the duplicated value or values back onto the operand stack in the original order.
    * */
    dup2(0x5cu),

    /*
    Operand Stack
Form 1:
..., value3, value2, value1 →
..., value2, value1, value3, value2, value1
Form 2:
..., value2, value1 →
..., value1, value2, value1

Duplicate the top one or two values on the operand stack and insert the duplicated values, in the original order, one value beneath the original value or values in the operand stack.
    * */
    dup2_x1(0x5du),

    /*
    Operand Stack
Form 1:
..., value4, value3, value2, value1 →
..., value2, value1, value4, value3, value2, value1
Form 2:
..., value3, value2, value1 →
..., value1, value3, value2, value1
Form 3:
..., value3, value2, value1 →
..., value2, value1, value3, value2, value1
Form 4:
..., value2, value1 →
..., value1, value2, value1

Duplicate the top one or two values on the operand stack and insert the duplicated values, in the original order, into the operand stack.

    * */
    dup2_x2(0x5eu),

    /*
    *Operand Stack
..., value2, value1 →
..., value1, value2
*
Swap the top two values on the operand stack.
    * */
    swap(0x5fu),

    /*
*    *  Math
*   */

    /*
     Operand Stack
..., value1, value2 →
..., result


Both value1 and value2 must be of type int. The values are popped from the operand stack. The int result is value1 + value2.
The result is pushed onto the operand stack.
The result is the 32 low-order bits of the true mathematical result in a sufficiently wide two's-complement format, represented as a value of type int.
If overflow occurs, then the sign of the result may not be the same as the sign of the mathematical sum of the two values.
Despite the fact that overflow may occur, execution of an iadd instruction never throws a run-time exception.
    * */
    iadd(0x60u),
    ladd(0x61u),
    fadd(0x62u),
    dadd(0x63u),

    // - 减
    isub(0x64u),
    lsub(0x65u),
    fsub(0x66u),
    dsub(0x67u),

    // * 乘
    imul(0x68u),
    lmul(0x69u),
    fmul(0x6au),
    dmul(0x6bu),

    // / 除
    idiv(0x6cu),
    ldiv(0x6du),
    fdiv(0x6eu),
    ddiv(0x6fu),

    // % 求余
    irem(0x70u),
    lrem(0x71u),
    frem(0x72u),
    drem(0x73u),

    /*
    Operand Stack
..., value →
..., result

    - 取负
    * */
    ineg(0x74u),
    lneg(0x75u),
    fneg(0x76u),
    dneg(0x77u),

    /*
    Operand Stack
..., value1, value2 →
..., result
    * */
    // 左移
    ishl(0x78u),
    lshl(0x79u),

    // 右移
    ishr(0x7au),
    lshr(0x7bu),

    // 无符号右移
    iushr(0x7cu),
    lushr(0x7du),

    // 与
    iand(0x7eu),
    land(0x7fu),

    // 或
    ior(0x80u),
    lor(0x81u),

    //异或
    ixor(0x82u),
    lxor(0x83u),

    /*
Format
    iinc
        index
        const
Operand Stack
No change
    * The index is an unsigned byte that must be an index into the local variable array of the current frame (§2.6).
     The const is an immediate signed byte. The local variable at index must contain an int.
     The value const is first sign-extended to an int, and then the local variable at index is incremented by that amount.
    * */
    iinc(0x84u, arrayOf(Type.VALUE_1, Type.VALUE_1)),

    /*
*    Conversions
*   强转
*   */

    /*
    *
   Operand Stack
..., value →
..., result
    * */
    i2l(0x85u),
    i2f(0x86u),
    i2d(0x87u),

    l2i(0x88u),
    l2f(0x89u),
    l2d(0x8au),

    f2i(0x8bu),
    f2l(0x8cu),
    f2d(0x8du),

    d2i(0x8eu),
    d2l(0x8fu),
    d2f(0x90u),

    i2b(0x91u),
    i2c(0x92u),
    i2s(0x93u),

    /*
    * Comparisons
    * */

    /*
    *
     Operand Stack
..., value1, value2 →
..., result
    *
    * oth value1 and value2 must be of type long. They are both popped from the operand stack, and a signed integer comparison is performed.
    * If value1 is greater than value2, the int value 1 is pushed onto the operand stack.
    * If value1 is equal to value2, the int value 0 is pushed onto the operand stack.
    * If value1 is less than value2, the int value -1 is pushed onto the operand stack
    * */
    lcmp(0x94u),

    /*
    Operand Stack
..., value1, value2 →
..., result

Both value1 and value2 must be of type float. The values are popped from the operand stack and undergo value set conversion (§2.8.3), resulting in value1' and value2'. A floating-point comparison is performed:
    - If value1' is greater than value2', the int value 1 is pushed onto the operand stack.
    - Otherwise, if value1' is equal to value2', the int value 0 is pushed onto the operand stack.
    - Otherwise, if value1' is less than value2', the int value -1 is pushed onto the operand stack.
    -Otherwise, at least one of value1' or value2' is NaN.
      The fcmpg instruction pushes the int value 1 onto the operand stack and the fcmpl instruction pushes the int value -1 onto the operand stack.

Floating-point comparison is performed in accordance with IEEE 754. All values other than NaN are ordered, with negative infinity less than all finite values and positive infinity greater than all finite values. Positive zero and negative zero are considered equal.

    * The fcmpg and fcmpl instructions differ only in their treatment of a comparison involving NaN.
    * NaN is unordered, so any float comparison fails if either or both of its operands are NaN.
    * With both fcmpg and fcmpl available, any float comparison may be compiled to push the same result onto the operand stack whether the comparison fails on non-NaN values or fails because it encountered a NaN. For more information, see §3.5.
    * */
    fcmpl(0x95u),
    fcmpg(0x96u),
    dcmpl(0x97u),
    dcmpg(0x98u),

    /*
    Format
if<cond>
branchbyte1
branchbyte2

Operand Stack
..., value →
...

The value must be of type int. It is popped from the operand stack and compared against zero. All comparisons are signed. The results of the comparisons are as follows:
    - ifeq succeeds if and only if value = 0
    - ifne succeeds if and only if value ≠ 0
    - iflt succeeds if and only if value < 0
    - ifle succeeds if and only if value ≤ 0
    - ifgt succeeds if and only if value > 0
    - ifge succeeds if and only if value ≥ 0

If the comparison succeeds, the unsigned branchbyte1 and branchbyte2 are used to construct a signed 16-bit offset,
where the offset is calculated to be (branchbyte1 << 8) | branchbyte2. Execution then proceeds at that offset from the address of the opcode of this if<cond> instruction.
The target address must be that of an opcode of an instruction within the method that contains this if<cond> instruction.
Otherwise, execution proceeds at the address of the instruction following this if<cond> instruction.
    * */
    ifeq(0x99u, VALUE_2_BYTE),
    ifne(0x9au, VALUE_2_BYTE),
    iflt(0x9bu, VALUE_2_BYTE),
    ifge(0x9cu, VALUE_2_BYTE),
    ifgt(0x9du, VALUE_2_BYTE),
    ifle(0x9eu, VALUE_2_BYTE),

    /*
Format
if_icmp<cond>
branchbyte1
branchbyte2

Operand Stack
..., value1, value2 →
...

Both value1 and value2 must be of type int. They are both popped from the operand stack and compared. All comparisons are signed. The results of the comparison are as follows:
    - if_icmpeq succeeds if and only if value1 = value2
    - if_icmpne succeeds if and only if value1 ≠ value2
    - if_icmplt succeeds if and only if value1 < value2
    - if_icmple succeeds if and only if value1 ≤ value2
    - if_icmpgt succeeds if and only if value1 > value2
    - if_icmpge succeeds if and only if value1 ≥ value2

If the comparison succeeds, the unsigned branchbyte1 and branchbyte2 are used to construct a signed 16-bit offset, where the offset is calculated to be (branchbyte1 << 8) | branchbyte2.
Execution then proceeds at that offset from the address of the opcode of this if_icmp<cond> instruction. The target address must be that of an opcode of an instruction within the method that contains this if_icmp<cond> instruction.
Otherwise, execution proceeds at the address of the instruction following this if_icmp<cond> instruction.
    * */
    if_icmpeq(0x9fu, VALUE_2_BYTE),
    if_icmpne(0xa0u, VALUE_2_BYTE),
    if_icmplt(0xa1u, VALUE_2_BYTE),
    if_icmpge(0xa2u, VALUE_2_BYTE),
    if_icmpgt(0xa3u, VALUE_2_BYTE),
    if_icmple(0xa4u, VALUE_2_BYTE),
    if_acmpeq(0xa5u, VALUE_2_BYTE),
    if_acmpne(0xa6u, VALUE_2_BYTE),

    /*
    * Control
    * */

    /*
Format
goto
branchbyte1
branchbyte2

Operand Stack
No change

     The unsigned bytes branchbyte1 and branchbyte2 are used to construct a signed 16-bit branchoffset,
     where branchoffset is (branchbyte1 << 8) | branchbyte2. Execution proceeds at that offset from the address of the opcode of this goto instruction.
     The target address must be that of an opcode of an instruction within the method that contains this goto instruction.
    * */
    goto(0xa7u, VALUE_2_BYTE),

    /*
Format
    jsr
    branchbyte1
    branchbyte2
Operand Stack
... →
..., address

Description
    The address of the opcode of the instruction immediately following this jsr instruction is pushed onto the operand stack as a value of type returnAddress.
     The unsigned branchbyte1 and branchbyte2 are used to construct a signed 16-bit offset, where the offset is (branchbyte1 << 8) | branchbyte2.
     Execution proceeds at that offset from the address of this jsr instruction. The target address must be that of an opcode of an instruction within the method that contains this jsr instruction.
Notes
    Note that jsr pushes the address onto the operand stack and ret (§ret) gets it out of a local variable. This asymmetry is intentional.
    In Oracle's implementation of a compiler for the Java programming language prior to Java SE 6, the jsr instruction was used with the ret instruction in the implementation of the finally clause (§3.13, §4.10.2.5).
    * */
    jsr(0xa8u, VALUE_2_BYTE),

    /*
    *
Format
    ret
    index

Operand Stack
No change

Description
    The index is an unsigned byte between 0 and 255, inclusive. The local variable at index in the current frame (§2.6) must contain a value of type returnAddress.
    The contents of the local variable are written into the Java Virtual Machine's pc register, and execution continues there.
    * */
    ret(0xa9u, VALUE_1_BYTE),

    /*
    * Format

tableswitch
<0-3 byte pad>
defaultbyte1
defaultbyte2
defaultbyte3
defaultbyte4
lowbyte1
lowbyte2
lowbyte3
lowbyte4
highbyte1
highbyte2
highbyte3
highbyte4
jump offsets...


Operand Stack
..., index →
...

Description
    A tableswitch is a variable-length instruction.
    Immediately after the tableswitch opcode,between zero and three bytes must act as padding,  such that defaultbyte1 begins at an address that is a multiple of four bytes from the start of the current method (the opcode of its first instruction).
    Immediately after the padding are bytes constituting three signed 32-bit values: default, low, and high.
    Immediately following are bytes constituting a series of high - low + 1 signed 32-bit offsets. The value low must be less than or equal to high. The high - low + 1 signed 32-bit offsets are treated as a 0-based jump table.

    Each of these signed 32-bit values is constructed as (byte1 << 24) | (byte2 << 16) | (byte3 << 8) | byte4.
    The index must be of type int and is popped from the operand stack. If index is less than low or index is greater than high, then a target address is calculated by adding default to the address of the opcode of this tableswitch instruction.
    Otherwise, the offset at position index - low of the jump table is extracted. The target address is calculated by adding that offset to the address of the opcode of this tableswitch instruction. Execution then continues at the target address.
    The target address that can be calculated from each jump table offset, as well as the one that can be calculated from default, must be the address of an opcode of an instruction within the method that contains this tableswitch instruction.

Notes
    The alignment required of the 4-byte operands of the tableswitch instruction guarantees 4-byte alignment of those operands if and only if the method that contains the tableswitch starts on a 4-byte boundary.
    * */
    tableswitch(0xaau, arrayOf(Type.TABLES_WITCH)),

    /*
    *
Format
lookupswitch
    <0-3 byte pad>
    defaultbyte1
    defaultbyte2
    defaultbyte3
    defaultbyte4
    npairs1
    npairs2
    npairs3
    npairs4
    match-offset pairs...

Operand Stack
..., key →

Description
    A lookupswitch is a variable-length instruction.
   Immediately after the lookupswitch opcode, between zero and three bytes must act as padding,
   such that defaultbyte1 begins at an address that is a multiple of four bytes from the start of the current method (the opcode of its first instruction).
   Immediately after the padding follow a series of signed 32-bit values: default, npairs, and then npairs pairs of signed 32-bit values. The npairs must be greater than or equal to 0. Each of the npairs pairs consists of an int match and a signed 32-bit offset.
   Each of these signed 32-bit values is constructed from four unsigned bytes as (byte1 << 24) | (byte2 << 16) | (byte3 << 8) | byte4.
    The table match-offset pairs of the lookupswitch instruction must be sorted in increasing numerical order by match.
    The key must be of type int and is popped from the operand stack. The key is compared against the match values. If it is equal to one of them, then a target address is calculated by adding the corresponding offset to the address of the opcode of this lookupswitch instruction.
    If the key does not match any of the match values, the target address is calculated by adding default to the address of the opcode of this lookupswitch instruction. Execution then continues at the target address.
    The target address that can be calculated from the offset of each match-offset pair, as well as the one calculated from default, must be the address of an opcode of an instruction within the method that contains this lookupswitch instruction.
Notes
    The alignment required of the 4-byte operands of the lookupswitch instruction guarantees 4-byte alignment of those operands if and only if the method that contains the lookupswitch is positioned on a 4-byte boundary.
    The match-offset pairs are sorted to support lookup routines that are quicker than linear search.
    * */
    lookupswitch(0xabu, arrayOf(Type.LOOKUP_SWITCH)),

    /*
Operand Stack
..., value →
[empty]
Description
    The current method must have return type boolean, byte, short, char, or int. The value must be of type int.
    If the current method is a synchronized method, the monitor entered or reentered on invocation of the method is updated and possibly exited as if by execution of a monitorexit instruction (§monitorexit) in the current thread. If no exception is thrown, value is popped from the operand stack of the current frame (§2.6) and pushed onto the operand stack of the frame of the invoker.
    Any other values on the operand stack of the current method are discarded.
    The interpreter then returns control to the invoker of the method, reinstating the frame of the invoker.
    * */
    ireturn(0xacu),
    lreturn(0xadu),
    freturn(0xaeu),
    dreturn(0xafu),
    areturn(0xb0u),
    Return(0xb1u),

    /*
    * References
    * */
    /*
Format
    getstatic
    indexbyte1
    indexbyte2

Operand Stack
..., →
..., value

    The unsigned indexbyte1 and indexbyte2 are used to construct an index into the run-time constant pool of the current class (§2.6), where the value of the index is (indexbyte1 << 8) | indexbyte2.
    The run-time constant pool item at that index must be a symbolic reference to a field (§5.1), which gives the name and descriptor of the field as well as a symbolic reference to the class or interface in which the field is to be found. The referenced field is resolved (§5.4.3.2).
    On successful resolution of the field, the class or interface that declared the resolved field is initialized (§5.5) if that class or interface has not already been initialized.
    The value of the class or interface field is fetched and pushed onto the operand stack.
    * */
    getstatic(0xb2u, CONSTANT_POOLS_2_BYTE),
    putstatic(0xb3u, CONSTANT_POOLS_2_BYTE),

    getfield(0xb4u, CONSTANT_POOLS_2_BYTE),
    putfield(0xb5u, CONSTANT_POOLS_2_BYTE),

    invokevirtual(0xb6u, CONSTANT_POOLS_2_BYTE),
    invokespecial(0xb7u, CONSTANT_POOLS_2_BYTE),
    invokestatic(0xb8u, CONSTANT_POOLS_2_BYTE),
    invokeinterface(0xb9u, CONSTANT_POOLS_2_BYTE),

    invokedynamic(0xbau, ARRAY_2_CONSTANT_POOLS_2),
    new(0xbbu, CONSTANT_POOLS_2_BYTE),

    /*
Format
newarray
    atype
Operand Stack
..., count →
..., arrayref

Description
    The count must be of type int. It is popped off the operand stack. The count represents the number of elements in the array to be created.
    The atype is a code that indicates the type of array to create. It must take one of the following values:
Table 6.5.newarray-A. Array type codes
Array Type	atype
    T_BOOLEAN	4
    T_CHAR	5
    T_FLOAT	6
    T_DOUBLE	7
    T_BYTE	8
    T_SHORT	9
    T_INT	10
    T_LONG	11

    A new array whose components are of type atype and of length count is allocated from the garbage-collected heap.
     A reference arrayref to this new array object is pushed into the operand stack. Each of the elements of the new array is initialized to the default initial value (§2.3, §2.4) for the element type of the array type.
    * */
    newarray(0xbcu, VALUE_1_BYTE),

    /*
    * Format
        anewarray
        indexbyte1
        indexbyte2

    Operand Stack
    ..., count →
    ..., arrayref

    Description
        The count must be of type int. It is popped off the operand stack. The count represents the number of components of the array to be created.
        The unsigned indexbyte1 and indexbyte2 are used to construct an index into the run-time constant pool of the current class (§2.6), where the value of the index is (indexbyte1 << 8) | indexbyte2. The run-time constant pool item at that index must be a symbolic reference to a class, array, or interface type.
        The named class, array, or interface type is resolved (§5.4.3.1). A new array with components of that type, of length count, is allocated from the garbage-collected heap, and a reference arrayref to this new array object is pushed onto the operand stack. All components of the new array are initialized to null, the default value for reference types (§2.4).
        *
    * */
    anewarray(0xbdu, CONSTANT_POOLS_2_BYTE),

    /*
    Operand Stack
..., arrayref →
..., length

Description
    The arrayref must be of type reference and must refer to an array. It is popped from the operand stack.
     The length of the array it references is determined. That length is pushed onto the operand stack as an int.
    * */
    arraylength(0xbeu),

    /*
 Operand Stack
..., objectref →
objectref

Description
    The objectref must be of type reference and must refer to an object that is an instance of class Throwable or of a subclass of Throwable. It is popped from the operand stack.
    The objectref is then thrown by searching the current method (§2.6) for the first exception handler that matches the class of objectref, as given by the algorithm in §2.10.
    If an exception handler that matches objectref is found, it contains the location of the code intended to handle this exception. The pc register is reset to that location,
    the operand stack of the current frame is cleared,  objectref is pushed back onto the operand stack, and execution continues.

   If no matching exception handler is found in the current frame, that frame is popped. If the current frame represents an invocation of a synchronized method, the monitor entered or reentered on invocation of the method is exited as if by execution of a monitorexit instruction (§monitorexit).
    Finally, the frame of its invoker is reinstated, if such a frame exists, and the objectref is rethrown. If no such frame exists, the current thread exits.
    * */
    athrow(0xbfu),

    /*
Format
    checkcast
    indexbyte1
    indexbyte2

Operand Stack
..., objectref →
..., objectref


    * */
    checkcast(0xc0u, CONSTANT_POOLS_2_BYTE),
    instanceof(0xc1u, CONSTANT_POOLS_2_BYTE),

    /*
Operand Stack
..., objectref →
...

Description
    The objectref must be of type reference.
    Each object is associated with a monitor. A monitor is locked if and only if it has an owner. The thread that executes monitorenter attempts to gain ownership of the monitor associated with objectref, as follows:

    - If the entry count of the monitor associated with objectref is zero, the thread enters the monitor and sets its entry count to one. The thread is then the owner of the monitor.
    - If the thread already owns the monitor associated with objectref, it reenters the monitor, incrementing its entry count.
    - If another thread already owns the monitor associated with objectref, the thread blocks until the monitor's entry count is zero, then tries again to gain ownership.
    * */
    monitorenter(0xc2u),
    monitorexit(0xc3u),

    /*
     *  Extended
     * */

    wide(0xc4u, arrayOf(Type.WIDE)),

    /*
Format

multianewarray
    indexbyte1
    indexbyte2
    dimensions
Operand Stack
..., count1, [count2, ...] →
..., arrayref

    * */
    multianewarray(0xc5u, CONSTANT_POOLS_2_VALUE_1_BYTE),

    ifnull(0xc6u, VALUE_2_BYTE),
    ifnonnull(0xc7u, VALUE_2_BYTE),

    /*
goto_w
branchbyte1
branchbyte2
branchbyte3
branchbyte4

Operand Stack
No change

    The unsigned bytes branchbyte1, branchbyte2, branchbyte3, and branchbyte4 are used to construct a signed 32-bit branchoffset,
    where branchoffset is (branchbyte1 << 24) | (branchbyte2 << 16) | (branchbyte3 << 8) | branchbyte4. Execution proceeds at that offset from the address of the opcode of this goto_w instruction.
    The target address must be that of an opcode of an instruction within the method that contains this goto_w instruction.
    * */
    goto_w(0xc8u, VALUE_4_BYTE),
    jsr_w(0xc9u, VALUE_4_BYTE),

    /*
    *  Reserved
    * */

    breakpoint(0xcau),
    impdep1(0xfeu),
    impdep2(0xffu);

    enum class Type {
        VALUE_1, // 一个字节，数值，无需查找常量池
        VALUE_2, // 两个字节，数值，无需查找常量池
        VALUE_4, // 4 个字节，数值，无需查找常量池
        CONSTANT_POOLS_1,// 一个字节，数值，查找常量池
        CONSTANT_POOLS_2, // 两个字节，数值，需要查找常量池
        TABLES_WITCH, // 变长
        LOOKUP_SWITCH, // 变长
        WIDE
    }

    companion object {
        fun valueOf(value: UByte) = values().find { it.value == value } !!
    }
}