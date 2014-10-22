package controllers

/**
 * Created by akhona on 2014/10/02.
 */

import models.AdminModel
import people.{Person, Admin}
import play.api.libs.json._
import play.api.mvc._
import services.AdminService
import services.crudservices.AdminTestCRUDInterface
import services.crudservices.Impl.AdminCRUD
import services.impl.AdminServiceImpl
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object AdminController extends Controller {

  implicit val adminWrites = Json.writes[Admin]
  implicit val personWrites = Json.writes[Person]

  def create( Admin: String) = Action.async(parse.json)
  {
    request =>
      val input = request.body

      val income = (input \ "object").as[String]
      val json = Json.parse(income)
      val adminModel = Json.fromJson[AdminModel](json).get
      val admin = adminModel.getDomain()
      val adminObj = AdminModel(admin.id).getDomain()
      val adm: AdminTestCRUDInterface = new AdminCRUD
      val res = adm.create(adminObj)
      val other = admin.copy(id = admin.id)
      val results: Future[Admin] = Future{res}
      results.map(result => Ok(Json.toJson(result)))
  }

  def update( Admin: String) = Action.async(parse.json)
  {
    request =>
      val input = request.body
      //val income = (input \ "object").as[String]
      //val json = Json.parse(income)
      val adminModel = Json.fromJson[AdminModel](input).get
      val admin = adminModel.getDomain()
      val adminObj = AdminModel(admin.id).getDomain()
      val adm: AdminTestCRUDInterface = new AdminCRUD
      val res = adm.update(adminObj.id)
      val results: Future[String] = Future{res.toString}
      results.map(result => Ok(Json.toJson(result)))
  }

  def delete(id:Long) = Action{

    val adm: AdminTestCRUDInterface = new AdminCRUD
    val res = adm.delete(id)
    Ok("Deleted")
  }

  def read(id: Long ) = Action
  {
    val adm: AdminTestCRUDInterface = new AdminCRUD
    val res = adm.read(id)
    val json = Json.toJson(res)
    Ok(json)
  }

  ///////////////////////////////////////////

  def getAll() = Action
  {
    request =>
      val obj : AdminService = new AdminServiceImpl
      val list = obj.getAllAdmin()
      Ok(Json.toJson(list))
  }

  def getByID(Adminid : Long) = Action
  {
    request =>
      val obj : AdminService = new AdminServiceImpl
      val list = obj.getAdminById(Adminid)
      Ok(Json.toJson(list))
  }

}
