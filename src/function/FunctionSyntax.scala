/**
 *
 */
package function

/**
 * @author ernie
 *
 */
object FunctionSyntax {
  
  implicit object StringAdd extends AddGroup[String]{
    def add(x: String, y: String): String = x concat y 
    def unit: String = ""
  }
  
  implicit object IntAdd extends AddGroup[Int]{
    def add(x: Int, y: Int): Int = x + y
    def unit: Int = 0
  }
  
  def sum[T](xs: List[T])(implicit a: AddGroup[T]): T = 
    if(xs.isEmpty) a.unit
    else a.add(xs.head, sum(xs.tail))
  
  object IntPredef{
     class IntWrapper(val num: Int){
      def isOdd = num % 2 != 0
      def isEven = !isOdd
    }
    implicit def aMethod(value: Int) = new IntWrapper(value)
  }
  
  def main(args: Array[String]){
    println(sum(List(1, 2, 3)))
    println(sum(List("A", "B", "C")))
    import IntPredef._
    println(19.isOdd)
    println(20.isEven)
    
    infixPrefixPostfixUsage
  }

  def infixPrefixPostfixUsage(){
    val integer: Int = 51
    val string: String = "Hello world!"
    val light = new Light
    
    println(integer.+(5))
    println(string indexOf 'o')
    println(string.indexOf('o'))
    println(string.indexOf('o', string.indexOf('o') + 1))
    println(integer toHexString)
    println(-light)
    println(+light)
  }
}

abstract class AddGroup[T]{
  def add(x: T, y: T): T
  def unit: T
}

class Light{
  def unary_+ = "open"
  
  def unary_- = "close"
}