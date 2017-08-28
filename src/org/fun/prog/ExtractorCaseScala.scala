package org.fun.prog

import scala.util.{Failure, Success, Try}

object ExtractorCaseScala extends App {

  processMessageWithCheck(Email("watches@mail.ie", "Watches", "Watches spam"))
  processMessageWithCheck(Email("jsmith@mail.ie", "Hi", "What's up?"))

  case class Email(
       from: String,
       title: String,
       message: String
  )

  case class Chat(
       name: String,
       message: String
  )

  def processMessage(message: Any) = {
    message match {
      case Email(sender, title, _)
           => audit("email", sender, title)

      case Chat(name, _)
           => audit("chat", name, "n/a")
    }
  }

  def processMessageWithCheck(message: Any) = {
    message match {
        
      case Email(sender, _, text) if spam(text)
           => report(sender)

      case Email(sender, title, _)
           => audit("email", sender, title)
    }
  }

  def processOption(result: Option[Int]) = {
    // result: Option[Int]
    result match {
      case Some(value) => println(s"Got value $value")
      case None => println("No value provided")
    }
  }

  def processTry(result: Try[Int]) = {
    // result: Try[Int]
    result match {
      case Success(value) => println(s"Got value $value")
      case Failure(error) => println(s"Failed to get value: $error")
    }
  }  

  def audit(msgType: String, sender: String, title: String): Unit = {
    println(s"Audit: $msgType, $sender, $title")
  }

  def spam(text: String) = text.contains("spam")

  def report(sender: String): Unit = {
    println(s"Spam: $sender")
  }

}
