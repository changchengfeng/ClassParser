package clz


enum class AccessPropertyFlags(val value: Int) {
    ACC_PUBLIC(0x0001),
    ACC_PRIVATE(0x0002),
    ACC_PROTECTED(0x0004),
    ACC_STATIC(0x0008),
    ACC_FINAL(0x0010),
    ACC_SUPER_ACC_SYNCHRONIZED(0x0020),
    ACC_BRIDGE_ACC_VOLATILE(0x0040),
    ACC_VARARGS_ACC_TRANSIENT(0x0080),
    ACC_NATIVE(0x0100),
    ACC_INTERFACE(0x0200),
    ACC_ABSTRACT(0x0400),
    ACC_STRICT(0x0800),
    ACC_SYNTHETIC(0x1000),
    ACC_ANNOTATION(0x2000),
    ACC_ENUM(0x4000),
    ACC_MANDATED(0x8000);

    companion object{
        @JvmStatic
        fun getAccess(accessFlags: u2): String {
            val stringBuilder = StringBuilder()
            for(it in values()){
                if ((it.value and accessFlags.toInt() )!= 0){
                    stringBuilder.append(it.name).append(" ")
                }
            }
            return stringBuilder.toString()
        }
    }


}