package controllers

/**
 * Created by akhona on 2014/10/01.
 */

import domain.people.Facilitator
import people.Person
import play.api.mvc._
import play.api.libs.json._
import repository.PersonRepository.PersonRepository
import services.{FacilitatorServices, getAllFacilitatorsInt}
import services.impl.{FacilitatorsServicesImpl, getAllFacilitatorsImpl}

object FacilitatorsController extends Controller{

  var facList: List[PersonRepository#TableElementType] = null

  val testthree: getAllFacilitatorsInt = new getAllFacilitatorsImpl

  //facList = testthree.getAll()


  implicit val facsWrites = Json.writes[Person]
  implicit val facWrites = Json.writes[Facilitator]


  def listFacilitators() = Action{

    val facs = testthree.getAll()
    val json = Json.toJson(facs)
    Ok(json)
  }

  def index = Action {

      Ok(views.html.index("All is well!"))

  }

  def getAllFacilitators() = Action
  {
    request =>
      val facilitator : FacilitatorServices = new FacilitatorsServicesImpl
      val all = facilitator.getAllFacilitators()
      Ok(Json.toJson(all))
  }

}
