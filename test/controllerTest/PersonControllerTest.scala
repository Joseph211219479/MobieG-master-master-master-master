package controllerTest

import models.{FacilitatorModel, AdminModel}
import models.crudmodels.{MembersModel, PersonModel}
import org.specs2.mutable.Specification
import com.google.gson.Gson

import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import play.api.Logger
import play.api.libs.json.{JsString, JsObject, JsValue, Json}
import play.api.test.Helpers._
import play.api.test.{FakeRequest, WithApplication}
/**
 * Created by joseph on 2014/10/10.
 */
@RunWith(classOf[JUnitRunner])
class PersonControllerTest extends Specification
{
  val gson = new Gson()
  "Controllers" should
  {
    "Should create Person Object" in new WithApplication()
    {
      val roleA = AdminModel(66583)
      val jsonstringA = gson.toJson(roleA).stripMargin

      val roleF = FacilitatorModel(40015)
      val jsonstringF = gson.toJson(roleF).stripMargin

      val roleM = MembersModel(4239,40015)
      val jsonstringM = gson.toJson(roleM).stripMargin

      val person = PersonModel(235435,"Mr","Eminem","Davids","Slim","Joseph","password","email",66583,40015,4239)
      val jsonstring = gson.toJson(person).stripMargin

      val json: JsValue = JsObject(Seq
        (
            "object" -> JsString(jsonstring),
            "admobject" -> JsString(jsonstringA),
            "facobject" -> JsString(jsonstringF),
            "memobject" -> JsString(jsonstringM)
        )
      )

      val Some(result) = route(FakeRequest(POST,"/createPerson/:Person").withJsonBody(json))

      status(result) must equalTo(OK)
      Logger.debug("The result is" + result)
      contentType(result) must beSome("application/json")

    }
    "Should Update Person Object" in new WithApplication {
      val facilitator = FacilitatorModel(5)
      val jsonstringf = gson.toJson(facilitator).stripMargin

      val person = PersonModel(18,"Mr","Joseph","Davds","","Joeph","pasword","emil",66583,40015,5)
      val jsonstring = gson.toJson(person).stripMargin

      val json: JsValue = JsObject(Seq
        (
            "object" -> JsString(jsonstring),
            "facobject" -> JsString(jsonstringf)
          )
      )
      val Some(result) = route(FakeRequest(
        PUT, "/updatePerson/:Person").withBody(json)
      )
      status(result) must equalTo(OK)
      Logger.debug(" The Result is " + result)
      contentType(result) must beSome("application/json")
    }
    "Should Delete Person Object" in new WithApplication{
      val Some(result) = route(FakeRequest(
        DELETE, "/deletePerson/:id")
      )
      status(result) must equalTo(OK)
      Logger.debug(" The Result is  " + result)
      contentType(result) must beSome("text/plain")
    }

    //////////////////////////////////////////////

    "Should get all Persons" in new WithApplication{
      val Some(result) = route(FakeRequest(
        GET, "/getall")
      )
      status(result) must equalTo(OK)
      Logger.debug(" The Result is  " + result)
      contentType(result) must beSome("text/plain")
    }

    "Should get Person by name" in new WithApplication{
      val Some(result) = route(FakeRequest(
        GET, "/getByName/:name")
      )
      status(result) must equalTo(OK)
      Logger.debug(" The Result is  " + result)
      contentType(result) must beSome("text/plain")
    }

    "Should get Person by username" in new WithApplication{
      val Some(result) = route(FakeRequest(
        GET, "/getByUsername/:username")
      )
      status(result) must equalTo(OK)
      Logger.debug(" The Result is  " + result)
      contentType(result) must beSome("text/plain")
    }

    "Should get Person by email" in new WithApplication{
      val Some(result) = route(FakeRequest(
        GET, "/getByEmail/:email")
      )
      status(result) must equalTo(OK)
      Logger.debug(" The Result is  " + result)
      contentType(result) must beSome("text/plain")
    }

    "Should get Person by facilitator Id" in new WithApplication{
      val Some(result) = route(FakeRequest(
        GET, "/getPersonByFacilId/:facId")
      )
      status(result) must equalTo(OK)
      Logger.debug(" The Result is  " + result)
      contentType(result) must beSome("text/plain")
    }
  }
}
