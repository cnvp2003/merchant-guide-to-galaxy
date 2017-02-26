package main.com.galaxy.merchant.test

import main.com.galaxy.merchant.parser.GalanticTextParser

import scala.io.Source

/**
  * Created by pati80 on 24/02/17.
  */

object Application {
  def main(args: Array[String]) = {

    val input = Source.fromFile("Test input.txt")
    for (line <- input.getLines) {
      GalanticTextParser.process(line)
    }
  }
}
