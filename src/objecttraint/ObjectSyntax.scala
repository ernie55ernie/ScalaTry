/**
 * 
 */
package objecttrain

/**
 * @author ernie
 *
 */
object ObjectSyntax {
  def main(args: Array[String]){
    new X(true)
    
    val messenger = new Messenger
    messenger.error
    messenger.succuss
    print(messenger.logCache.mkString(";"))
  }
}

trait Expression{
  println("Initializing Expression")
}

trait Literal extends Expression{
  println("Initilaizing Literal")
}

class X(x: Boolean) extends Literal{
  println("Initializing X" + x)
}

trait Logging{
  var logCache = List[String]()
  
  def log(message: String){
    logCache = logCache :+ message;
    println(message)
  }
}

class Messenger extends Logging{
  def error(){
    log("Internet connection failed")
  } 
  
  def succuss(){
    log("Message sent")
  }
}