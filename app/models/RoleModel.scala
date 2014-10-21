package models

import people.Role
import repository.RoleRepository.RoleRepository
import java.util.UUID
import play.api.libs.json.Json
/**
 * Created by joseph on 2014/10/07.
 */
case class RoleModel (id :Long ,role : String, description : String)
{
  def getDomain() : Role = RoleModel.domain(this)
}

object RoleModel
{
  implicit val roleFmt = Json.format[RoleModel]
  def domain(model: RoleModel) =
    {
      Role( model.id, model.role, model.description)
    }
  }