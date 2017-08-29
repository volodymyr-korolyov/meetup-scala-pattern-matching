package org.fun.prog

import java.util.StringTokenizer

object CustomExtractor extends App {

  case class Header(name: String, value: String)

  // Aladdin:OpenSesame
  private val basic = Header("Authorization", "Basic QWxhZGRpbjpPcGVuU2VzYW1l")

  // { "name" : "John Doe" }
  private val jwt = Header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYW1lIjoiSm9obiBEb2UifQ.xuEv8qrfXu424LZk8bVgr9MQJUIrp1rHcPyZw_KSsds")

  assert(getPrincipal(basic) == "Aladdin")
  assert(getPrincipal(jwt) == """{"name":"John Doe"}""")

  def getPrincipal(header: Header) = {
    header match {
      case Header("Authorization",
                  "Basic" :: " " :: Base64(user :: ":" :: pass)
      ) => user

      case Header("Authorization",
                  "Bearer" :: " " :: Base64(alg) :: "." :: Base64(json) :: "." :: sign
      ) => json
    }
  }

  object Base64 {

    def unapply(text: String): Option[String] = {
      try {
        Some(decode(text))
      } catch {
        case _: Exception => None
      }
    }

    def decode(text: String) = new String(java.util.Base64.getDecoder.decode(text))
  }

  object :: {

    val separators = " .:"

    def unapply(text: String): Option[(String, String)] = {
      firstToken(text)
        .flatMap(token => Some((token, text.substring(token.length))))
    }

    def firstToken(text: String) = {
      val tokenizer = new StringTokenizer(text, separators, true)
      if (tokenizer.hasMoreTokens) Some(tokenizer.nextToken())
      else None
    }
  }
/*
  val doc = Json.obj("foo" -> "bar", "foo2" -> 123L)

  doc match {
    case json"""{ "foo" : $a, "foo2" : $b }""" => Some(a -> b)
    case _ => None
  }
  */

}
