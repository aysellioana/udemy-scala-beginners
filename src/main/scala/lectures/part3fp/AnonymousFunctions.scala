package lectures.part3fp

object AnonymousFunctions extends App{

//  val doubler = new Function1[Int, Int]{
//    override def apply(x: Int) = x * 2}
    //equivalent =>

  //anonymous function(LAMBDA)
  val doubler: Int => Int = (x: Int) => x * 2

  //multiple params in a lambda
  val adder:(Int, Int) => Int = (a: Int, b: Int) => a + b

  //no params
  val justDoSomething:() => Int = () => 3

  //careful
  println(justDoSomething) //function itself
  println(justDoSomething()) //call


  //curly braces with lambda
  val stringToInt = {
    (str: String)=>
      str.toInt
  }

  //MOAR syntactic sygar
  val niceIncrementer: Int => Int = _ + 1  //equivalent x=> x + 1
  val niceAdder: (Int, Int) => Int = _ + _ //equivalent to (a,b) => a + b

  /*
  1. MyList : replace all Functionx calls with lambdas
  2. rewrite the "special" adder as anonymous function
   */

  //2.
  val superAdd = (x: Int) => (y: Int) => x + y
  println(superAdd(3)(4))

}
