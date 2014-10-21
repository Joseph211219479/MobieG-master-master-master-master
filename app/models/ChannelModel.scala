package models

import domain.stuff.Channel
import people.Admin
import play.api.libs.json.Json

/**
 * Created by alex on 2014/10/07.
 */

case class ChannelModel(id: Long, name: String, description: String, facilitatorId: Long){
  def getDomain(): Channel = ChannelModel.domain(this)
}

object ChannelModel {
  implicit val channelfmt = Json.format[ChannelModel]

  def domain(model: ChannelModel) = {
      Channel(model.id, model.name, model.description, model.facilitatorId)
  }
}
