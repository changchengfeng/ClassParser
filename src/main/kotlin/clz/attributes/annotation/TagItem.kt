package clz.attributes.annotation

import clz.u1

enum class TagItem(val char: Char) {
    B('B'), // byte
    C('C'), // char
    D('D'), //double
    F('F'), // float
    I('I'), // int
    J('J'), // long
    S('S'), // short
    Z('Z'), // boolean
    s('s'), // String
    e('e'), // Enum type
    c('c'), // Class
    at('@'), //Annotation
    A('['); //Array type

    companion object {
        fun getTagItemByValue(value: u1) = values().find { it.char==value .toInt().toChar()}!!
    }
}