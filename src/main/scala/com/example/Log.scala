package com.example

object Log {
  class LogWrapper[T](original: T) {
    def log[Y](msg: String, f: T => Y): T = {
      println(msg + ": " + f(original).toString)

      original
    }
  }

  implicit def anyToLog[T](original: T): LogWrapper[T] = new LogWrapper(original)
}
