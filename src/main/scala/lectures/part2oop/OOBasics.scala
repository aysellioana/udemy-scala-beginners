package lectures.part2oop

object OOBasics extends App{

  val person = new Person("John", 26)
  println(person.x)
  person.greet("Daniel")
  person.greet()
  val author = new Writer("Charles", "Dickens", 1812)
  val imposter = new Writer("Charles", "Dickens", 1812)
  val novel = new Novel("Great Expectations", 1861, author)

  println(novel.authorAge)
  println(novel.isWrittenBy(imposter))

  val counter = new Counter
  counter.inc.print
  counter.inc.inc.inc.print
  counter.inc(10).print
}

//class parameters are NOT FIELDS

//constructor
class Person(name: String,val age: Int=0) {
  //body
  val x = 2

  println(1 + 3)

  def greet(name: String): Unit  =println(s"${this.name} says: Hi, $name")
  //Overloadind: methods with the same name, but different signature
  def greet(): Unit = println(s"Hi, I am $name")

  //multiple constructors
  def this(name: String) = this(name,0)
  def this() = this("John Doe")


}
/*
    Novel and Writer class
    Writer: first name, surname, year
     -method fullname
    Novel: name, year of release, author
      -authorAge
      -isWrttenBy(author)
      -copy( new year of release)  = new instance if Novel
 */

  /*
  Counter class
   -receives an int value
    -method current count
    -method to increment/decrement = > new Counter
    -overload inc/dec to receive an amount
   */

class Writer(firstName: String, surname:String,val year:Int)  {
  def fullname:String =firstName + " " + surname
}
class Novel(name: String, year: Int, author: Writer){
    def authorAge = year - author.year
    def isWrittenBy(author: Writer) = author == this.author
    def copy(newYear: Int):Novel = new Novel(name, newYear, author)
}

class Counter(val count: Int=0){
  def inc = {
    println("incrementing")
    new Counter(count + 1)
  } //immutability


  def dec ={
    println("decrementing")
    new Counter(count - 1)
  }

  def inc(n: Int):Counter = {
    if(n <=0 )this
    else inc.inc(n-1)
  }

  def dec(n: Int) :Counter={
    if (n <= 0) this
    else dec.dec(n - 1)
  }

  def print = println(count)
}