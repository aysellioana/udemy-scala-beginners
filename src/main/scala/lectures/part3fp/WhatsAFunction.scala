package lectures.part3fp

object WhatsAFunction extends App{

  //DREAM: use functions as first class elements
  //problem: oop

  val doubler = new MyFunction[Int, Int]{
    override def apply(elemet: Int): Int = elemet * 2
  }

  println(doubler(2))

  //function types = Function1[A, B]
  val stringToIntConvertor = new Function1[String, Int]{
    override def apply(string: String): Int = string.toInt
  }

  println(stringToIntConvertor("3") + 4)

  val adder: ((Int, Int) => Int) = new Function2[Int, Int, Int]{
    override def apply(a: Int, b: Int): Int = a + b
  }

  //function types Function2[A, B, R] ===(A,B) => R

  //ALL SCALA FUNCTIONS ARE OBJETS
  /*
  1. a functions which takes 2 strings and concatenates them
  2. transform the MyPredicate and My Transformer into function types
  3. define a function which takes an int and returns anotherf function which takes an int and return an int
    -what s the type of this function
    -how to do it
   */

  def concatenator:(String, String) => String = new Function2[String, String, String]{
    override def apply(a: String, b: String): String =  a + b
  }
  println(concatenator("Hello ", "Scala"))

  //Function1[Int, Function1[Int, Int]]
  val superAdder: Function1[Int, Function1[Int, Int]] = new Function1[Int, Function1[Int, Int]]{
    override def apply(x: Int): Function1[Int, Int] = new Function1[Int, Int]{
      override def apply(y: Int): Int = x + y
    }
  }
  val adde3 = superAdder(3)
  println(adde3(4))
  println(superAdder(3)(5)) //curried function

}

trait MyFunction[A, B]{
  def apply(element: A): B = ???
}
