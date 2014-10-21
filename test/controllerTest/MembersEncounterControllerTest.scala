package controllerTest

/**
 * Created by akhona on 2014/10/20.
 */
import com.google.gson.Gson
import models.crudmodels.MemberEncounterModel
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import play.api.Logger
import play.api.libs.json.{JsString, JsObject, JsValue, Json}
import play.api.test.Helpers._
import play.api.test.{FakeRequest, WithApplication}

@RunWith(classOf[JUnitRunner])
class MembersEncounterControllerTest extends Specification
{
  val gson = new Gson()

  "Controllers" should {
    "Should create MembersEncounter Object" in new WithApplication() {
      val role = MemberEncounterModel(12, "15/01/2014", "15/01/2014", 58, 99)
      val jsonstring = gson.toJson(role).stripMargin

      val json: JsValue = JsObject(Seq
        (
            "object" -> JsString(jsonstring)
          )
      )

      val Some(result) = route(FakeRequest(
        POST, "/createMembersEncounter/:MembersEncounter").withJsonBody(json)
      )
      status(result) must equalTo(OK)
      Logger.debug(" The Result is " + result)
      contentType(result) must beSome("application/json")

    }
    "Should update MembersEncounter Object" in new WithApplication {
      val role = MemberEncounterModel(12, "19/01/2014", "18/01/2014", 58, 99)
      val jsonstring = gson.toJson(role).stripMargin
      val json = Json.parse(jsonstring)
      val Some(result) = route(FakeRequest(
        PUT, "/updateMembersEncounter/:MembersEncounter").withBody(json)
      )
      status(result) must equalTo(OK)
      Logger.debug(" The Result is " + result)
      contentType(result) must beSome("application/json")
    }

    "Should Delete MembersEncounter Object" in new WithApplication{
      val Some(result) = route(FakeRequest(
        DELETE, "/deleteMembersEncounter/:id")
      )
      status(result) must equalTo(OK)
      Logger.debug(" The Result is  " + result)
      contentType(result) must beSome("text/plain")
    }
  }
}
