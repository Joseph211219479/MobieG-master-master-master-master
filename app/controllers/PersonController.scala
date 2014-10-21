package controllers

/**
 * Created by akhona on 2014/10/10.
 */

import domain.people.Facilitator
import models.{AdminModel, FacilitatorModel}
import models.crudmodels.{MembersModel, PersonModel}
import people.{Admin, Members, Person}
import play.api.libs.json.Json
import play.api.mvc._
import services.PersonServices
import services.crudservices.Impl.PersonCRUD
import services.crudservices.PersonCRUDInterface
import services.impl.PersonServiceImpl
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object PersonController extends Controller{
  implicit val personWrites = Json.writes[Person]
  implicit val facsWrites = Json.writes[Facilitator]
  implicit val adminWrites = Json.writes[Admin]
  implicit val memberWrites = Json.writes[Members]

  def create( Person: String ) = Action.async(parse.json)
  {
    request =>
      val input = request.body

      val income = (input \ "object").as[String]
      val incomeA = (input \ "admobject").as[String]
      val incomeF = (input \ "facobject").as[String]
      val incomeM = (input \ "memobject").as[String]

      val json = Json.parse(income)
      val jsonA = Json.parse(incomeA)
      val jsonF = Json.parse(incomeF)
      val jsonM = Json.parse(incomeM)

      val chanModel = Json.fromJson[PersonModel](json).get
      val chanzoModel = Json.fromJson[FacilitatorModel](jsonF).get
      val othr = Json.fromJson[AdminModel](jsonA).get
      val thrz = Json.fromJson[MembersModel](jsonM).get
      val admin = chanModel.getDomain
      val chanzo = chanzoModel.getDomain()
      val one = othr.getDomain()
      val two = thrz.getDomain

      val persObj = PersonModel(admin.id,admin.title,admin.firstname,admin.surname,admin.othername,admin.username,admin.password,admin.email,admin.adminId,admin.facilitatorId,admin.membersId).getDomain()
      val facObj = FacilitatorModel(chanzo.id).getDomain()
      val admObj = AdminModel(one.id).getDomain()
      val memObj = MembersModel(two.id,two.facilitatorId).getDomain
      val obj: PersonCRUDInterface = new PersonCRUD
      val res = obj.create(chanzo, two, one, admin)
      val other = admin.copy(id = admin.id)
      val othera = chanzo.copy(id = chanzo.id)
      val otherb = one.copy(id = one.id)
      val otherc = two.copy(id = two.id)
      val results: Future[Person] = Future{res}
      results.map(resu => Ok(Json.toJson(resu)))
  }

  def update( Person: String ) = Action.async(parse.json)
  {
    request =>
      val input = request.body
      val income = (input \ "object").as[String]
      val json = Json.parse(income)
      val chanModel = Json.fromJson[PersonModel](json).get
      val chanzoModel = Json.fromJson[FacilitatorModel](input).get
      val admin = chanModel.getDomain()
      //val chanzo = chanzoModel.getDomain()
      val persObj = PersonModel(admin.id,admin.title,admin.firstname,admin.surname,admin.othername,admin.username,admin.password,admin.email,admin.adminId,admin.facilitatorId,admin.membersId).getDomain()
      val obj: PersonCRUDInterface = new PersonCRUD
      val res = obj.update(persObj.firstname,persObj.id)
      val results: Future[String] = Future{res.toString}
      results.map(result => Ok(Json.toJson(result)))
  }

  def delete(id:Long) = Action{

    val obj: PersonCRUDInterface = new PersonCRUD
    val res = obj.delete(id)
    Ok("Deleted")
  }

  def read(id: Long) = Action
  {
    val obj: PersonCRUDInterface = new PersonCRUD
    //val admin = chanModel.getDomain()
    val res = obj.read(id)
    val json = Json.toJson(res)
    Ok(json)
  }

//////////////////////////////////////////////////////////////////

  def getall() = Action
  {
    request =>
      val personObj : PersonServices= new  PersonServiceImpl
      val list = personObj.getAllPeople()
      Ok(Json.toJson(list))
  }

  def getByName(name : String ) = Action
  {
    request =>
      val personObj : PersonServices= new  PersonServiceImpl
      val list = personObj.getPersonByName(name)
      Ok(Json.toJson(list))
  }

  def getByUsername(username : String) = Action
  {
    request =>
      val personObj : PersonServices= new  PersonServiceImpl
      val list = personObj.getByUsername(username)
      Ok(Json.toJson(list))
  }

  def getByEmail(email: String) = Action
  {
    request =>
      val personObj : PersonServices= new  PersonServiceImpl
      val list = personObj.getByEmail(email)
      Ok(Json.toJson(list))
  }

  def getPersonByFacilId(facilId : Long) = Action
  {
    request =>
      val personObj : PersonServices= new  PersonServiceImpl
      val list = personObj.getPersonByFacilId(facilId)
      Ok(Json.toJson(list))
  }

}
