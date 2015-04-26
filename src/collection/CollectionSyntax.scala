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
    
    traversableUsage
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

  def traversableUsage(){
    val list = List(1, 2, 3)
    val set = Set(3, 4, 5)
    val traversable = List(list, set)
    val mod2time3: PartialFunction[Int, Int] = {
      case i: Int if (i % 2 == 0) => i * 3
    }
    val nmod2time4: PartialFunction[Int, Int] = {
      case i: Int if (i % 2 != 0) => i * 4
    }
    val listmap = List(1 -> "One", 2 -> "Two", 3 -> "Three")
    import Stream.cons
    val stream = cons(0, cons(1, Stream.empty))
    def streamer(v: Int): Stream[Int] = cons(v, streamer(v + 1))
    val streamerstream = streamer(5)
    val smallerthan2: PartialFunction[Int, String] = {
      case i: Int if (i < 2) => "Smaller than 2"
    }
    val smallerthan3biggerthan1: PartialFunction[Int, String] = {
      case i: Int if (i > 1 && i < 3) => "Smaller than 3 and bigger than 1"
    }
    val biggerthan2: PartialFunction[Int, String] = {
      case i: Int if (i > 2) => "Bigger than 2"
    }
    val foldLeft = (0 /: list){
      (`running total`, `next element`) => `running total` - `next element` 
    }
    val foldRight = (list :\ 0){
      (`running total`, `next element`) => `running total` - `next element` 
    }
    val MAX_SIZE = 1000000
    val reduceLeftStartTime = new java.util.Date
    (1 to MAX_SIZE) reduceLeft (_ + _)
    val reduceLeftEndTime = new java.util.Date
    val reduceRightStartTime = new java.util.Date
    (1 to MAX_SIZE) reduceRight (_ + _)
    val reduceRightEndTime = new java.util.Date
    val totalReduceLeftTime = reduceLeftEndTime.getTime - reduceLeftStartTime.getTime
    val totalReduceRightTime = reduceRightEndTime.getTime - reduceRightStartTime.getTime
    var logging = List[String]()
    def addLogging(s: String){
      logging = logging :+ s
    }
    list.view.map{ x => addLogging("Adding 5 to %s".format(x)); x + 5}
              .map {x => addLogging("Tripling %s".format(x)); x * 3}.force
    
    println(list ++ set)
    println(set ++ list)
    println(traversable.flatten)
    println(traversable.flatMap(_.map(_ * 4)))
    println(list.flatMap(i => if (i % 2 == 0) Some(i) else None))
    println(set.collect{
        case x: Int if (x % 2 == 0) => x * 3
          })
    println(list.toIndexedSeq)
    println(list.toStream)
    println(listmap.toMap)
    println(stream.foreach { x => print(x + " ") })
    println(list.find(_ % 2 == 0))
    println(list.slice(0, 2))
    println(streamerstream)
    println(((streamerstream drop 5) take 10).toList)
    println(list.splitAt(2))
    println(list partition (_ < 2))
    println(list groupBy{
      smallerthan2 orElse
      smallerthan3biggerthan1 orElse
      biggerthan2
    })
    println(list forall (_ > 2))
    println(list exists (_ > 2))
    println(list count (_ > 2))
    println(foldLeft)
    println(list.foldLeft(0)((x, y) => x - y))
    println((0 /: list)(_ - _))
    println(list.foldLeft(0)(_ - _))
    println(foldRight)
    println(list.foldRight(0)((x, y) => x - y))
    println((list :\ 0)(_ - _))
    println(list.foldRight(0)(_ - _))
    println(listmap.reduceLeft((x, y) => (x._1 - y._1 , x._2 + y._2)))
    println(listmap.reduceRight((x, y) => (x._1 - y._1 , x._2 + y._2)))
    println(totalReduceLeftTime + " " + totalReduceRightTime)
    println(traversable.transpose)
    println(list.mkString(","))
    println(list.mkString("<", ",", ">"))
    println(logging)
  }
}