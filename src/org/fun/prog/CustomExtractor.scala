package org.fun.prog

object CustomExtractor extends App {


  "Hello world" match {
    case exp"""Hello (\w+)${name}""" => println(name)

    //StringContext("""Hello (\w+)""").exp(name)

    //Regex("""Hello (\w+)""")(name)
  }

  implicit class StringContextExtension(sc: StringContext) {
    def exp = sc.parts.mkString.r
  }

}
