/**
 *
 */
package basic

import scala.collection.mutable.MutableList;
import scala.collection.mutable.Set;

/**
 * @author ernie
 *
 */
object BasicSyntax {
  def main(args: Array[String]){
    println("Hello Scala!")
    VarAndVal
    
    val e = new Enumerate()
    e.enumerate("car", 2)
    println
    println(e)
    "car".permutations.foreach { x => print(x);print(" ") }
    println
    
    Option
    
    val b = new BasicSyntax("public", "secret")
    println(BasicSyntax.getPrivateWord(b))
    
    Tuples
  }
  
  def VarAndVal(){
    var age:Int = 19
    val sex:String = "male"
    println("I'm " + age + " years old, and I'm " + sex)
    
    age += 1;
    println("I'm " + age + " years old, and I'm " + sex)
    assert(age == 20)
  }
  
  def Option(){
    val someString:Option[String] = Some("There is a string")
    println(someString match {
      case Some(s) => s
      case None => "None"
    })
    
    val noneString:Option[String] = None
    println(noneString match {
      case Some(s) => s
      case None => "None"
    })
  }
  
  def Tuples(){
    val tuple_3 = ("1", "2", "3")
    val tuple_5 = (tuple_3._1, tuple_3._2, tuple_3._3, "4", "5")
    val tuple_7 = (tuple_3._1, tuple_3._2, tuple_3._3, tuple_5._4, tuple_5._5);
    print(tuple_7)
    print(tuple_5)
    print(tuple_7)
  }
  
  def getPrivateWord(b: BasicSyntax) = b.privateWord
}

class BasicSyntax(val publicWord: String, private val privateWord: String)

class Enumerate(){
  
  val set = Set[String]()
  var list = MutableList[Char]()
    
  def enumerate(input: String, n: Int){
      val in = input.toList
      doEnumerate(in, 0, 0, n);
  }
    
  def doEnumerate(in: List[Char], level: Int, current: Int, n: Int){
    if(level == n){
      list.foreach { x => print(x) }
      print(" ")
      set.add(list.toString())
      return
    }
    for(i <- current to (in.size - 1)){
      list += in.apply(i)
      doEnumerate(in, level + 1, current + 1, n)
      list = list.init
    }
  }
  
  override def toString(): String = set.toString()
}

