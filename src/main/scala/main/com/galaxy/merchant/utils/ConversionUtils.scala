package main.com.galaxy.merchant.utils

import main.com.galaxy.merchant.parser.ConversionRules

import scala.collection.mutable.Map
import scala.util.parsing.combinator.RegexParsers

/**
  * Created by pati80 on 24/02/17.
  */
object ConversionUtils extends RegexParsers{

  var credits = Map[String, Float]()
  var intergalacticsToRomans = Map[String, String]()

  def assign(galactic: String, roman: String): String = {
    intergalacticsToRomans(galactic) = roman
    ""
  }

  def value(galactics: List[String], item: String, value: String): String = {
    val quantity =galactic2arabic(galactics)
    val cost = value.toFloat / quantity
    credits(item) = cost
    ""
  }

  def calculateUnits(galactics: List[String]) = {
    val quantity = galactic2arabic(galactics)
    val galactic = galactics.mkString(" ")
    formatOutput("much", galactic, quantity)
  }

  def calculateCredits(galactics: List[String], metal: String) = {
    val quantity = galactic2arabic(galactics)
    val galactic = galactics.mkString(" ")
    formatOutput("many", galactic, quantity, Some(metal))
  }

  def formatOutput(question :String, galactic: String, quantity :Int, metal :Option[String]=null) = question match {
    case "much" => s"${galactic} is ${quantity}"
    case "many" => {
      val totalCredit = metal.map(x=> credits(x) * quantity)
      s"${galactic} ${metal.get} is ${totalCredit.get} Credits"
    }
  }

  def galactic2arabic(galactics: List[String]): Int = {
    val romans = galactics.foldLeft("") {
      (result, galactic) => result + ConversionUtils.intergalacticsToRomans(galactic)
    }

    ConversionRules.toArabic(romans)
  }

}