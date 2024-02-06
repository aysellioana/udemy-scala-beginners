package lectures.part2oop

object AnonymousClass extends App {

  abstract class Animal{
    def eat:Unit
  }

  //anonymous class
  val funnyAnimal:Animal = new Animal{
    override def eat: Unit = print("ahahahahaha")
  }

//equivalent with
//  class AnonymousClass$$anon$1 extends Animal{
//    override def eat: Unit = print("ahahahahaha")
//  }
//  val funnyAnimal: Animal = new AnonymousClass$$anon$1
  println(funnyAnimal.getClass)

  class Person(name: String){
    def sayHi: Unit = println(s"Hi, my name is $name, how can I help?")
  }

  val jim= new Person("Jim"){
    override def sayHi: Unit = println(s"Hi, my name is Jim, how can I be of service?")
  }
  
}
