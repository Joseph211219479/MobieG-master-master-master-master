package models.crudmodels

import people.Members
import play.api.libs.json.Json

/**
 * Created by alex on 2014/10/08.
 */
case class MembersModel(id:Long, facilitatorId:Long) {
      def getDomain: Members = MembersModel.domain(this)
}

object MembersModel{
  implicit val membersfmt = Json.format[MembersModel]

  def domain(model: MembersModel) ={
    Members(model.id, model.facilitatorId)
  }
}
