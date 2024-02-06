package lectures.part2oop

object MethodNotations extends App{
  class Person(val name: String, favoriteMovie: String, val age: Int = 0){
    def likes(movie: String): Boolean = movie ==favoriteMovie

    def +(person: Person): String = s"${this.name} is hanging out with ${person.name}"
    def unary_! : String = s"$name, what a heck?!"
    def isAlive: Boolean = true
    def apply():String = s"Hi, my name is $name and I like $favoriteMovie"
    def unary_+ : Person = new Person(name, favoriteMovie, age + 1)

    def apply(n: Int) : String = s"$name watched $favoriteMovie $n times"

    def learns(thing: String):String =s"$name is learning $thing"
    def learnsScala : String = this learns "Scala"
    def +(nickname: String): Person = new Person(s"$name ($nickname)", favoriteMovie )
  }

  val mary = new Person("Mary", "Inception")
  println(mary.likes("Inception"))
  println(mary likes "Inception") //equivalent
  //infix notation = operator notation (syntactic sugar)    ||works with methods with only one parameter

  //"operators" in Scala
  val tom = new Person("Tom", "Fight Club")
  println(mary + tom)
  println(mary.+(tom))

  println(1 + 2)
  println(1.+(2))

  //ALL OPERATORS ARE METHODS.
  //Akka actors have ! ?

  //prefix notation -another form of syntactic sugar
  val x = -1  // equivalent with 1.unary_-
  val y = 1.unary_-
  //unary_ prefix only works with - + ~ !

  println(!mary)  //equivalent
  println(mary.unary_!)


  //postfix notation
  println(mary.isAlive)



  //apply
  println(mary.apply())
  println(mary()) //equivalent

  /*
  1. overload the + operator
      mary + " the rockstar" MAry (the rockstar)"
  2. Add an age to the Person class
    add unary +  operator => new Person with the age +1
  +mary => mary with the age incrementer

  3. Add a "learns" method in the Person class => "Mary learns Scala"
    Add a learnsScala method, calls learns method with "Scala".
  Use it in postFix notation.

  4. Overload the apply method
  mery.apply(2) => "Mary watch Inception 2 times"
   */

  println(mary.apply(2))
  println(mary(10))
  println((mary + "the Rockstar")())
  println((+mary).age)
  println(mary.learnsScala)
}
