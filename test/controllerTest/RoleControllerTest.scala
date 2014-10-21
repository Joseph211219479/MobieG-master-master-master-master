package controllerTest

import com.google.gson.Gson
import models.RoleModel
import org.junit.runner.RunWith
import org.specs2.mutable.Specification

import org.specs2.runner.JUnitRunner
import play.api.Logger
import play.api.libs.json.{JsString, JsObject, JsValue, Json}
import play.api.mvc.Controller
import play.api.test.Helpers._
import play.api.test.{FakeRequest, WithApplication}


/**
 * Created by joseph on 2014/10/09.
 */
@RunWith(classOf[JUnitRunner])
class RoleControllerTest extends Specification
{
    val gson = new Gson()

  "Controllers" should
    {
      "Should create role Object" in new WithApplication()
      {
        {
          val role = RoleModel(34014105, "some role name", "this is description things")
          val jsonstring = gson.toJson(role).stripMargin

          val json: JsValue = JsObject(Seq
            (
                "object" -> JsString(jsonstring)
            )
          )

          val Some(result) = route(FakeRequest(
            POST, "/createRole/:Role").withJsonBody(json)
          )

          status(result) must equalTo(OK)
          Logger.debug("The result of role controller is : " + result)
          contentType(result) must beSome("application/json")

        }
      }
      "Should Update Role Object" in new WithApplication {
        val role = RoleModel(34014105, "some role name", "this is description things")
        val jsonstring = gson.toJson(role).stripMargin
        val json = Json.parse(jsonstring)
        val Some(result) = route(FakeRequest(
          PUT, "/updateRole/:Role").withBody(json)
        )
        status(result) must equalTo(OK)
        Logger.debug(" The Result is " + result)
        contentType(result) must beSome("application/json")
      }
      "Should Delete Role Object" in new WithApplication{
        val Some(result) = route(FakeRequest(
          DELETE, "/deleteRole/:id")
        )
        status(result) must equalTo(OK)
        Logger.debug(" The Result is  " + result)
        contentType(result) must beSome("text/plain")
      }
  }

}
