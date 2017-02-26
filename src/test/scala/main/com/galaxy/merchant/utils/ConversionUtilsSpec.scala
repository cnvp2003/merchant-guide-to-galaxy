package main.com.galaxy.merchant.utils

import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by pati80 on 25/02/17.
  */
class ConversionUtilsSpec extends FlatSpec with Matchers {

  "calculateUnits" should "return empty list" in {
    val question = List("pish", "tegj", "glob", "glob")
    ConversionUtils.calculateUnits(question) shouldBe()
  }
}
 /*
  calculateCredits

  formatOutput
}*/
