package clz

import okio.*
import okio.Path.Companion.toPath
import kotlin.io.use

fun main(vararg args: String) {
    val file = ClassFile::class.java.classLoader.getResource("MainTest.class").file
    FileSystem.SYSTEM.source(file.toPath()).use {
        ClassFile(it.buffer())
    }
}

