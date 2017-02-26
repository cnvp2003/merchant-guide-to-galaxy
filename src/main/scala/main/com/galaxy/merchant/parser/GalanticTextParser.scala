package main.com.galaxy.merchant.parser

import main.com.galaxy.merchant.utils.ConversionUtils

import scala.util.parsing.combinator._

/**
  * Created by pati80 on 24/02/17.
  */
object GalanticTextParser extends RegexParsers{

  /**
    * Symbol Value
    * I       1
    * V       5
    * X       10
    * L       50
    * C       100
    * D       500
    * M       1,000
    *
    * General Declaration:
    * Numbers are formed by combining symbols together and adding the values (For example, MMVI is 1000 + 1000 + 5 + 1 = 2006)
    * Generally, symbols are placed in order of value, starting with the largest values
    * When smaller values precede larger values, the smaller values are subtracted from the larger values, and the result is added to the total
    * (For example MCMXLIV = 1000 + (1000 − 100) + (50 − 10) + (5 − 1) = 1944)
    *
    * Rules:
    *
    * 1.The symbols "I", "X", "C", and "M" can be repeated three times in succession, but no more.
    * (They may appear four times if the third and fourth are separated by a smaller value, such as XXXIX.)
    *
    * 2."D", "L", and "V" can never be repeated.
    *
    * 3."I" can be subtracted from "V" and "X" only
    * "X" can be subtracted from "L" and "C" only
    * "C" can be subtracted from "D" and "M" only
    * "V", "L", and "D" can never be subtracted
    *
    * 4.Only one small-value symbol may be subtracted from any large-value symbol
    * 5. A number written in [16]Arabic numerals can be broken into digits. For example, 1903 is composed of 1, 9, 0, and 3. Therefore, 1903 = MCMIII.
    *
    *
    * Solution:
    * Understanding
    *
    * 1. Assgin Galantic to Roman
    * 2. convert Galatic to Roman
    * 3. Convert Roman to Arabic
    * 4. Decide/Assign metal credits(Arabic)
    * 5. calculate galactics to arabic
    * 6. calculate cerdits per item
    * 7. formulate output
    *
    */

  /** Error on wrong input */
  val ERROR = "I have no idea what you are talking about\n"

  /** patterns */
  def galaticPattern = """([a-z]+)""".r

  def metalPattern = """([A-Z][a-z]+)""".r

  def numberPattern = """\d+""".r

  def roman: Parser[String] = "I" | "V" | "X" | "L" | "C" | "D" | "M"

  def romanToArabic(roman: String): Int = roman match {
    case "I" => 1
    case "V" => 5
    case "X" => 10
    case "L" => 50
    case "C" => 100
    case "D" => 500
    case "M" => 1000
  }

  def process(input: String): String = parseAll(sentence, input) match {
    case Success(result, _) => result
    case failure: NoSuccess => {
      ERROR
    }
  }

  def sentence: Parser[String] ={
    assumption | metalValue | cost | value
  }


  def assumption: Parser[String] = galaticPattern ~ "is" ~ roman ^^ { case g ~ _ ~ r => ConversionUtils.assign(g, r)}

  def metalValue: Parser[String] = rep(galaticPattern) ~ metalPattern ~ "is" ~ numberPattern ~ "Credits" ^^ { case rg ~ i ~ _ ~ a ~ _ => ConversionUtils.value(rg, i, a)}

  def cost: Parser[String] = "how" ~ "many" ~ "Credits" ~ "is" ~ rep(galaticPattern) ~ metalPattern ~ "?" ^^ { case _ ~ _ ~ _ ~ _ ~ rg ~ i ~ _ =>
    println(s"${ConversionUtils.calculateCredits(rg, i)}")
      ""
  }

  def value: Parser[String] = "how" ~ "much" ~ "is" ~ rep(galaticPattern) ~ "?" ^^ { case _ ~ _ ~ _ ~ rg ~ _ =>
    println(s"${ConversionUtils.calculateUnits(rg)}")
      ""
  }

}