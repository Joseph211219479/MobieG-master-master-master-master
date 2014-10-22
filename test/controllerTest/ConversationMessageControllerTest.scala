package controllerTest

import com.google.gson.Gson
import models.ConversationMessageModel
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import play.api.Logger
import play.api.libs.json.{JsString, JsObject, JsValue, Json}
import play.api.test.Helpers._
import play.api.test.{FakeRequest, WithApplication}
/**
 * Created by joseph on 2014/10/22.
 */
@RunWith(classOf[JUnitRunner])
class ConversationMessageControllerTest extends Specification{

  val gson = new Gson()

  "Controllers" should {
    "Should create MembersEncounter Object" in new WithApplication() {
      val role = ConversationMessageModel(12, "Hello", 22, 58, 99)
      val jsonstring = gson.toJson(role).stripMargin

      /*val json: JsValue = JsObject(Seq
        (
            "object" -> JsString(jsonstring)
          )
      )*/
      val json = Json.parse(jsonstring)

      val Some(result) = route(FakeRequest(
        POST, "/createConversationMessage/:ConversationMessage").withJsonBody(json)
      )
      status(result) must equalTo(OK)
      Logger.debug(" The Result is " + result)
      contentType(result) must beSome("application/json")

    }

    "Should update ConversationMessage Object" in new WithApplication {
      val role = ConversationMessageModel(12, "How are you doing?", 22, 58, 99)
      val jsonstring = gson.toJson(role).stripMargin
      val json = Json.parse(jsonstring)
      val Some(result) = route(FakeRequest(
        PUT, "/updateConversationMessage/:ConversationMessage").withBody(json)
      )
      status(result) must equalTo(OK)
      Logger.debug(" The Result is " + result)
      contentType(result) must beSome("application/json")
    }

    "Should Delete ConversationMessage Object" in new WithApplication{
      val Some(result) = route(FakeRequest(
        DELETE, "/deleteConversationMessage/:id")
      )
      /*status(result) must equalTo(OK)
      Logger.debug(" The Result is  " + result)
      contentType(result) must beSome("text/plain")*/
    }
  }
}
