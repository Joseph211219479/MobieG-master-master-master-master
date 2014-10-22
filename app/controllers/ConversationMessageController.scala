package controllers

import domain.stuff.ConversationMessage
import models.ConversationMessageModel
import play.api.libs.json.Json
import play.api.mvc._
import services.crudservices.ConversationMessageCRUDInterface
import services.crudservices.Impl.ConversationMessageCRUD
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by joseph on 2014/10/22.
 */
object ConversationMessageController extends Controller{
  implicit val adminWrites = Json.writes[ConversationMessage]

  def create( ConversationMessage: String) = Action.async(parse.json)
  {
    request =>
      val input = request.body

      //val income = (input \ "object").as[String]
      //val json = Json.parse(income)
      val adminModel = Json.fromJson[ConversationMessageModel](input).get
      val admin = adminModel.getDomain()
      val adminObj = ConversationMessageModel(admin.id, admin.message, admin.conversationId, admin.memberId, admin.facilitatorId).getDomain()
      val adm: ConversationMessageCRUDInterface = new ConversationMessageCRUD
      val res = adm.create(adminObj)
      val other = admin.copy(id = admin.id)
      val results: Future[ConversationMessage] = Future{res}
      results.map(result => Ok(Json.toJson(result)))
  }

  def update( ConversationMessage: String ) = Action.async(parse.json)
  {
    request =>
      val input = request.body
      //val income = (input \ "object").as[String]
      //val json = Json.parse(income)
      val chanModel = Json.fromJson[ConversationMessageModel](input).get
      //val chanzoModel = Json.fromJson[FacilitatorModel](input).get
      val admin = chanModel.getDomain
      //val chanzo = chanzoModel.getDomain()
      val adminObj = ConversationMessageModel(admin.id, admin.message, admin.conversationId, admin.memberId, admin.facilitatorId).getDomain()
      val adm: ConversationMessageCRUDInterface = new ConversationMessageCRUD
      val res = adm.update(adminObj.message, adminObj.id)
      val results: Future[String] = Future{res.toString}
      results.map(result => Ok(Json.toJson(result)))
  }

  def delete(id:Long) = Action{

    val obj: ConversationMessageCRUDInterface = new ConversationMessageCRUD
    val res = obj.delete(id)
    Ok("Deleted")
  }

  def read(id: Long) = Action
  {
    val obj: ConversationMessageCRUDInterface = new ConversationMessageCRUD
    val res = obj.read(id)
    val json = Json.toJson(res)
    Ok(json)
  }
}
