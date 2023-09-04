package clz.constantpool

class CpInfoFloat : CpInfo(ConstantPool.CONSTANT_Float) {
     var data = 0.0f
     override fun toString(): String {
          return "CpInfoFloat(data = $data)"
     }

     override fun getData(): Any {
          return data
     }
}