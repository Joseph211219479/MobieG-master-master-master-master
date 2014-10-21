package domain.stuff

import play.api.libs.json.Json

/**
 * Created by alex on 2014/09/02.
 */

case class Speciality(id:Long, specialityName:String, specialityDescription:String, facilitatorId:Long)

object Specialitys{
  implicit lazy val specialityfmt = Json.format[Speciality]
}
