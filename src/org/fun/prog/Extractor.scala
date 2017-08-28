package org.fun.prog

import java.util.regex.Pattern

import scala.collection.immutable.List

//noinspection NameBooleanParameters
object Extractor extends App {

  case class User(name: String, vip: Boolean)

  User("J Smith", true) match {
    case VipUser(name) => println(s"VIP: $name")
  }

  object VipUser {

    def unapply(user: User): Option[String] = {
      if (user.vip) Some(user.name)
      else None
    }
  }

  "http://server.com:8080" match {
    case Host(host, port) => println(s"Host: $host, port: $port")
  }

  object Host {

    def unapply(url: String): Option[(String, Int)] = {
      val regEx = """https?\://([.\w]+)\:(\d+)""".r

      url match {
        case regEx(host, port) => Some(host, port.toInt)
        case _ => None
      }
    }
  }

  "http://server.com:8080" match {
    case host :: port => println(s"Host: $host, port: $port")
    // Same as this:
    case ::(host, port) => println(s"Host: $host, port: $port")
  }

  object :: {
    def unapply(url: String): Option[(String, Int)] = {
      val regEx = """https?\://([.\w]+)\:(\d+)""".r

      url match {
        case regEx(host, port) => Some(host, port.toInt)
        case _ => None
      }
    }
  }


  val name = MyRegEx("""(\w+) (\w+)""")

  "John Smith" match {
    case name(first, last) => println(s"Hello $first $last")
  }

  case class MyRegEx(pattern: String) {

    def unapplySeq(text: String): Option[List[String]] = {
      val m = Pattern.compile(pattern).matcher(text)
      if (m.matches()) Some((1 to m.groupCount).toList map m.group)
      else None
    }
  }

}
