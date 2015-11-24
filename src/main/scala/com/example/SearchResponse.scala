package com.example

import play.api.libs.json.JsValue

import scala.concurrent.Future

case class SearchResponse(term: String, json: JsValue) {
  def jsonValue: JsValue = {
    return json
  }

  def foo: Future[List[SearchResponse]] =
    Demo.run(List("foo", "bar"))
}
