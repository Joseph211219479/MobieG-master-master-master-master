package controllers

/**
 * Created by akhona on 2014/10/14.
 */

import domain.people.Facilitator
import domain.stuff.Speciality
import models.FacilitatorModel
import models.crudmodels.SpecialityModel
import services.crudservices.Impl.SpecialityCRUD
import services.crudservices.SpecialityCRUDInterface

import scala.concurrent.Future
import play.api.libs.json.Json
import play.api.mvc._
import scala.concurrent.ExecutionContext.Implicits.global

object SpecialityController extends Controller{
  implicit val roleWrites = Json.writes[Speciality]
  implicit val adminWrites = Json.writes[Facilitator]

  def create( Speciality: String ) = Action.async(parse.json)
  {
    request =>
      val input = request.body
      val income = (input \ "object").as[String]
      val incomeF = (input \ "facobject").as[String]
      val json = Json.parse(income)
      val jsonF = Json.parse(incomeF)

      val chanModel = Json.fromJson[SpecialityModel](json).get
      val admin = chanModel.getDomain()
      val chanzoModel = Json.fromJson[FacilitatorModel](jsonF).get
      val chanzo = chanzoModel.getDomain()

      val specsObj = SpecialityModel(admin.id,admin.specialityName,admin.specialityDescription,admin.facilitatorId).getDomain()
      val facsObj = FacilitatorModel(chanzo.id).getDomain()
      val obj: SpecialityCRUDInterface = new SpecialityCRUD
      val res = obj.create(facsObj, specsObj)
      val other = admin.copy(id = admin.id)
      val zoma = chanzo.copy(id = chanzo.id)
      val results: Future[Speciality] = Future{res}
      results.map(resu => Ok(Json.toJson(resu)))
  }

  def update( Speciality: String ) = Action.async(parse.json)
  {
    request =>
      val input = request.body
      val income = (input \ "object").as[String]
      val incomeF = (input \ "facobject").as[String]
      val json = Json.parse(income)
      val jsonF = Json.parse(incomeF)
      val chanModel = Json.fromJson[SpecialityModel](json).get
      val admin = chanModel.getDomain()
      val specsObj = SpecialityModel(admin.id,admin.specialityName,admin.specialityDescription,admin.facilitatorId).getDomain()
      //val facsObj = FacilitatorModel(chanzo.id).getDomain()
      val obj: SpecialityCRUDInterface = new SpecialityCRUD
      val res = obj.update(specsObj.specialityDescription,specsObj.id)
      val results: Future[String] = Future{res.toString}
      results.map(result => Ok(Json.toJson(result)))
  }

  def delete(id:Long) = Action{

    val obj: SpecialityCRUDInterface = new SpecialityCRUD
    val res = obj.delete(id)
    Ok("Deleted")
  }

  def read(id: Long) = Action
  {
    val obj: SpecialityCRUDInterface = new SpecialityCRUD
    //val admin = chanModel.getDomain()
    val res = obj.read(id)
    val json = Json.toJson(res)
    Ok(json)
  }
}
