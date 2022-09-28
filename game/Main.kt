package game

import kotlin.reflect.typeOf

interface A {
    fun a () {
    }
}

class B : A {
    override fun a () {

 }
    fun b ( ) {

    }
}
 fun main(args: Array<String>) {


    var a : A = B()

     a.a()
     a = a as B;

     a.b()
 }