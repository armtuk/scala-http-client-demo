package com.example

import java.nio.charset.Charset

import com.ning.http.client
import play.api.libs.json.{Json, JsValue}

import Log._

object as {
   object JSON extends (client.Response => JsValue) {
     /** @return response body as a string decoded as either the charset provided by
       *         Content-Type header of the response or ISO-8859-1 */
     def apply(r: client.Response) = Json.parse(r.getResponseBody)

     /** @return a function that will return response body decoded in the provided charset */
     case class charset(set: Charset) extends (client.Response => String) {
       def apply(r: client.Response) = r.getResponseBody(set.name)
     }

     /** @return a function that will return response body as a utf8 decoded string */
     object utf8 extends charset(Charset.forName("utf8"))

   }
 }
