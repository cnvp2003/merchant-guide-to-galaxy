package main.com.galaxy.merchant.parser

import scala.util.parsing.combinator.RegexParsers

/**
  * Created by pati80 on 24/02/17.
  */
object SentencePatterns extends RegexParsers {

 /* def sentence: Parser[String] ={
    println(s"SE")
    assignment | credit | much | many
  }*/


/*
  /** types */
  def assignment = galactic ~ "is" ~ roman ^^ { case g ~ _ ~ r =>
    println(s"Addd")
    ConversionUtils.assignmentFn(g, r)}

  def credit = rep(galactic) ~ item ~ "is" ~ arabic ~ "Credits" ^^ { case rg ~ i ~ _ ~ a ~ _ =>
    println(s"credit")
    ConversionUtils.costFn(rg, i, a)}

  def much = "how" ~ "many" ~ "Credits" ~ "is" ~ rep(galactic) ~ item ~ "?" ^^ { case _ ~ _ ~ _ ~ _ ~ rg ~ i ~ _ =>
    println(s"much")
    ConversionUtils.manyqFn(rg, i)}

  def many = "how" ~ "much" ~ "is" ~ rep(galactic) ~ "?" ^^ { case _ ~ _ ~ _ ~ rg ~ _ => ConversionUtils.muchqFn(rg)}
*/


/*
  /** Tokens */
  def galactic: Parser[String] = """([a-z]+)""".r

  def roman: Parser[String] = "I" | "V" | "X" | "L" | "C" | "D" | "M"

  def item: Parser[String] = """([A-Z][a-z]+)""".r

  def arabic: Parser[String] = """\d+""".r*/
}
