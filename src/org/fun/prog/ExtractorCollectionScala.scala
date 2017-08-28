package org.fun.prog

import scala.util.matching.Regex


object ExtractorCollectionScala extends App {

  println(matchMapKey(Map("name" -> "J Smith")))
  println(matchMapKey(Map("age" -> 18)))

  describe(Nil) === "Empty"
  describe("A" :: Nil) === "A"
  describe("A" :: "B" :: Nil) === "A and B"
  describe("A" :: "B" :: "C" :: Nil) === "A, B and 1 more"

  def describe(list: List[Any]) = {
    list match {
      case Nil => "Empty"
      case head :: Nil => s"$head"
      case head1 :: head2 :: Nil => s"$head1 and $head2"
      case head1 :: head2 :: tail => s"$head1, $head2 and ${tail.size} more"
    }
  }

  implicit class StringHelper(value: String) {
    def ===(testValue: String) = assert(value == testValue)
  }

  applyOp("not", List(true, true)) === List(false, true)
  applyOp("and", List(true, true)) === List(true)

  def applyOp(op: String, stack: List[Boolean]) = {
    // Match multiple parameters at once
    (op, stack) match {
      case ("not", arg1 :: tail) => !arg1 :: tail
      case ("and", arg1 :: arg2 :: tail) => (arg1 && arg2) :: tail
      case ("or",  arg1 :: arg2 :: tail) => (arg1 || arg2) :: tail
    }
  }

  extractContact("\"J Smith\" <j@mail.ie>") === "J Smith at j@mail.ie"

  def extractContact(value: String) = {

    val emailRegEx = """"([ \w]+)" <([.\w]+@[.\w]+)>""".r

    value match {
      case emailRegEx(name, email) => s"$name at $email"
    }
  }

  implicit class ListHelper[T](value: List[T]) {
    def ===(testValue: List[T]) = assert(value == testValue)
  }

  def matchMapKey(map: Map[String, Any]) = {
    map match {
      //case ValueExtractor("name")(value) => value
      case mapKey"name${value}" => value
      case mapKey"age${value}" => value
    }
  }

  implicit class StringContextExtension(sc: StringContext) {
    def mapKey = MapExtractor(sc.parts.mkString)
  }

  case class MapExtractor(key: String) {

    def unapply(map: Map[String, Any]) = {
      map.get(key)
    }
  }

  case class ValueExtractor(key: String, value: Any) {
    def unapply(map: Map[String, Any]) = {
      map.get(key)
    }
  }

}
