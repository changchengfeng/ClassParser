package clz

import clz.attributes.AnnotationAttribute
import clz.constantpool.CpInfo
import okio.BufferedSource
import okio.ByteString
import okio.ByteString.Companion.readByteString
import okio.ByteString.Companion.toByteString
import java.io.ByteArrayInputStream
import java.nio.ByteOrder

typealias u2 = Short
typealias u1 = Byte
typealias u4 = Int
typealias u1Array = ByteArray
typealias u2Array = ShortArray
typealias RA = AnnotationAttribute.Annotation

sealed class BooleanExt<out T>
object Otherwise : BooleanExt<Nothing>()
class WithData<T>(val data: T) : BooleanExt<T>()

inline fun <T> Boolean.yes(block: () -> T) =
    when {
        this -> {
            WithData(block())
        }

        else -> {
            Otherwise
        }
    }

fun ByteString.toInt(order: ByteOrder = ByteOrder.BIG_ENDIAN): Int {
    if (size == 0) {
        return 0
    }
    if (size == 1) {
        return this[0].toInt()
    }
    if (order == ByteOrder.BIG_ENDIAN) {

        when (this.size) {
            4 -> {
                return (this[0].toInt() shl 24) or
                        (this[1].toInt() shl 16) or
                        (this[2].toInt() shl 8) or
                        (this[3].toInt())
            }

            3 -> {
                return (this[0].toInt() shl 16) or
                        (this[1].toInt() shl 8) or
                        this[2].toInt()
            }

            2 -> {
                return (this[0].toInt() shl 8) or
                        this[1].toInt()
            }
        }
    } else if (order == ByteOrder.LITTLE_ENDIAN) {
        when (this.size) {
            4 -> {
                return (this[3].toInt() shl 24) or
                        (this[2].toInt() shl 16) or
                        (this[1].toInt() shl 8) or
                        (this[0].toInt())
            }

            3 -> {
                return (this[2].toInt() shl 16) or
                        (this[1].toInt() shl 8) or
                        this[0].toInt()
            }

            2 -> {
                return (this[1].toInt() shl 8) or
                        this[0].toInt()
            }
        }
    }
    throw IllegalArgumentException("ByteString size (= ${this.size}) error ")
}

fun Int.toByteString(): ByteString {
    val byteArray = ByteArray(4)
    byteArray[0] = ((this shr 24) and 0x000000FF).toByte()
    byteArray[1] = ((this shr 16) and 0x000000FF).toByte()
    byteArray[2] = ((this shr 8) and 0x000000FF).toByte()
    byteArray[3] = (this and 0x000000FF).toByte()
    return ByteArrayInputStream(byteArray).readByteString(4)
}


fun BufferedSource.readFloat(): Float {
    return Float.fromBits(readInt())
}

fun BufferedSource.readDouble(): Double {
    return Double.fromBits(readLong())
}

inline fun <T> Boolean.no(block: () -> T) = when {
    this -> Otherwise
    else -> {
        WithData(block())
    }
}

inline fun <T> BooleanExt<T>.otherwise(block: () -> T): T =
    when (this) {
        is Otherwise -> block()
        is WithData -> this.data
    }


fun arrayToStr(arrayOfAnys: Array<out Any?>, tag: String, level: Int = 3): String {
    val stringBuffer = StringBuilder()
    val tab = StringBuilder()

    for (it in arrayOfAnys.indices) {
        if (it != 0) {
            tab.clear()
            for (it in 0..level) {
                tab.append("    ")
            }
        }
        stringBuffer.append("${tab}$tag[${it}] = ${arrayOfAnys[it]}")
        if (it != arrayOfAnys.size - 1) {
            stringBuffer.append("\n")
        }
    }
    return stringBuffer.toString()
}

fun u1Array.readInt(index: Int): Int {
    return byteArrayOf(this[index], this[index + 1], this[index + 2], this[index + 3]).toByteString()
        .toInt()
}

inline fun <T> Array<out T>.printToString(): String {
    val builder = StringBuilder()
    val componentType = this.javaClass.componentType.simpleName
    builder.append("\n..........................................$componentType start ......................................................\n")
    for (i in indices) {
        builder.append("$componentType[${i}] = ${this[i]}\n")
    }
    builder.append("..........................................$componentType end........................................................")
    return builder.toString()
}
inline fun ShortArray.printToString(tag: String,constant_pools: Array<CpInfo?>): String {
    val builder = StringBuilder()

    builder.append("\n..........................................$tag start ......................................................\n")
    for (i in indices) {
        builder.append("$tag[${i}] = #${this[i]} +${constant_pools[this[i].toInt()]} \n")
    }
    builder.append("..........................................$tag end ........................................................")
    return builder.toString()
}