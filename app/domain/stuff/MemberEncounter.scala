package domain.stuff

import play.api.libs.json.Json

/**
 * Created by akhona on 2014/09/23.
 */
case class MemberEncounter(id:Long, startTime:String, endTime:String, facilitatorId:Long, memberId:Long)

object MembersEncounters{

  implicit lazy val membersencounterfmt = Json.format[MemberEncounter]
}
