package controllers

/**
 * Created by akhona on 2014/10/14.
 */

import models.RoleModel
import people.Role
import services.RoleServices
import services.crudservices.Impl.RoleCRUD
import services.crudservices.RoleCRUDInterface
import services.impl.RoleServiceImpl

import scala.concurrent.Future
import play.api.libs.json.Json
import play.api.mvc._
import scala.concurrent.ExecutionContext.Implicits.global

object RoleController extends Controller{
  implicit val roleWrites = Json.writes[Role]

  def create( Role: String ) = Action.async(parse.json)
  {
    request =>
      val input = request.body

      val income = (input \ "object").as[String]
      val json = Json.parse(income)

      val chanModel = Json.fromJson[RoleModel](json).get
      val admin = chanModel.getDomain()

      val rolObj = RoleModel(admin.roleId, admin.description, admin.roleName).getDomain()

      val obj: RoleCRUDInterface = new RoleCRUD
      val res = obj.create(rolObj)
      val other = admin.copy(roleId = admin.roleId)
      val results: Future[Role] = Future{res}
      results.map(resu => Ok(Json.toJson(resu)))
  }

  def update(Role: String) = Action.async(parse.json)
  {
    request =>
      val input = request.body
      //val income = (input \ "object").as[String]
      //val json = Json.parse(income)

      val chanModel = Json.fromJson[RoleModel](input).get
      val admin = chanModel.getDomain()
      val rolObj = RoleModel(admin.roleId, admin.description, admin.roleName).getDomain()

      val obj: RoleCRUDInterface = new RoleCRUD
      val res = obj.update(rolObj.description,rolObj.roleId)
      val results: Future[String] = Future{res.toString}
      results.map(result => Ok(Json.toJson(result)))
  }

  def delete(id:Long) = Action{

    val obj: RoleCRUDInterface = new RoleCRUD
    val res = obj.delete(id)
    Ok("Deleted")
  }

  def read(id: Long) = Action
  {
    val obj: RoleCRUDInterface = new RoleCRUD
    //val admin = chanModel.getDomain()
    val res = obj.read(id)
    val json = Json.toJson(res)
    Ok(json)
  }

  ////////////////////////////////////////////////

  def getAll = Action
  {
    request =>
      val roleObj : RoleServices = new RoleServiceImpl
      val list = roleObj.allServices()
      Ok(Json.toJson(list))
  }

  def getById(id : Long) = Action
  {
    request =>
      val roleObj : RoleServices = new RoleServiceImpl
      val list = roleObj.roleById(id)
      Ok(Json.toJson(list))
  }

  def getByName(name : String) = Action
  {
    request =>
      val roleObj : RoleServices = new RoleServiceImpl
      val list = roleObj.getByName(name)
      Ok(Json.toJson(list))
  }

}
