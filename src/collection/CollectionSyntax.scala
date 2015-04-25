/**
 *
 */
package collection

import scala.collection.mutable.Map;
import scala.collection.mutable.Set;

/**
 * @author ernie
 *
 */
object CollectionSyntax {
  
  def main(args: Array[String]){
    mutableMapUsage
    
    mutableSetUsage
    
    seqAndArray
    
    iterableUsage
  }
  
  def mutableMapUsage(){
    val mutableMap = Map(1 -> "One", 2 -> "Two", 3 -> "Three", 4 -> "Four", 5 -> "Five")
    println(mutableMap)
    mutableMap -= 1
    mutableMap -= (2, 3)
    println(mutableMap)
    mutableMap += 1 -> "One"
    mutableMap += (2 -> "Two", 3 -> "Three")
    mutableMap ++= List(6 -> "Six", 7 -> "Seven")
    println(mutableMap)
    mutableMap.clear()
    println(mutableMap.size)
  }
  
  def mutableSetUsage(){
    val mutableSet = Set(1, 2, 3, 4, 5)
    println(mutableSet)
    mutableSet -= 1
    mutableSet --= List(2, 3)
    mutableSet -= (4, 5)
    println(mutableSet)
    mutableSet += 1
    mutableSet += (2, 3)
    mutableSet ++= List(4, 5)
    println(mutableSet)
    mutableSet.clear
    println(mutableSet)
  }
  
  def seqAndArray(){
    val list = List("1", "2", "3")
    val array = list.toArray
    val seq = array.toSeq
    val seqFor = for(i <- 1 to 5) yield i
    val seqString = Seq("one", "two", "three", "four", "five") 
    println(list)
    println(array)
    println(seq)
    println(seqFor)
    println(seqString.filter(_.length > 3))
    println(seqString.map{
      _.reverse
    })
  }
  
  def iterableUsage(){
    val list = List(1, 3, 5, 7, 9, 11, 13, 15)
    val stringList = List("Ernie", "Fiona", "Tom", "Grace")
    val set = Set(1, 3, 5)
    val longSet = set + (7, 8, 9, 10)
    
    val itGrouped = list grouped 3
    itGrouped.foreach { x => print(x) }
    println
    
    val itSliding = list sliding 3
    itSliding.foreach { x => print(x + " ") }
    println
    
    val listTakeRight = list takeRight 3
    println(listTakeRight)
    
    val listDropRight = list takeRight 3
    println(listDropRight)
    
    println(list zip stringList)
    
    println(list zipAll(stringList, "wtf", "Jennifer"))
  
    println(list zipWithIndex)
    
    println(set sameElements Set(3, 2, 1))
    
    println(longSet sameElements Set(10, 9, 8, 7, 5, 3, 1))
  }

}