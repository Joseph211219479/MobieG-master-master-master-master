package models

import people.FacilitatorType
import play.api.libs.json.Json

/**
 * Created by akhona on 2014/10/09.
 */
case class FacilitatorTypeModel(id:Long, name:String, description:String, facilitatorId:Long)
{
  def getDomain(): FacilitatorType = FacilitatorTypeModel.domain(this)
}

object FacilitatorTypeModel{
  implicit val factypefmt = Json.format[FacilitatorTypeModel]

  def domain(model: FacilitatorTypeModel) = {
    FacilitatorType(model.id, model.name, model.description, model.facilitatorId)
  }
}