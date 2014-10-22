package controllerTest

/**
 * Created by akhona on 2014/10/16.
 */
import com.google.gson.Gson
import models.FacilitatorModel
import models.crudmodels.ConversationModel
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import play.api.Logger
import play.api.libs.json.{JsString, JsObject, JsValue, Json}
import play.api.test.Helpers._
import play.api.test.{FakeRequest, WithApplication}

@RunWith(classOf[JUnitRunner])
class ConversationControllerTest extends Specification
{
  val gson = new Gson()

  "Controllers" should
    {
      "Should create Conversation Object" in new WithApplication()
      {

        val facilitator = FacilitatorModel(33111338)
        val jsonstringf = gson.toJson(facilitator).stripMargin


        val role = ConversationModel(102574108,"West side nigga",33111338)
        val jsonstring = gson.toJson(role).stripMargin

        val json : JsValue = JsObject(Seq
          (
              "object" -> JsString(jsonstring),
              "facobject" -> JsString(jsonstringf)
          )
        )

        //val json = Json.parse(jsonstring)
        val Some(result) = route(FakeRequest(
          POST, "/createConversation/:Conversation").withJsonBody(json)
        )
        status(result) must equalTo(OK)
        Logger.debug(" The Result is " + result)
        contentType(result) must beSome("application/json")

      }
      "Should Update Conversation Object" in new WithApplication {
        val facilitator = FacilitatorModel(23577)
        val jsonstringf = gson.toJson(facilitator).stripMargin

        val model = ConversationModel(10257,"East side mate",588)
        val jsonstring = gson.toJson(model).stripMargin

        val json: JsValue = JsObject(Seq
          (
              "object" -> JsString(jsonstring),
              "facobject" -> JsString(jsonstringf)
            )
        )
        val Some(result) = route(FakeRequest(
          PUT, "/updateConversation/:Conversation").withBody(json)
        )
        status(result) must equalTo(OK)
        Logger.debug(" The Result is " + result)
        contentType(result) must beSome("application/json")
      }
      "Should Delete Conversation Object" in new WithApplication{
        val Some(result) = route(FakeRequest(
          DELETE, "/deleteConversation/:id")
        )
        /*status(result) must equalTo(OK)
        Logger.debug(" The Result is  " + result)
        contentType(result) must beSome("text/plain")*/
      }

    }
}