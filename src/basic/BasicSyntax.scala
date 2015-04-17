/**
 *
 */
package basic

import scala.collection.mutable.MutableList;
import scala.collection.mutable.Set;
import scala.collection.mutable.Map;

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
    
    Lambda
    
    ListUsage
  
    MapUsage
    
    SetUsage
    
    formatUsage
  
    patternMatching
    
    caseClass
    
    rangeUsage
    
    partialFunction
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
    val tuple_7 = (tuple_3._1, tuple_3._2, tuple_3._3, tuple_5._4, tuple_5._5)
    print(tuple_7)
    print(tuple_5)
    println(tuple_7)
  }
  
  def Lambda(){
    val newFunction = new Function[Int, Int]{
      def apply(x: Int): Int = x + 5
    }
    val multiplier = (i: Int) => i * 10
    var decrementer = 1
    def closure = {
      x: Int => x - decrementer
    }
    def summation(x: Int, y: Int => Int) = y(x)
    def add(x: Int) = {
      new Function[Int, Int](){
        def apply(y: Int): Int = x + y
      }
    }
    def add5 = add(5)
    def firstChar(xs: List[String]) = xs map (_.charAt(0))
    def summationCurried = (summation _).curried
    def customFilter(f: Int => Boolean)(xs: List[Int]) = {
      xs filter f
    }
    def onlyOdd(x: Int) = (x - 1) % 2 == 0
    val onlyOddFilter = customFilter(onlyOdd) _
    
    println("newFunction: " + newFunction(1))
    println("multiplier: " + multiplier(1))
    println("decrementer: " + closure(2))
    decrementer = 2
    println("decrementer: " + closure(2))
    println("summation: " + summation(5, closure))
    println("add: " + add(1)(1))
    println("add5: " + add5(1))
    println("firstChar: " + firstChar(List("123", "qwe", "asd", "zxc")))
    println("summationCurried: " + summationCurried(5)(closure))
    println(List(1, 2, 3, 4, 5))
    println("customFilter: " + customFilter(onlyOdd)(List(1, 2, 3, 4, 5)))
    println("onlyOddFilter: " + onlyOddFilter(List(1, 2, 3, 4, 5)))
  }
  
  def ListUsage(){
    val l1 = (1 to 5).toList
    val l2 = List(1, 2, 3, 4, 5)
    
    println("l1 eq l2: " + (l1 eq l2))
    println("l1 == l2: " + (l1 == l2))
    println("init: " + l1.init + ", last: " + l1.last )
    println("head: " + l1.head + ", tail: " + l1.tail )
    println(l1.filterNot { x => x % 2 == 0 })
    println(l1 map (_ * 2))
    println(l1 filter (_ % 2 == 0))
    println(l1.reduceLeft(_ + _))
    println(l1.foldLeft(10)(_ + _))
  }
  
  
  def MapUsage(){
    val map = Map[String, Any]("MI" -> "Michigan", "OH" -> "Ohio", "WI" -> "Wisconsin", "MI" -> "Michigan")
    val mapValues = map.values
    map("Tuple") = (123, 456, 789)
    val substractMap = map -- List("MI")
    
    
    println("Head of map: " + mapValues.head)
    println("Size of map: " + map.size)
    println("Tuple in map: " + map("Tuple"))
    
  }
  
  def SetUsage(){
    val set = Set("Java", "C++", "C", "Python")
    val addSet = set + "Scala"
    val subSet = set - "Python"
    val interSet = set intersect addSet
    val union = set union interSet
    val diffSet = set diff subSet
    
    println(set)
    println(addSet)
    println(subSet)
    println("Subset of: " + (subSet subsetOf addSet))
    
  }
  
  def formatUsage(){
    val greeting = "Hello"
    val char = 'H'
    val number = 180
    val cake = "cake"
    
    println("Application %s".format(greeting))
    println("%cello application".format(char))
    println("%d beverages are provided" format number - 100)
    println("%d %s are provided" format(number - 80, cake))
  }
  
  def patternMatching(){
    def intMatch(x: Int): String = x match{
      case 1 => "one"
      case 2 => "two"
      case 3 => "three"
      case _ => "many"
    }
    
    def colorMatch(x: String): Any = x match{
      case "blue" => (0, 0, 255)
      case "green" => (0, 255, 0)
      case "red" => (255, 0, 0)
      case _ => print(x); 0
    }
    
    def multiMatch(x: Any) = x match {
      case ("yesterday", "dinner") => "Good night" 
      case ("today", "lunch") => "It's noon"
      case ("tomorrow", "breakfast") => "Good morning"
      case _ => "what?"
    }
    
    def substituteMatch(x: Any) = x match{
      case ("dinner", people) => people + " is eating dinner"
      case ("lunch", people) => people + " is eating lunch"
      case ("breakfast", people) => people + " is eating breakfast"
      case _ => "Who is doing what?"
    }
    
    val DinnerRegularExpression = """Dinner time: people=([^,]+),\s+food=(.+)""".r
    val LunchRegularExpression = """Lunch time: people=([^,]+),\s+food=(.+)""".r
    val BreakfastRegularExpression = """Breakfast time: people=([^,]+),\s+food=(.+)""".r
    def mealRegularExpression(rex: String) = rex match{
      case (DinnerRegularExpression(people, food)) => "%s is eating %s".format(people, food)
      case (LunchRegularExpression(people, food)) => "%s is eating %s".format(people, food)
      case (BreakfastRegularExpression(people, food)) => "%s is eating %s".format(people, food)
      case _ => "what?"
    }
    
    val element = List(1, 2, 3, 4, 5) match{
      case x::y::Nil=> x
      case x::xs => xs.head
      case _ => 0
    }
    
    println(intMatch(2))
    println(colorMatch("blue"))
    println(multiMatch(("yesterday", "dinner")))
    println(substituteMatch(("lunch", "Ernie")))
    println(mealRegularExpression("Breakfast time: people=Ernie, food=guava"))
    println(element)
  }
  
  def caseClass(){
    abstract class Exp
    case class And(exp1: Exp, exp2: Exp) extends Exp
    case class Or(exp1: Exp, exp2: Exp) extends Exp 
    case class Not(exp: Exp) extends Exp
    case class Val(value: Boolean) extends Exp
    
    def printExp(exp: Exp){
      exp match{
        case Val(v) =>
          print(v)
        case Or(x, y) =>
          print("(")
          printExp(x)
          print("||")
          printExp(y)
          print(")")
        case And(x, y) =>
          print("(")
          printExp(x)
          print("&&")
          printExp(y)
          print(")")
        case Not(x) =>
          print("(~")
          printExp(x)
          print(")")
       }
    }
    
    val texp = Val(true)
    val atexp = Val(true)
    val exp = And(Val(true), Or(Not(Val(false)), Val(false)))
    val expc = exp.copy(exp1 = Not(Val(false)))
    val parts = And.unapply(exp).get
    
    printExp(exp)
    println
    println(exp.toString)
    printExp(expc)
    println
    println(parts)
    println("texp == atexp: " + (texp == atexp))
    println("Serializable: " + exp.isInstanceOf[Serializable])
  }
  
  def rangeUsage(){
    val rangeList = Range(2, 11, 3);
    val rangeInclusiveList = Range(2, 11, 3).inclusive;
    
    println(rangeList)
    println(rangeInclusiveList)
  }

  def partialFunction(){
    def sum(a: Int, b: Int) = a + b 
    def sum5 = sum(5, _: Int)
    val quatorEven: PartialFunction[Int, Int] = new PartialFunction[Int, Int](){
      def isDefinedAt(x: Int) = x % 2 == 0
      
      def apply(y: Int) = y * 4
    }
    val quintupleOdd: PartialFunction[Int, Int] = new PartialFunction[Int, Int](){
      def isDefinedAt(x: Int) = x % 2 != 0
      
      def apply(y: Int) = y * 5
    }
    val print: PartialFunction[Int, String] = {
      case x: Int if(x % 2 != 0) => "Odd"
      case x: Int if(x % 2 == 0) => "Even"
    }
    val addFive = (x: Int) => x + 5
    val orElseAndThen = quatorEven orElse quintupleOdd andThen addFive
    val orElseAndThenPrint = quatorEven orElse quintupleOdd andThen print
    
    println(sum5(5))
    println(orElseAndThen(6))
    println(orElseAndThenPrint(6))
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

