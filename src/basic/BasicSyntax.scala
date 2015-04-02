/**
 *
 */
package basic

import scala.collection.mutable.MutableList;

/**
 * @author ernie
 *
 */
object BasicSyntax {
  def main(args: Array[String]){
    println("Hello Scala!")
    VarAndVal()
    
    val e = new Enumerate()
    e.enumerate("car", 2)
    println
    "car".permutations.foreach { x => print(x);print(" ") }
  }
  
  def VarAndVal(){
    var age:Int = 19
    val sex:String = "male"
    println("I'm " + age + " years old, and I'm " + sex)
    
    age += 1;
    println("I'm " + age + " years old, and I'm " + sex)
    assert(age == 20)
  }
  
}

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
      set.+(list.toString())
      return
    }
    for(i <- current to (in.size - 1)){
      list += in.apply(i)
      doEnumerate(in, level + 1, current + 1, n)
      list = list.init
    }
  }
}

