package lectures.part2oop

object Generics extends App{

  class MyList[+A]{       //A -> generic type
    //use the type A inside the class
    def add[B >: A](element: B):MyList[B] = ???
    /*
    A =  Cat
    B = Animal
     */
  }

  class MyMap[Key, Value]
  val listOfIntegers = new MyList[Int]
  val listOfStrings = new MyList[String]

  //generic methods
  object MyList {            // objects can t be type paramet.
    def empty[A]: MyList[A] = ???
  }
  val emptyListOfIntegers = MyList.empty[Int]

  //variance problem
  class Animal
  class Cat extends Animal
  class Dog extends Animal

  //1. yes, List[Cat] extends List[Animal = COVARIENCE
  class CovariantList[+A]     //+A => exist a list of covariance
  val animal:Animal = new Cat
  val animalListL:CovariantList[Animal] = new CovariantList[Cat]
  // animalLisr.add(new Dog) ???? HARD QUESTION   => we return a list of Animals

  //2. NO = INVARIANCE
  class InvariantList[A]
  val invariantAnimalList: InvariantList[Animal] = new InvariantList[Animal]

  //3. Hell, no! CONTRAVARIANCE
  class Trainer[-A]    //   -A => contravariant list
  val trainer:Trainer[Cat] = new Trainer[Animal]


  //bounded types  || allow you to use generic classes only for certain types
  class Cage[A <: Animal] (animal: A) //class Cage only accepts type param -> subtypes of Animal
  val cage = new Cage(new Dog)


  //NO!!!
//  class Car
//  val newCage = new Cage(new Car)

    //expand MyList to be generic
}
