package controllerTest

/**
 * Created by akhona on 2014/10/16.
 */

import models.{FacilitatorModel, RatingsModel}
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import play.api.Logger
import play.api.libs.json.{JsString, JsObject, JsValue, Json}
import play.api.test.Helpers._
import com.google.gson.Gson
import play.api.test.{FakeRequest, WithApplication}

@RunWith(classOf[JUnitRunner])
class RatingsControllerTest extends Specification
{
  val gson = new Gson()

  "Controllers" should
    {
      "Should create Ratings Object" in new WithApplication()
      {

        val roleF = FacilitatorModel(4589 )
        val jsonstringF = gson.toJson(roleF).stripMargin

        val role = RatingsModel(92776402,77,"Not bad but not great",4589)
        val jsonstring = gson.toJson(role).stripMargin

        val json: JsValue = JsObject(Seq
          (
              "object" -> JsString(jsonstring),
              "facobject" -> JsString(jsonstringF)
          )
        )

        val Some(result) = route(FakeRequest(
          POST, "/createRatings/:Ratings").withJsonBody(json)
        )
        status(result) must equalTo(OK)
        Logger.debug(" The Result is " + result)
        contentType(result) must beSome("application/json")

      }
      "Should Update Members Object" in new WithApplication {
        val facilitator = FacilitatorModel(458)
        val jsonstringf = gson.toJson(facilitator).stripMargin

        val role = RatingsModel(922,77,"Not bad but not great",458)
        val jsonstring = gson.toJson(role).stripMargin

        val json: JsValue = JsObject(Seq
          (
              "object" -> JsString(jsonstring),
              "facobject" -> JsString(jsonstringf)
            )
        )
        val Some(result) = route(FakeRequest(
          PUT, "/updateRatings/:Ratings").withBody(json)
        )
        status(result) must equalTo(OK)
        Logger.debug(" The Result is " + result)
        contentType(result) must beSome("application/json")
      }
      "Should Delete Ratings Object" in new WithApplication{
        val Some(result) = route(FakeRequest(
          DELETE, "/deleteRatings/:id")
        )
        status(result) must equalTo(OK)
        Logger.debug(" The Result is  " + result)
        contentType(result) must beSome("text/plain")
      }
    }
}