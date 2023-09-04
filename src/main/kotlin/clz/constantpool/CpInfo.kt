package clz.constantpool


abstract class CpInfo(val tag: ConstantPool){
    open fun evaluate(constantPools: Array<CpInfo?>){

    }

    open fun getData() : Any{
        return Any()
    }
}