package com.example

import org.scalatest.{Matchers, FlatSpec}

import Demo._
import play.api.libs.json.{JsResultException, Json}

class DemoSpec extends FlatSpec with Matchers {
  "Demo" should "retrieve a total item count for a search term " in {
    val r = await(execSearch(searchRequest("java")))

    r.term should be ("java")
    (r.json \ "totalItems").as[Int] should be >(100)
  }

  it should "extract totalItems from a sample Json structure" in {
    val k = SearchResponse("test", Json.obj("kind" -> "books#volumes", "totalItems" -> 929, "items" -> Json.arr()))

    val r = extractTotalItems(k)

    r should be 929
  }

  it should "Throw an exeption given bad json" in {
    a [JsResultException] should be thrownBy {
      extractTotalItems(SearchResponse("none", Json.obj()))
    }

    MySingleton.k

    MySingleton.j
  }
}

object MySingleton {
  val one = "1"

  def k = MySingleton

  def j() = MySingleton
}
