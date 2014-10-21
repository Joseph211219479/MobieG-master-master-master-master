package domain.stuff

import play.api.libs.json.Json

/**
 * Created by alex on 2014/09/02.
 */
case class Sponsor
(
  id:Long,
 name:String,
 url:String,
 message:String,
 imageId:String,
 channelId:Long
  )

object Sponsors{
  implicit lazy val sponsorfmt = Json.format[Sponsor]
}
