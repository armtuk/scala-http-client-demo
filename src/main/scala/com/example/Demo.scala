package com.example

import dispatch._, Defaults._

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

object Demo {
  val l = List("scala", "clojure", "java", "node", "javascript", "ruby", "rails", "groovy")

  def main(args: Array[String]) = await { run(l).map(_.map(x => (x.term, extract(x)))) }

  val extractTotalItems: SearchResponse => Int =
    response => (response.json \ "totalItems").as[Int]

  val apiUrlFormat = "https://www.googleapis.com/books/v1/volumes?q=%s"

  def run(queries: List[String]) =
    Future.sequence(queries.map(searchRequest).map(execSearch))

  def extract(response: SearchResponse) = extractTotalItems(response)

  def execSearch(request: SearchRequest) =
    Http(url(request.url) OK as.JSON).map(x => SearchResponse(request.term, x))

  def await[T](x: Future[T])(implicit duration: Duration = Duration.Inf) = Await.result(x, duration)

  def searchRequest(term: String) = new SearchRequest(term, apiUrlFormat.format(term))
}
