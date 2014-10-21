package controllerTest

/**
 * Created by akhona on 2014/10/16.
 */

import models.{FacilitatorModel, RoleModel}
import models.crudmodels.SpecialityModel
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import play.api.Logger
import play.api.libs.json.{JsString, JsObject, JsValue, Json}
import play.api.test.Helpers._
import com.google.gson.Gson
import play.api.test.{FakeRequest, WithApplication}

@RunWith(classOf[JUnitRunner])
class SpecialityControllerTest extends Specification
{
  val gson = new Gson()

  "Controllers" should
    {
      "Should create Speciality Object" in new WithApplication()
      {
        val roleF = FacilitatorModel(852441793)
        val jsonstringF = gson.toJson(roleF).stripMargin

        val role = SpecialityModel(99871114,"Zubenathi","Well versed in all",852441793)
        val jsonstring = gson.toJson(role).stripMargin


        val json: JsValue = JsObject(Seq
          (
              "object" -> JsString(jsonstring),
              "facobject" -> JsString(jsonstringF)
            )
        )

        val Some(result) = route(FakeRequest(
          POST, "/createSpeciality/:Speciality").withJsonBody(json)
        )
        status(result) must equalTo(OK)
        Logger.debug(" The Result is " + result)
        contentType(result) must beSome("application/json")

      }
      "Should Update Speciality Object" in new WithApplication {
        val facilitator = FacilitatorModel(458)
        val jsonstringf = gson.toJson(facilitator).stripMargin

        val role = SpecialityModel(99871,"Zubenathi","Well versed in all",458)
        val jsonstring = gson.toJson(role).stripMargin

        val json: JsValue = JsObject(Seq
          (
              "object" -> JsString(jsonstring),
              "facobject" -> JsString(jsonstringf)
            )
        )
        val Some(result) = route(FakeRequest(
          PUT, "/updateSpeciality/:Speciality").withBody(json)
        )
        status(result) must equalTo(OK)
        Logger.debug(" The Result is " + result)
        contentType(result) must beSome("application/json")
      }
      "Should Delete Speciality Object" in new WithApplication{
        val Some(result) = route(FakeRequest(
          DELETE, "/deleteSpeciality/:id")
        )
        status(result) must equalTo(OK)
        Logger.debug(" The Result is  " + result)
        contentType(result) must beSome("text/plain")
      }

      "Should get all Speciality Object" in new WithApplication()
      {
        val Some(result) =route(FakeRequest(
        GET, "/getAllSpeciality/")
        )
        status(result) must equalTo(OK)
        Logger.debug(" The Result is " + result)
        contentType(result) must beSome("application/json")

      }


    }
}
