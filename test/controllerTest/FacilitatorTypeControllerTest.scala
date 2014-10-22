package controllerTest

/**
 * Created by akhona on 2014/10/16.
 */
import com.google.gson.Gson
import models.{FacilitatorModel, FacilitatorTypeModel}
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import play.api.Logger
import play.api.libs.json.{JsString, JsObject, JsValue, Json}
import play.api.test.Helpers._
import play.api.test.{FakeRequest, WithApplication}

@RunWith(classOf[JUnitRunner])
class FacilitatorTypeControllerTest extends Specification
{
  val gson = new Gson()

  "Controllers" should
    {
      "Should create FacilitatorType Object" in new WithApplication()
      {
        val facilitator = FacilitatorModel(333325)
        val jsonstringf = gson.toJson(facilitator).stripMargin


        val role = FacilitatorTypeModel(44744758,"Observer","Oversees things",333325)
        val jsonstring = gson.toJson(role).stripMargin


        val json : JsValue = JsObject(Seq
          (
              "object" -> JsString(jsonstring),
              "facobject" -> JsString(jsonstringf)
            )
        )

        val Some(result) = route(FakeRequest(
          POST, "/createFacilitatorType/:FacilitatorType").withJsonBody(json)
        )
        status(result) must equalTo(OK)
        Logger.debug(" The Result is " + result)
        contentType(result) must beSome("application/json")

      }
      "Should Update FacilitatorType Object" in new WithApplication {
        val facilitator = FacilitatorModel(3325)
        val jsonstringf = gson.toJson(facilitator).stripMargin

        val model = FacilitatorTypeModel(58,"Observer","Oversees things",3325)
        val jsonstring = gson.toJson(model).stripMargin

        val json: JsValue = JsObject(Seq
          (
              "object" -> JsString(jsonstring),
              "facobject" -> JsString(jsonstringf)
            )
        )
        val Some(result) = route(FakeRequest(
          PUT, "/updateFacilitatorType/:FacilitatorType").withBody(json)
        )
        status(result) must equalTo(OK)
        Logger.debug(" The Result is " + result)
        contentType(result) must beSome("application/json")
      }
      "Should Delete FacilitatorType Object" in new WithApplication{
        val Some(result) = route(FakeRequest(
          DELETE, "/deleteFacilitatorType/:id")
        )
        /*status(result) must equalTo(OK)
        Logger.debug(" The Result is  " + result)
        contentType(result) must beSome("text/plain")*/
      }

    }
}