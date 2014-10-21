package controllers

/**
 * Created by akhona on 2014/10/20.
 */

import domain.stuff.MemberEncounter
import models.FacilitatorModel
import models.crudmodels.MemberEncounterModel
import play.api.libs.json.Json
import play.api.mvc._
import services.MembersService
import services.crudservices.Impl.MembersEncounterCRUD
import services.crudservices.MembersEncounterCRUDint
import services.impl.MemberServiceImpl
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global


object MembersEncounterController extends Controller{
  implicit val adminWrites = Json.writes[MemberEncounter]

  def create( MembersEncounter: String) = Action.async(parse.json)
  {
    request =>
      val input = request.body

      val income = (input \ "object").as[String]
      val json = Json.parse(income)
      val adminModel = Json.fromJson[MemberEncounterModel](json).get
      val admin = adminModel.getDomain()
      val adminObj = MemberEncounterModel(admin.id,admin.startTime,admin.endTime,admin.memberId,admin.facilitatorId).getDomain()
      val adm: MembersEncounterCRUDint = new MembersEncounterCRUD
      val res = adm.create(adminObj)
      val other = admin.copy(id = admin.id)
      val results: Future[MemberEncounter] = Future{res}
      results.map(result => Ok(Json.toJson(result)))
  }
  def update( MembersEncounter: String ) = Action.async(parse.json)
  {
    request =>
      val input = request.body
      val income = (input \ "object").as[String]
      val json = Json.parse(income)
      val chanModel = Json.fromJson[MemberEncounterModel](json).get
      val chanzoModel = Json.fromJson[FacilitatorModel](input).get
      val admin = chanModel.getDomain
      val chanzo = chanzoModel.getDomain()
      val adminObj = MemberEncounterModel(admin.id,admin.startTime,admin.endTime,admin.memberId,admin.facilitatorId).getDomain()
      val obj: MembersEncounterCRUDint = new MembersEncounterCRUD
      val res = obj.update(adminObj.id, adminObj.startTime)
      val results: Future[String] = Future{res.toString}
      results.map(result => Ok(Json.toJson(result)))
  }

  def delete(id:Long) = Action{

    val obj: MembersEncounterCRUDint = new MembersEncounterCRUD
    val res = obj.delete(id)
    Ok("Deleted")
  }
  def read(id: Long) = Action
  {
    val obj: MembersEncounterCRUDint = new MembersEncounterCRUD
    val res = obj.read(id)
    val json = Json.toJson(res)
    Ok(json)
  }

  /////////////////////////////////////////////////

  def getById(id : Long) = Action
  {
    request =>
      val obj: MembersEncounterCRUDint = new MembersEncounterCRUD
      val res = obj.read(id)
      val json = Json.toJson(res)
      Ok(json)
  }

}
