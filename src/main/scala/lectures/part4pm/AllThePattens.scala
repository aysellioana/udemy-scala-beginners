package lectures.part4pm

import exercices.{MyList, Cons, Empty}

object AllThePattens extends App{

  // 1- constants
  val x: Any = "Scala"
  val constants = x match {
    case 1 => " a number"
    case " Scala" => "THE Scala"
    case true => "The Truth"
    case AllThePattens => "A singleton object"
  }

  //2 - match anythinh
  //2,1 wildcard

  val matchAnything = x match {
    case _ =>
  }

  // 2.2 variable
  val matchAVariable = x match {
    case something => s"I'v found $something"
  }

  //3 -  tuples
  val aTuple = (1,2)
  val matchATuple = aTuple match {
    case(1,1) =>
    case(something, 2) => s"I'v found $something"
  }

  val nestedTuple = (1, (2,3))
  val matchNestedTuple = nestedTuple match{
    case (_, (2, v)) =>
  }
  //PMs can be NESTED!

  // 4 -  case classes - constructor pattern
  // PMs can be nested with CCS as well
  val aList: MyList[Int] = Cons(1, Cons(2, Empty))
  val matchAList = aList match {
    case Empty =>
    case Cons(head, Cons(subhead, subtail)) =>
  }
  //5 - list patterns
  val aStandardList = List(1,2,3,4)
  val standardListMatching = aStandardList match {
    case List(1, _, _, _) => //extractor -advanced
    case List(1, _*) => // list of arbitrary lenght - advanced
    case 1:: List() => //infix pattern
    case List(1,2,3) :+ 42 => //infix pattern
  }

  // 6- types specifiers
  val unknown: Any = 2
  val unknownMAtch = unknown match {
    case list: List[Int] => //explicit type specifier
    case _ =>
  }

  // 7- name binding
  val nameBindingMatch = aList match {
    case nonEmptyList @ Cons(_, _) => //name binding => use the name later(here)
    case Cons(1, rest @ Cons(2, _)) => //namebinding inside nested patterns
  }

  //8 - multi-patterns
  val multipattern = aList match {
    case Empty | Cons(0, _) => //compound patern (multi-pattern)
  }
  //9 - if-guards
  val secondElementSpecial = aList match {
    case Cons(_, Cons(specialElement, _)) if specialElement % 2 == 0 =>
  }

  //ALL
  /*
  Question.
   */
  val numbers = List(1,2,3)
  val numbersMatch = numbers match {
    case listOfstrings: List[String] => "a list of strings"
    case listOfnumbers: List[Int] => "a list of numers"
    case _ => ""
  }

  println(numbersMatch)
  //JVM trick question
}
