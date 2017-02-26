package main.com.galaxy.merchant.parser

import scala.collection.mutable.LinkedHashMap
import scala.util.control.Breaks._

object ConversionRules {

  /**
   * toArabic converts a number in Roman format to Arabic format.
   *
   * It iterates from the start of the Roman string, attempting to match
   * against the valid combinations contained in Decodes. It throws an Exception
   * if an invalid Roman number is detected.
   */
  def toArabic(in: String): Integer = {

    /**
     * Decodes is a mapping from Roman digits sequences to Array. Array fields:
     *   0: integer value of this roman sequence eg 1000 for m
     *   1: max number of times this sequence can appear eg 3 for m
     * That is, Decodes is:
     *   roman => [value, max_times]
     */
    val VALUE = 0      // field index
    val MAXTIMES = 1   // field index
    var Decodes = LinkedHashMap[String, Array[Int]]()
    Decodes("m") = Array(1000, 3)
    Decodes("cm") = Array(900, 1)
    Decodes("d") = Array(500, 1)
    Decodes("cd") = Array(400, 1)
    Decodes("c") = Array(100, 3)
    Decodes("xc") = Array(90, 1)
    Decodes("l") = Array(50, 1)
    Decodes("xl") = Array(40, 1)
    Decodes("x") = Array(10, 3)
    Decodes("ix") = Array(9, 1)
    Decodes("v") = Array(5, 1)
    Decodes("iv") = Array(4, 1)
    Decodes("i") = Array(1, 3)

    val input = in.toLowerCase()  // input to iterate over
    var result = 0                // result to return
    var remainder = input         // truncated remainder of input
    var previous: String = ""     // the previous Roman match

    for ((key, array) <- Decodes) {
      breakable {
        var count = 0             // how often this key has been seen
        while (remainder.startsWith(key)) {
          if (badFour(previous, key)) {
            throw new Exception("invalid Roman number: %s" format input)
          }
          previous = key
          remainder = remainder.stripPrefix(key)
          result += array(VALUE)
          count += 1
          if (count >= array(MAXTIMES)) { break }
        }
      }
    }

    if (remainder.length() > 0) {
      throw new Exception("invalid Roman number: %s" format input)
    }

    result
  }

  /**
   * badFour returns true if a "4" sequence is followed by the next
   * lower digit - invalid.
   *
   * For example "CD" (400) is invalid if followed by a "C" (100).
   */
  def badFour(prev: String, cur: String): Boolean = (prev, cur) match {
    case ("cd", "c") => true
    case ("xl", "x") => true
    case ("iv", "i") => true
    case _ => false
  }
}