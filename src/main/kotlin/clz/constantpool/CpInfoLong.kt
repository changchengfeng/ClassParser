package clz.constantpool

class CpInfoLong : CpInfo(ConstantPool.CONSTANT_Long) {
     var data = 0L

     override fun toString(): String {
          return "CpInfoLong(data = $data)"
     }

     override fun getData(): Any {
          return data
     }
}