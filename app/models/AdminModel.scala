package models

import people.Admin
import play.api.libs.json.Json

/**
 * Created by akhona on 2014/10/07.
 */
case class AdminModel(id: Long) {
      def getDomain(): Admin = AdminModel.domain(this)
}

object AdminModel{
  implicit val facilitatorfmt = Json.format[AdminModel]

  def domain(model: AdminModel) = {
    Admin(model.id)
  }
}
