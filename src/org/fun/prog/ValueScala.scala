package org.fun.prog

object ValueScala extends App {

  println(intToString(5))

  println(anyToString(false))
  println(anyToString(1))
  println(anyToString("Many"))

  def intToString(value: Int) = {
    // Number to string
    value match {
      case 1 => "One"
      case 2 => "Two"
      case _ => "Many"
    }
  }

  def intToStringMulti(value: Int) = {
    // Number to string
    value match {
      case 1 => "One"
      case 2 => "Two"
      case 3 | 4 | 5 => "Many"
    }
  }

  def anyToString(value: Any) = {
    // Any to string
    value match {
      case false => "Zero"
      case 1 => "One"
      case "Many" => "Infinity"
    }
  }

  def anyToInt(value: Any) = {
    // Any to number
    value match {
      case number: Int => number
      case text: String => text.toInt
    }
  }
}
