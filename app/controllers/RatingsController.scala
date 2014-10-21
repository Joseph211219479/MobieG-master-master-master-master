package controllers

/**
 * Created by akhona on 2014/10/10.
 */

import domain.people.Facilitator
import domain.stuff.Ratings
import models.{FacilitatorModel, RatingsModel}
import services.RatingServices
import services.crudservices.Impl.RatingsCRUD
import services.crudservices.RatingsCRUDInterface
import services.impl.RatingServiceImpl

import scala.concurrent.Future
import play.api.libs.json.Json
import play.api.mvc._
import scala.concurrent.ExecutionContext.Implicits.global

object RatingsController extends Controller{
  implicit val adminWrites = Json.writes[Ratings]
  implicit val facsWrites = Json.writes[Facilitator]

  def create( Ratings: String ) = Action.async(parse.json)
  {
    request =>
      val input = request.body

      val income = (input \ "object").as[String]
      val incomeF = (input \ "facobject").as[String]

      val json = Json.parse(income)
      val jsonF = Json.parse(incomeF)

      val chanModel = Json.fromJson[RatingsModel](json).get
      val chanzoModel = Json.fromJson[FacilitatorModel](jsonF).get
      val admin = chanModel.geDomain()
      val chanzo = chanzoModel.getDomain()

      val ratObj = RatingsModel(admin.id,admin.rate,admin.comment,admin.facilitatorId).geDomain()
      val facObj = FacilitatorModel(chanzo.id).getDomain()

      val obj: RatingsCRUDInterface = new RatingsCRUD
      val res = obj.create(facObj, ratObj)
      val other = admin.copy(id = admin.id)
      val otherz = chanzo.copy(id = chanzo.id)
      val results: Future[Ratings] = Future{res}
      results.map(resu => Ok(Json.toJson(resu)))
  }

  def update( Ratings: String ) = Action.async(parse.json)
  {
    request =>
      val input = request.body
      val income = (input \ "object").as[String]
      val incomeF = (input \ "facobject").as[String]
      val json = Json.parse(income)
      val jsonF = Json.parse(incomeF)
      val chanModel = Json.fromJson[RatingsModel](json).get
      val chanzoModel = Json.fromJson[FacilitatorModel](jsonF).get
      val admin = chanModel.geDomain()
      //val chanzo = chanzoModel.getDomain()
      val ratObj = RatingsModel(admin.id,admin.rate,admin.comment,admin.facilitatorId).geDomain()

      val obj: RatingsCRUDInterface = new RatingsCRUD
      val res = obj.update(ratObj.comment,ratObj.id)
      val results: Future[String] = Future{res.toString}
      results.map(result => Ok(Json.toJson(result)))
  }
/////////////////////////////////////////////////////////
  def delete(id:Long) = Action{

    val obj: RatingsCRUDInterface = new RatingsCRUD
    val res = obj.delete(id)
    Ok("Deleted")
  }

  def read(id: Long) = Action
  {
    val obj: RatingsCRUDInterface = new RatingsCRUD
    //val admin = chanModel.getDomain()
    val res = obj.read(id)
    val json = Json.toJson(res)
    Ok(json)
  }

  ////////////////////////////////////////////////////////

  def ratingsWithRatingOf(rate : Int) = Action
  {
    request =>
      val ratingObj : RatingServices = new RatingServiceImpl
      val list = ratingObj.ratingsWithRatingOf(rate)
      Ok(Json.toJson(list))
  }

  def ratingsbigger(rate: Int) = Action
  {
    request =>
      val ratingObj : RatingServices = new RatingServiceImpl
      val list = ratingObj.ratingsbigger(rate)
      Ok(Json.toJson(list))
  }

  def ratingsLower(rate: Int) = Action
  {
    request =>
      val ratingObj : RatingServices = new RatingServiceImpl
      val list = ratingObj.ratingsLower(rate)
      Ok(Json.toJson(list))
  }

  def rateByFacilId(facilId: Long) = Action
  {
    request =>
      val ratingObj : RatingServices = new RatingServiceImpl
      val list = ratingObj.rateByFacilId(facilId)
      Ok(Json.toJson(list))
  }

  def rateById(id: Long) = Action
  {
    request =>
      val ratingObj : RatingServices = new RatingServiceImpl
      val list = ratingObj.rateById(id)
      Ok(Json.toJson(list))
  }

}
