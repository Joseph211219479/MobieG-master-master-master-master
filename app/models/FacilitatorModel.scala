package models

import domain.people.Facilitator
//import models.crudmodels.{PersonModel, FacilitatorTypeModel}
import play.api.libs.json.Json

/**
 * Created by joseph on 2014/10/10.
 */
case class FacilitatorModel(id : Long)
{
  def getDomain() : Facilitator = FacilitatorModel.domain(this)
}

object FacilitatorModel
{
  implicit lazy val facilitatorfmt = Json.format[FacilitatorModel]

  def domain(model : FacilitatorModel) =
  {
    Facilitator(model.id)
  }
}
