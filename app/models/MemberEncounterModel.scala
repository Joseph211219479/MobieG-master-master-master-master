package models.crudmodels

import domain.stuff.MemberEncounter
import play.api.libs.json.Json

/**
 * Created by alex on 2014/10/08.
 */
case class MemberEncounterModel(id:Long, startTime:String, endTime:String, facilitatorId:Long, memberId:Long){
        def getDomain(): MemberEncounter = MemberEncounterModel.domain(this)
}

object MemberEncounterModel{
  implicit val memberencounterfmt = Json.format[MemberEncounterModel]

  def domain(model: MemberEncounterModel) ={
    MemberEncounter(model.id, model.startTime, model.endTime, model.facilitatorId, model.memberId)
  }
}