package controllerTest

/**
 * Created by akhona on 2014/10/16.
 */

import models.FacilitatorModel
import models.crudmodels.MembersModel
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import play.api.Logger
import play.api.libs.json.{JsString, JsObject, JsValue, Json}
import play.api.test.Helpers._
import com.google.gson.Gson
import play.api.test.{FakeRequest, WithApplication}

@RunWith(classOf[JUnitRunner])
class MembersControllerTest extends Specification
{
  val gson = new Gson()

  "Controllers" should
    {
      "Should create Members Object" in new WithApplication()
      {
        val facilitator = FacilitatorModel(110110187)
        val jsonstringf = gson.toJson(facilitator).stripMargin

        val role = MembersModel(39715,110110187)
        val jsonstring = gson.toJson(role).stripMargin

        val json: JsValue = JsObject(Seq
          (
             "object" -> JsString(jsonstring),
             "facobject" -> JsString(jsonstringf)
          )
        )

        val Some(result) = route(FakeRequest(
          POST, "/createMembers/:Members").withJsonBody(json)
        )
        status(result) must equalTo(OK)
        Logger.debug(" The Result is " + result)
        contentType(result) must beSome("application/json")

      }
      "Should Update Members Object" in new WithApplication {
        val facilitator = FacilitatorModel(25)
        val jsonstringf = gson.toJson(facilitator).stripMargin

        val model = MembersModel(58,25)
        val jsonstring = gson.toJson(model).stripMargin

        val json: JsValue = JsObject(Seq
          (
              "object" -> JsString(jsonstring),
              "facobject" -> JsString(jsonstringf)
            )
        )
        val Some(result) = route(FakeRequest(
          PUT, "/updateMembers/:Members").withBody(json)
        )
        status(result) must equalTo(OK)
        Logger.debug(" The Result is " + result)
        contentType(result) must beSome("application/json")
      }
      "Should Delete Members Object" in new WithApplication{
        val Some(result) = route(FakeRequest(
          DELETE, "/deleteMembers/:id")
        )
        status(result) must equalTo(OK)
        Logger.debug(" The Result is  " + result)
        contentType(result) must beSome("text/plain")
      }
    }
}