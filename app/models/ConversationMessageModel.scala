package models

import domain.stuff.ConversationMessage
import play.api.libs.json.Json

/**
 * Created by d on 2014/10/11.
 */
case class ConversationMessageModel(id:Long, message:String, conversationId:Long, memberId:Long, facilitatorId:Long)
{
    def getDomain() : ConversationMessage = ConversationMessageModel.domain(this)
}

object ConversationMessageModel
{
  implicit val confmt = Json.format[ConversationMessageModel]

  def domain(model : ConversationMessageModel) =
  {
    ConversationMessage(model.id,model.message, model.conversationId, model.memberId, model.facilitatorId)
  }
}
