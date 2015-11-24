package com.example

case class SearchRequest(term: String, url: String)

object SearchRequest {
  val apiUrlFormat: String = "https://www.googleapis.com/books/v1/volumes?q=%s"
}
