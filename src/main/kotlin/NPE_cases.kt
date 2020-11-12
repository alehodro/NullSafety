/*
Ниже приведены случаи, когда могут возникнуть NPE во время runtime
 */

/*
1. Когда мы вручную в коде указали выбрасывать NPE
 */

/*
2. Когда мы использовали оператор !!
 */

/*
3.
 */


/*
 A superclass constructor calls an open member whose implementation in the derived class uses uninitialized state;
  */
/*
abstract class A{
    init {
        println(returner())
    }
    abstract fun returner():String
}

class B():A(){
    val t:String
    init {
       t="хуй"
    }
    override fun returner(): String {
        return t
    }
}
*/



//  val t=B()

/*
An uninitialized this available in a constructor is passed and used somewhere ("leaking this");
 */

class AA {
    val t2: String

    init {
        aauser(this)
        t2 = "jgf"
    }

    fun aauser(t: AA) {
        println(t.t2.toLowerCase())
    }
}

val rtrt = AA()