package domain.stuff

import play.api.libs.json.Json

/**
 * Created by akhona on 2014/09/25.
 */
case class ConversationMessage (id:Long, message:String, conversationId:Long, memberId:Long, facilitatorId:Long)

object ConversationMessages{
  implicit lazy val conversationmessagefmt = Json.format[ConversationMessage]
}