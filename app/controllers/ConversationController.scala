package controllers

/**
 * Created by akhona on 2014/10/02.
 */


import domain.people.Facilitator
import domain.stuff.{Conversation, ConversationMessage}
import models.FacilitatorModel
import models.crudmodels.ConversationModel
import play.api.libs.json.Json
import play.api.mvc._
import repository.ConversationMessageRepository.ConversationMessageRepository
import services.ConversationService
import services.crudservices.ConversationCRUDInterface
import services.crudservices.Impl.ConversationCRUD
import services.impl.ConversationServiceImpl
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object ConversationController extends Controller{
  implicit val convoWrites = Json.writes[Conversation]


  def create( Conversation: String ) = Action.async(parse.json)
  {
    request =>
      val input = request.body

      val income = (input \ "object").as[String]
      val incomeF = (input \ "facobject").as[String]
      val json = Json.parse(income)
      val jsonF = Json.parse(incomeF)
      val chanModel = Json.fromJson[ConversationModel](json).get
      val chanzoModel = Json.fromJson[FacilitatorModel](jsonF).get
      val admin = chanModel.getDomain()
      val chanzo = chanzoModel.getDomain()
      val convObj = ConversationModel(admin.id,admin.message,admin.facilitatorId).getDomain()
      val facObj = FacilitatorModel(chanzo.id).getDomain()
      val obj: ConversationCRUDInterface = new ConversationCRUD
      val res = obj.create(chanzo, admin)
      val other = admin.copy(id = admin.id)
      val otherz = chanzo.copy(id = chanzo.id)
      val results: Future[Conversation] = Future{res}
      results.map(resu => Ok(Json.toJson(resu)))
  }

  def update( Conversation: String  ) = Action.async(parse.json)
  {
    request =>
      val input = request.body
      val income = (input \ "object").as[String]
      val json = Json.parse(income)
      val chanModel = Json.fromJson[ConversationModel](json).get
      //val chanzoModel = Json.fromJson[FacilitatorModel](input).get
      val admin = chanModel.getDomain()
      //val chanzo = chanzoModel.getDomain()
      val convObj = ConversationModel(admin.id,admin.message,admin.facilitatorId).getDomain()
      //val facObj = FacilitatorModel(chanzo.id).getDomain()
      val obj: ConversationCRUDInterface = new ConversationCRUD
      val res = obj.update(convObj.message,convObj.id)

      val results: Future[String] = Future{res.toString}
      results.map(result => Ok(Json.toJson(result)))
  }

  def delete(id:Long) = Action
  {
    val obj: ConversationCRUDInterface = new ConversationCRUD
    val res = obj.delete(id)
    Ok("Deleted")
  }

  def read(id: Long) = Action
  {
    val obj: ConversationCRUDInterface = new ConversationCRUD
    //val admin = chanModel.getDomain()
    val res = obj.read(id)
    val json = Json.toJson(res)
    Ok(json)
  }
}
