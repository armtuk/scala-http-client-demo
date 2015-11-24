package com.example

import dispatch._, Defaults._

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

object DemoExploded {
  val l: List[String] = List("scala", "clojure", "java", "node", "javascript", "ruby", "rails", "groovy")

  val apiUrlFormat: String = "https://www.googleapis.com/books/v1/volumes?q=%s"

  def run(queries: List[String]): List[SearchResponse] = {
    val requests: List[SearchRequest] = queries.map(x => new SearchRequest(x, apiUrlFormat.format(x)))

    val responses: List[Future[SearchResponse]] = requests
      .map(request => Http(url(request.url) OK as.JSON)
        .map(responseJson => SearchResponse(request.term, responseJson)))

    val futures: Future[List[SearchResponse]] = Future.sequence(responses)

    return await(futures, Duration.Inf)
  }

  def extractTotalItems(response: SearchResponse): Int = {
    return (response.json \ "totalItem").as[Int]
  }

  def await[T](x: Future[T], duration: Duration): T = {
    return Await.result(x, duration)
  }
}
