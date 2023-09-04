package clz.attributes

import clz.constantpool.CpInfo
import clz.printToString
import clz.u2
import clz.u2Array
import okio.BufferedSource

class BootstrapMethodsAttribute(val data: Array<BootstrapMethod?>, constant_pools: Array<CpInfo?>) :
    AttributeInfo(AttributeType.BootstrapMethods, constant_pools) {
    class BootstrapMethod(
        val bootstrap_method_ref: u2,
        val bootstrap_arguments: u2Array,
        val constant_pools: Array<CpInfo?>

    ) {
        override fun toString(): String {
            return "\n      BootstrapMethod(bootstrap_method_ref=#$bootstrap_method_ref = ${constant_pools[bootstrap_method_ref.toInt()]} \n" +
                    "       bootstrap_arguments=${
                        bootstrap_arguments.printToString(
                            "bootstrap_argument",
                            constant_pools
                        )
                    })"
        }
    }

    override fun toString(): String {
        return "BootstrapMethodsAttribute(data=${data.printToString()})"
    }


    companion object : ReadAttributeAdapter {
        override fun read(source: BufferedSource, constant_pools: Array<CpInfo?>): AttributeInfo {
            val num_bootstrap_methods = source.readShort()
            val bootstrap_methods =
                arrayOfNulls<BootstrapMethod>(num_bootstrap_methods.toInt())
            for (it in 0 until num_bootstrap_methods) {
                val bootstrap_method_ref = source.readShort()
                val num_bootstrap_arguments = source.readShort()
                val bootstrap_arguments = u2Array(num_bootstrap_arguments.toInt())
                for (index in 0 until num_bootstrap_arguments) {
                    bootstrap_arguments[index] = source.readShort()
                }
                val bootstrap_method = BootstrapMethod(
                    bootstrap_method_ref,
                    bootstrap_arguments,
                    constant_pools
                )
                bootstrap_methods[it] = bootstrap_method
            }
            return BootstrapMethodsAttribute(bootstrap_methods, constant_pools)
        }
    }
}