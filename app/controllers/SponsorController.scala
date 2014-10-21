package controllers

/**
 * Created by akhona on 2014/10/14.
 */

import domain.people.Facilitator
import domain.stuff.{Speciality, Channel, Sponsor}
import models.{ChannelModel, FacilitatorModel}
import models.crudmodels.{SpecialityModel, SponsorModel}
import services.SponsorService
import services.crudservices.Impl.SponsorCRUD
import services.crudservices.SponsorCRUDInterface
import services.impl.SponsorServiceImpl

import scala.concurrent.Future
import play.api.libs.json.Json
import play.api.mvc._
import scala.concurrent.ExecutionContext.Implicits.global

object SponsorController extends Controller{
  implicit val roleWrites = Json.writes[Sponsor]
  implicit val facsWrites = Json.writes[Facilitator]
  implicit val chanWrites = Json.writes[Channel]
  implicit val specialityWrites = Json.writes[Speciality]

  var sponObj : SponsorService = new SponsorServiceImpl


  def create( Sponsor: String ) = Action.async(parse.json)
  {
    request =>
      val input = request.body
      val income = (input \ "object").as[String]
      val incomeC = (input \ "facobject").as[String]
      val incomeF = (input \ "Fobject").as[String]
      val incomeD = (input \ "Sobject").as[String]
      val json = Json.parse(income)
      val jsonC = Json.parse(incomeC)
      val jsonD = Json.parse(incomeF)
      val jsonE = Json.parse(incomeD)

      val chanModel = Json.fromJson[SponsorModel](json).get
      val admin = chanModel.getDomain()
      val chanzoModel = Json.fromJson[FacilitatorModel](jsonC).get
      val chanzo = chanzoModel.getDomain()
      val chanModela = Json.fromJson[SpecialityModel](jsonD).get
      val admina = chanModela.getDomain()
      val chanzoModelb = Json.fromJson[ChannelModel](jsonE).get
      val chanzos = chanzoModelb.getDomain()

      val SpoObject = SponsorModel(admin.id,admin.name,admin.url,admin.message,admin.imageId,admin.channelId).getDomain()
      val FacObject = FacilitatorModel(chanzo.id).getDomain()
      val SpecObject = SpecialityModel(admina.id,admina.specialityName,admina.specialityDescription,admina.facilitatorId).getDomain()
      val ChanObject = ChannelModel(chanzos.id,chanzos.name,chanzos.description,chanzos.facilitatorId).getDomain()

      val obj: SponsorCRUDInterface = new SponsorCRUD
      val res = obj.create(FacObject,ChanObject, SpecObject, SpoObject)
      val other = admin.copy(id = SpoObject.id)
      val results: Future[Sponsor] = Future{res}
      results.map(resu => Ok(Json.toJson(resu)))
  }

  def update( Sponsor: String ) = Action.async(parse.json)
  {
    request =>
      val input = request.body
      val income = (input \ "object").as[String]
      val json = Json.parse(income)

      val chanModel = Json.fromJson[SponsorModel](input).get
      val chanzoModel = Json.fromJson[FacilitatorModel](input).get
      val admin = chanModel.getDomain()
      //val chanzo = chanzoModel.getDomain()
      val SpoObject = SponsorModel(admin.id,admin.name,admin.url,admin.message,admin.imageId,admin.channelId).getDomain()
      val obj: SponsorCRUDInterface = new SponsorCRUD
      val res = obj.update(SpoObject.message,SpoObject.id)
      val results: Future[String] = Future{res.toString}
      results.map(result => Ok(Json.toJson(result)))
  }

  def read(id: Long ) = Action
  {
    val obj: SponsorCRUDInterface = new SponsorCRUD
    //val admin = chanModel.getDomain()
    val res = obj.read(id)
    val json = Json.toJson(res)
    Ok(json)
  }

  def delete(id:Long) = Action{

    val obj: SponsorCRUDInterface = new SponsorCRUD
    val res = obj.delete(id)
    Ok("Deleted")
  }

  //////////////////////////////////////////////////
  def getSponsorById(id: Long) = Action
  {
    val sponsor = sponObj.getSponsorById(id )
    val json = Json.toJson(sponsor)
    Ok(json)
  }

  def getAllSponsors() = Action
  {
    val  sponsor = sponObj.getAllSponsors()
    val json = Json.toJson(sponsor)
    Ok(json)
  }

  def getSponsorByName(name: String) = Action
  {
    val  sponsor = sponObj.getSponsorByName(name)
    val json = Json.toJson(sponsor)
    Ok(json)
  }
}
