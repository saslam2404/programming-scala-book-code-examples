// src/script/scala/progscala3/BracesSyntax.scala

// For completeness, Scala 2 imports, not allowed in Scala 3:
import scala.concurrent._
import java.util.{Queue => JQueue}
import java.util.{HashMap => JHashMap, HashSet => _}

// Braces syntax - valid in both Scala 2 and 3

// Package definition
// package mypkg {  // Can't declare a package in a script!
//   // stuff under mypkg...
// }

// For comprehension
val evens = for {
  i <- 0 until 10
  if i%2 == 0
} yield i

// For loop
for {
  i <- 0 until 10
  if i%2 == 0
} println(i)

if (8 < 10) {
  println(true)
} else {
  println(false)
}

// While loop
var i = 0
while (i < 10) { i+=1 }

// Match expression
0 match {
  case 0 => println("zero")
  case _ => println("other value")
}

// Partially-defined function
val o:Option[Int] => Int = {
  case Some(i) => i
  case None => 0
}

// Try, catch, finally expression
import scala.io.Source
import scala.util.control.NonFatal
var source: Option[Source] = None
try {
  source = Some(Source.fromFile("..."))
  // do something with it
} catch {
  case NonFatal(ex) => println(ex)
} finally {
  if (source != None) {
    source.get.close
  }
}

// Multiline method definition
def multiline(s: String): String = {
  println(s"input: $s")
  val result = s.toUpperCase
  println(s"output: $result")
  result
}

// Trait, class, and object definition
trait Monoid[A] {
  def add(a1: A, a2: A): A
  def zero: A
}

// Instantiate an anonymous instance
val mon = new Monoid[Int] {
  def add(i1: Int, i2: Int): Int = i1+i2
  def zero: Int = 0
}

// New type class definition
given intMonoid: Monoid[Int] with {
  def add(i1: Int, i2: Int): Int = i1+i2
  def zero: Int = 0
}

// Extension method definition
extension (s: String) {
  def bold: String = s.toUpperCase + "!"
  def meek: String = "(" + s.toLowerCase + ", maybe?)"
}
