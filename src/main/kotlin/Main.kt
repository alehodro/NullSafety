import java.lang.Exception

fun main() {

/*
Kotlin - имеет мощный механизм типизации, и свой собственный подход к обработке null объектов.
Главной целью такого подхода является максимально возможное избежание получений NullPoint исключений во время
выполнения программы, и выявление всех потенциально опасных моментов в коде в этом контексте во время компиляции.
Поэтому для всех типов, которые могут иметь значение есть дополнительный классификатор Nullable, при этом Nullable
типы уже не могут использовать свои функции явно и требуют особого подхода для обработки
*/

/*
1.Самый очевидный способ обработки nullable объектов является проверка их в операторе if
 */

    val a: String? = "test"
    // val b=a.length - здесь ошибка компиляции
    val b = if (a != null && a.length > 0) a.length else null
    println(b)

/*
При этом проверка сработает только если переменная a будет immutable, иначе компилятор не знает точно, что после
проверки a не изменилась и снова не получила значение null
 */

/*
2. Также для обработки null объектов можно использовать так называемые Safe calls.

При безопасном вызове функции nullable объекта, если объект не null вернется значение функции, если null - то
вернется null
 */

    val someString: String? = null
    //   val someStringLength = someString.length - ошибка компиляции
    val someStringLength = someString?.length
    println(someStringLength)
    // В переменной someString - null, поэтому безопасный вызов функции .length из этой переменной вернет null  в переменную someStringLength

    /*
    Оператор ?. удобно использовать для проверки свойств в цепочке.

     */
    data class Head(val name: String)
    data class Department(val head: Head?)
    data class Employee(val name: String, val department: Department?)

    val bob: Employee? = Employee("Bob", Department(Head("John")))
    val bobsHeadName = bob?.department?.head?.name
    // если в цепочке не будет самого Боба, либо у него не будет департамента, либо в департаменте не будет главы
    // то в переменную с именем начальника Боба запишется null
    println(bobsHeadName)

    /*
    3. Elvis оператор ?: позволяет не только обработать nullable объект, но и присвоить определенное значение, если объект
    является null (а не просто класть null в переменную, если safe call осуществлен для null oбъекта)
     */
    class Girl(val name: String)
    class Boy(val name: String) {
        var girlFriend: Girl? = null
        fun getGirlFriend(girl: Girl?) {
            girlFriend = girl
            val girl = girl?.name ?: "his hand"
            println("$name have sex with $girl")
        }
    }

    val boy = Boy("John")
    val girl = Girl("Mary")
    // boy.getGirlFriend(null)
    // раскоменнть выше и закоменть ниже, чтобы протестировать кейс с выбросом исключения через elvis
    boy.getGirlFriend(girl)
    /*
    Elvis оператор также позволяет использовать экспрешены throw и return
     */
    fun Boy.dropGirlFriend() {
        val girlToDrop = girlFriend ?: throw Exception("${this.name} has no girlfriend to drop")
        println("${this.name} has dropped ${girlToDrop.name}")
    }

    boy.dropGirlFriend()
// здесь вылетит исключение, если у парня нет девушки

/*
4. Оператор !! можно использовать когда, не страшно получить NPE  в runtime, но лучше так не надо.
 */

    val someInt: Int? = 7
    // умножаем someInt на 3, если она не null, иначе вылетит NPE
    val anotherInt = someInt!!.times(3)
    println(anotherInt)

/*
5. Safe casts - также для обработки nullable объектов можно использовать безопасные объявления типов.
При этом если объект является null экспрешен вернет null, иначе вернет значение
 */

   // val text:String?=null
    val text:String?="Kotlin"
    println(text as? String)

}