package clz.constantpool

class CpInfoInteger : CpInfo(ConstantPool.CONSTANT_Integer) {
     var data = 0
     override fun toString(): String {
          return "CpInfoInteger(data = $data)"
     }
     override fun getData(): Any {
          return data
     }
}