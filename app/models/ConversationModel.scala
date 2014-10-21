package models.crudmodels

import domain.stuff.Conversation
import play.api.libs.json.Json

/**
 * Created by alex on 2014/10/08.
 */
case class ConversationModel(id:Long, message:String, facilitatorId:Long) {
      def getDomain(): Conversation = ConversationModel.domain(this)
}

object ConversationModel{
  implicit val conversationfmt = Json.format[ConversationModel]

  def domain(model: ConversationModel) ={
    Conversation(model.id, model.message, model.facilitatorId)
  }
}