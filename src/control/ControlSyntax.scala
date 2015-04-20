/**
 *
 */
package control

/**
 * @author ernie
 *
 */
object ControlSyntax {
  
  def main(args: Array[String]){
    
    forUsage
    
  }
  
  def forUsage(){
    val zeroToNine = Range(0, 10)
    var sum: Int = 0
    for(num <- zeroToNine)
      sum += num
    println(sum)
    
    val zeroToFive = Range(0, 6)
    val pairList = for{
      x <- zeroToNine
      y <- zeroToFive
    }yield(x, y)
    println(pairList)
  }
}