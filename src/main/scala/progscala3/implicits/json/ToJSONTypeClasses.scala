// src/main/scala/progscala3/implicits/json/ToJSONTypeClasses.scala
package progscala3.implicits.json

import progscala3.implicits.{Address, Person}
import scala.language.implicitConversions                            // <1>

trait ToJSON:                                                        // <2>
  def toJSON(level: Int = 0): String

  val INDENTATION = "  "
  def indentation(level: Int): (String,String) =
    (INDENTATION * level, INDENTATION * (level+1))

implicit final class AddressToJSON(address: Address) extends ToJSON: // <3>
  def toJSON(level: Int = 0): String =
    val (outdent, indent) = indentation(level)
    s"""{
      |${indent}"street": "${address.street}",
      |${indent}"city":   "${address.city}"
      |$outdent}""".stripMargin

implicit final class PersonToJSON(person: Person) extends ToJSON:    // <4>
  def toJSON(level: Int = 0): String =
    val (outdent, indent) = indentation(level)
    s"""{
      |${indent}"name":    "${person.name}",
      |${indent}"address": ${person.address.toJSON(level + 1)}
      |$outdent}""".stripMargin

@main def TryJSONTypeClasses() =
  val address = Address("1 Scala Lane", "Anytown")
  val person = Person("Buck Trends", address)
  println(s"address: $address vs. ${address.toJSON(0)}")
  println(s"person: $person vs. ${person.toJSON(0)}")
