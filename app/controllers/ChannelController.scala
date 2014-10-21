package controllers

import domain.people.Facilitator
import domain.stuff.Channel
import models.{FacilitatorModel, ChannelModel}
import services.crudservices.Impl.ChannelCRUD
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import play.api.libs.json.Json
import play.api.mvc._
import repository.ChannelRepository.ChannelRepository
import services.ChannelService
import services.crudservices.ChannelCRUDInterface
import services.impl.ChannelServiceImpl



/**
 * Created by akhona on 2014/10/01.
 */
object ChannelController extends Controller{
  implicit val adminWrites = Json.writes[Channel]
  implicit val facsWrites = Json.writes[Facilitator]


  def create( Channel: String ) = Action.async(parse.json )
  {
    request =>
      val input = request.body

      val income = (input \ "object").as[String]
      val incomeF = (input \ "facobject").as[String]
      val json = Json.parse(income)
      val jsonF = Json.parse(incomeF)
      val chanModel = Json.fromJson[ChannelModel](json).get
      val chanzoModel = Json.fromJson[FacilitatorModel](jsonF).get
      val admin = chanModel.getDomain()
      val chanzo = chanzoModel.getDomain()
      val chanObj = ChannelModel(admin.id,admin.name,admin.description,admin.facilitatorId).getDomain()
      val facObj = FacilitatorModel(chanzo.id).getDomain()
      val obj: ChannelCRUDInterface = new ChannelCRUD
      val res = obj.create(chanObj, facObj)
      val other = admin.copy(id = admin.id)
      val otherz = admin.copy(id = chanzo.id)
      val results: Future[Channel] = Future{res}
      results.map(resu => Ok(Json.toJson(resu)))
  }

  def update( Channel: String ) = Action.async(parse.json)
  {
    request =>
      val input = request.body
      val income = (input \ "object").as[String]
      //val incomeF = (input \ "facobject").as[String]
      val json = Json.parse(income)
      //val jsonF = Json.parse(incomeF)

      val chanModel = Json.fromJson[ChannelModel](json).get
      //val chanzoModel = Json.fromJson[FacilitatorModel](jsonF).get
      val admin = chanModel.getDomain()
      //val chanzo = chanzoModel.getDomain()
      val chanObj = ChannelModel(admin.id,admin.name,admin.description,admin.facilitatorId).getDomain()
      //val facObj = FacilitatorModel(chanzo.id).getDomain()
      val obj: ChannelCRUDInterface = new ChannelCRUD
      val res = obj.update(chanObj.name,chanObj.id)
      val results: Future[String] = Future{res.toString}
      results.map(result => Ok(Json.toJson(result)))
  }

  def delete(id:Long) = Action{

    val obj: ChannelCRUDInterface = new ChannelCRUD
    val res = obj.delete(id)
    Ok("Deleted")
  }

  def read(id:Long) = Action
  {
    val obj: ChannelCRUDInterface = new ChannelCRUD
    //val admin = chanModel.getDomain()
    val res = obj.read(id)
    val json = Json.toJson(res)
    Ok(json)
  }

  def getall() = Action
  {
    request =>
      val chanObj : ChannelService = new ChannelServiceImpl
      val all = chanObj.getAllChannels()
      Ok(Json.toJson(all))
  }

  def getByName(name : String)= Action
  {
    request =>
      val chanObj : ChannelService = new ChannelServiceImpl
      val all = chanObj.getChannelByName(name)
      Ok(Json.toJson(all))
  }

  def getById(id : Long) = Action
  {
    request =>
      val chanObj : ChannelService = new ChannelServiceImpl
      val all = chanObj.chooseChannel(id)
      Ok(Json.toJson(all))
  }

  def getByFacilitator(facilId : Long) = Action
  {
    request =>
      val chanObj : ChannelService = new ChannelServiceImpl
      val all = chanObj.getChanByFacilitatorId(facilId)
      Ok(Json.toJson(all))
  }
}
