package domain.people

import play.api.libs.json.Json

/**
 * Created by alex on 2014/09/02.
 */

case class Facilitator(id:Long)

object Facilitators {
  implicit lazy val facilitatorfmt = Json.format[Facilitator]

}
