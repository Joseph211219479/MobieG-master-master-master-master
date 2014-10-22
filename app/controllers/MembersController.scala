package controllers

/**
 * Created by akhona on 2014/10/10.
 */

import controllers.MembersEncounterController._
import domain.people.Facilitator
import models.FacilitatorModel
import models.crudmodels.MembersModel
import people.Members
import play.api.libs.json.Json
import play.api.mvc._
import services.MembersService
import services.crudservices.Impl.MembersCRUD
import services.crudservices.MembersCRUDInterface
import services.impl.MemberServiceImpl
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object MembersController extends Controller{
  implicit val adminWrites = Json.writes[Members]
  implicit val facsWrites = Json.writes[Facilitator]

  def create( Members: String ) = Action.async(parse.json)
  {
    request =>
      val input = request.body

      val income = (input \ "object").as[String]
      val incomeF = (input \ "facobject").as[String]
      val json = Json.parse(income)
      val jsonF = Json.parse(incomeF)

      val chanModel = Json.fromJson[MembersModel](json).get
      val chanzoModel = Json.fromJson[FacilitatorModel](jsonF).get
      val admin = chanModel.getDomain
      val chanzo = chanzoModel.getDomain()

      val memObj = MembersModel(admin.id,admin.facilitatorId).getDomain
      val facObj = FacilitatorModel(chanzo.id).getDomain()

      val obj: MembersCRUDInterface = new MembersCRUD
      val res = obj.create(memObj, facObj)
      val other = admin.copy(id = admin.id)
      val otherz = chanzo.copy(id = chanzo.id)
      val results: Future[Members] = Future{res}
      results.map(resu => Ok(Json.toJson(resu)))
  }

 def update( Members: String ) = Action.async(parse.json)
  {
    request =>
      val input = request.body
      val income = (input \ "object").as[String]
      val json = Json.parse(income)
      val chanModel = Json.fromJson[MembersModel](json).get
      //val chanzoModel = Json.fromJson[FacilitatorModel](input).get
      val admin = chanModel.getDomain
      //val chanzo = chanzoModel.getDomain()
      val memObj = MembersModel(admin.id, admin.facilitatorId).getDomain
      val obj: MembersCRUDInterface = new MembersCRUD
      val res = obj.update(memObj.facilitatorId, memObj.id)
      val results: Future[String] = Future{res.toString}
      results.map(result => Ok(Json.toJson(result)))
  }

  def delete(id:Long) = Action{

    val obj: MembersCRUDInterface = new MembersCRUD
    val res = obj.delete(id)
    Ok("Deleted")
  }

  def read(id: Long) = Action
  {
    val obj: MembersCRUDInterface = new MembersCRUD
    //val admin = chanModel.getDomain()
    val res = obj.read(id)
    val json = Json.toJson(res)
    Ok(json)
  }

  /////////////////////////////////////////////////

  def getAllMembers() = Action
  {
    request =>
      val obj : MembersService = new MemberServiceImpl
      val list = obj.getAllMembers()
      Ok(Json.toJson(list))
  }

  def getMemberByID(id : Long) = Action
  {
    request =>
      val obj : MembersService = new MemberServiceImpl
      val list = obj.getMemberByID(id)
      Ok(Json.toJson(list))
  }

  //problem with that ficilitator id to compare two Lists

//  def getConversationHistory(idMember : Long  ) = Action
//  {
//    request =>
//      val obj : MembersService = new MemberServiceImpl
//      val list = obj.(idMember)
//      Ok(Json.toJson(list))
//  }

//  def getLastServedMember(idMember : Long  ) = Action
//  {
//    request =>
//      val obj : MembersService = new MemberServiceImpl
//      val list = obj.getLastServedMember(idMember)
//      Ok(Json.toJson(list))
//  }
}
