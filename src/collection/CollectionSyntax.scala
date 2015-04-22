/**
 *
 */
package collection

import scala.collection.mutable.Map;

/**
 * @author ernie
 *
 */
object CollectionSyntax {
  
  def main(args: Array[String]){
    mutableUsage
  }
  
  def mutableUsage(){
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

}