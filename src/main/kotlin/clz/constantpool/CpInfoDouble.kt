package clz.constantpool


class CpInfoDouble : CpInfo(ConstantPool.CONSTANT_Double) {
     var data = 0.0

     override fun toString(): String {
          return "CpInfoDouble(data = $data)"
     }

     override fun getData(): Any {
          return data
     }
}