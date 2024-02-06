package exercices

//import exercices.ListTest.{MyPredicate, MyTransformer}

abstract class MyList[+A] {
  /*
  head = first element of the list
  tail = ramainder of the list
  isEmpty = is this list empty
  add(int) => new list with this element added
  toString  => a string representation of the list
   */

  def head: A
  def tail:MyList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B):MyList[B]

  def printElements: String
  override def toString: String = "[" + printElements + "]"

  //higher-order functions
  def map[B](trasformer: A => B):MyList[B]
  def flatMap[B](transformer:A => MyList[B]):MyList[B]
  def filter(predicate: A => Boolean):MyList[A]

  //concatenation
  def ++[B >: A](list: MyList[B]): MyList[B]

  //hofs
  def foreach(f: A => Unit): Unit
  def sort(compare:(A, A) => Int):MyList[A]
  def zipWith[B, C](list: MyList[B], zip:(A,B) => C):MyList[C]
  def fold[B](start: B)(operator: (B, A) => B): B
}

case object Empty extends MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)
  def printElements: String = ""

  def map[B](trasformer: Nothing =>B): MyList[B] = Empty
  def flatMap[B](transformer: Nothing=>  MyList[B]): MyList[B] = Empty
  def filter(predicate: Nothing => Boolean): MyList[Nothing] = Empty
  def ++[B >: Nothing](list: MyList[B]): MyList[B] = list

  //hofs
  def foreach(f: Nothing => Unit): Unit = ()
  def sort(compare: (Nothing,Nothing) => Int) = Empty
  def zipWith[B, C](list:MyList[B], zip: (Nothing, B) => C): MyList[C] =
    if(!list.isEmpty) throw new RuntimeException("Lists do not have the same lenght")
    else Empty

  def fold[B](start: B)(operator: (B, Nothing) => B): B = start
}



 case class Cons[+A](h: A, t: MyList[A]) extends MyList[A]{
  def head: A =  h
  def tail: MyList[A] = t
  def isEmpty: Boolean = false
  def add[B >: A](element: B): MyList[B] = new Cons(element, this)
  def printElements: String =
    if(t.isEmpty) "" + h
    else h.toString + " " + t.printElements

  /*
    [1,2,3].filter(n % 2 == 0) =
    [2,3].filter(n % 2 == 0) =
    = new Cons(2, [3.filter(n % 2 == 0))
    = new Cons(2, Empty.filter(n % 2 == 0))
    = new Cons(2, Empty)
   */
  def filter(predicate: A => Boolean):MyList[A] =
    if(predicate.apply(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)

    /*
    [1,2,3].map( n* 2)
      = new Cons(2, [2,3].map(n*2))
      = new Cons(2, new Cons(4, [3].map(n*2)))
      = new Cons(2, new Cons(4, new Cons(6, Empty.map(n * 2))))
      = new Cons(2, new Cons(4, new Cons(6, Empty))))
     */
  def map[B](trasformer:A => B):MyList[B] =
    new Cons(trasformer.apply(h), t.map(trasformer))

  /*
  [1,2] ++ [3,4,5]
  = new cons(1, [2] ++ [3,4,5])
  = new Cons(1, new Cons(2, Empty ++ [3,4,5]))
  = new cons(1, new Cons(2, new Cons(3, new cons(4, new Cons(5))))
   */
  def ++[B >: A](list: MyList[B]): MyList[B] = new Cons(h, t ++ list)
  /*
  [1,2].flatMap(n => [n, n + 1])
  = [1, 2] ++ [2].flatMap( n => [n, n+1])
  =[1,2] ++ [2,3] ++ Empty.flatMap(n => [n, n+1])
  =[1,2] ++ [2,3] ++ Empty
  =[1,2,2,3]
     */
  def flatMap[B](transformer: A =>MyList[B]):MyList[B] =
    transformer(h) ++ t.flatMap(transformer)


  //hofs
  def foreach(f: A => Unit): Unit = {
    f(h)
    t.foreach(f)
  }

  def sort(compare: (A, A) => Int): MyList[A] = {
    def insert(x: A, sortedList: MyList[A]): MyList[A] =
      if(sortedList.isEmpty) new Cons(x,Empty)
      else if(compare(x, sortedList.head) <= 0) new Cons(x, sortedList)
      else new Cons(sortedList.head, insert(x, sortedList.tail))
    val sortedTail = t.sort(compare)
    insert(h, sortedTail)
  }

  def zipWith[B, C](list: MyList[B], zip: (A, B) => C): MyList[C] =
    if(list.isEmpty) throw new RuntimeException("Lists do not have the same lenght")
    else new Cons(zip(h, list.head), t.zipWith(list.tail, zip))

    /*
    [1,2,3].fold(0)(+) =
    =[2,3].fold(1)(+) =
    =[3].fold(3)(+) =
    = [].fold(6)(+)
    = 6
     */

  def fold[B](start: B)(operator: (B, A) => B): B = {
    t.fold(operator(start,h))(operator)
  }
}


object ListTest extends App{
//  val list = new Cons(1, new Cons(2, new Cons(3, Empty)))
//  println(list.tail.head)
//  println(list.add(4).head)
//  println(list.isEmpty)
//  println(list.toString)
val listOfIntegers: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
val cloneListOfIntegers: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
val anotherlistOfIntegers: MyList[Int] = new Cons(4, new Cons(5, Empty))
val listOfStrings: MyList[String] = new Cons("Hello", new Cons("Scala", Empty))

println(listOfIntegers.toString)
println(listOfStrings.toString)

  /*
    1. Generic trait MyPredicate[-T] with a little method test(T) => Boolean
    2. Generic trait MyTransformer[-A, B] with a method transform(A) => B
    3. MyList:
    -map(transformer) => MyList
    -filter(predicate) => MyList
    -flatMAp(transformer from A to MyList[B]) => MyList[B]

    class EvenPredicate extends MyPredicate[Int]
    class StringToIntTransformer extends MyTransformer[String, Int]

    [1,2,3].map(n * 2) = [2,4,6]
    [1,2,3,4].filter(n % 2) =[2,4]
    [1,2,3].flatMap(n => [n, n+1]) => [1,2,2,3,3,4]
     */
//  trait MyPredicate[-T]{  //T =>Boolean
//    def test(elem: T): Boolean
//  }
//
//  trait MyTransformer[-A, B]{  // A => B
//    def transform(elem: A): B
//  }

//  println(listOfIntegers.map(new Function1[Int, Int]{
//    override def apply(elem: Int): Int = elem * 2
//  }).toString)
//equivalent
  println(listOfIntegers.map(elem => elem * 2).toString)
  // or
  println(listOfIntegers.map(_ * 2).toString)

//  println(listOfIntegers.filter(new Function1[Int, Boolean]{
//    override def apply(elem: Int): Boolean = elem % 2 == 0
//  }).toString)
  println(listOfIntegers.filter(elem => elem % 2 ==0).toString)
  //or
  println(listOfIntegers.filter(_ % 2 ==0).toString)

  println((listOfIntegers ++ anotherlistOfIntegers).toString)

//  println(listOfIntegers.flatMap(new Function1[Int, MyList[Int]]{
//    override def apply(elem: Int): MyList[Int] = new Cons(elem, new Cons(elem + 1, Empty))
//  }).toString)
  println(listOfIntegers.flatMap(elem => new Cons(elem, new Cons(elem + 1, Empty))).toString)

  println(cloneListOfIntegers ==listOfIntegers)


  //hofs
  listOfIntegers.foreach(println)
  println(listOfIntegers.sort((x,y) => y - x))
  println(anotherlistOfIntegers.zipWith[String, String](listOfStrings, _ + "-" + _))
  println(listOfIntegers.fold(0)(_ + _))

  //for comprehensions
  val combinations = for{
    n <- listOfIntegers
    string <- listOfStrings
  } yield n + "-" +string
  println(combinations)
  
  

}