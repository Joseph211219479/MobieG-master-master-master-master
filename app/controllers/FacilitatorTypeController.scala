package controllers

/**
 * Created by akhona on 2014/10/10.
 */

import domain.people.Facilitator
import models.{FacilitatorModel, FacilitatorTypeModel}
import people.FacilitatorType
import play.api.libs.json.Json
import play.api.mvc._
import services.crudservices.FacilitatorTypeCRUDInterface
import services.crudservices.Impl.FacilitatorTypeCRUD
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object FacilitatorTypeController extends Controller{
  implicit val adminWrites = Json.writes[FacilitatorType]
  implicit val facsWrites = Json.writes[Facilitator]

  def create( FacilitatorType: String ) = Action.async(parse.json)
  {
    request =>
      val input = request.body

      val income = (input \ "object").as[String]
      val incomeF = (input \ "facobject").as[String]
      val json = Json.parse(income)
      val jsonF = Json.parse(incomeF)

      val chanModel = Json.fromJson[FacilitatorTypeModel](json).get
      val chanzoModel = Json.fromJson[FacilitatorModel](jsonF).get
      val admin = chanModel.getDomain()
      val chanzo = chanzoModel.getDomain()

      val typObj = FacilitatorTypeModel(admin.id,admin.name,admin.description,admin.facilitatorId).getDomain()
      val facObj = FacilitatorModel(chanzo.id).getDomain()

      val obj: FacilitatorTypeCRUDInterface = new FacilitatorTypeCRUD
      val res = obj.create(facObj,typObj)
      val other = admin.copy(id = admin.id)
      val otherz = chanzo.copy(id = chanzo.id)
      val results: Future[FacilitatorType] = Future{res}
      results.map(resu => Ok(Json.toJson(resu)))
  }

  def update( FacilitatorType: String ) = Action.async(parse.json)
  {
    request =>
      val input = request.body
      val income = (input \ "object").as[String]
      val incomeF = (input \ "facobject").as[String]
      val json = Json.parse(income)
      //val jsonF = Json.parse(incomeF)

      val chanModel = Json.fromJson[FacilitatorTypeModel](json).get
      val chanzoModel = Json.fromJson[FacilitatorModel](input).get
      val admin = chanModel.getDomain()
      //val chanzo = chanzoModel.getDomain()
      val typObj = FacilitatorTypeModel(admin.id,admin.name,admin.description,admin.facilitatorId).getDomain()
      //val facObj = FacilitatorModel(chanzo.id).getDomain()
      val obj: FacilitatorTypeCRUDInterface = new FacilitatorTypeCRUD
      val res = obj.update(typObj.description,typObj.id)
      val results: Future[String] = Future{res.toString}
      results.map(result => Ok(Json.toJson(result)))
  }

  def delete(id:Long) = Action{

    val obj: FacilitatorTypeCRUDInterface = new FacilitatorTypeCRUD
    val res = obj.delete(id)
    Ok("Deleted")
  }

  def read(id: Long) = Action
  {
    val obj: FacilitatorTypeCRUDInterface = new FacilitatorTypeCRUD
    //val admin = chanModel.getDomain()
    val res = obj.read(id)
    val json = Json.toJson(res)
    Ok(json)
  }

}
