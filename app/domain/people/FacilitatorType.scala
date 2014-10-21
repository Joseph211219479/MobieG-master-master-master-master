package people

import play.api.libs.json.Json

/**
 * Created by alex on 2014/09/02.
 */
case class FacilitatorType(id:Long, name:String, description:String, facilitatorId:Long)

object FacilitatorTypes{
  implicit lazy val facilitatortypefmt = Json.format[FacilitatorType]
}
