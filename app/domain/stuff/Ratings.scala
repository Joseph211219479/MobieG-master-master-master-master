package domain.stuff

import play.api.libs.json.Json

/**
 * Created by alex on 2014/09/02.
 */

case class Ratings (id:Long,rate:Int,comment:String, facilitatorId:Long)

object Ratingsr{
  implicit lazy val ratingsfmt = Json.format[Ratings]
}
