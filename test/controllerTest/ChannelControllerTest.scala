package controllerTest

/**
 * Created by akhona on 2014/10/16.
 */
import com.google.gson.Gson
import models.{FacilitatorModel, ChannelModel}
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import play.api.Logger
import play.api.libs.json.{JsString, JsObject, JsValue, Json}
import play.api.test.Helpers._
import play.api.test.{FakeRequest, WithApplication}

@RunWith(classOf[JUnitRunner])
class ChannelControllerTest extends Specification
{
  val gson = new Gson()

  "Controllers" should
    {
      "Should create Channel Object" in new WithApplication()
      {
        val facilitator = FacilitatorModel(3577)
        val jsonstringf = gson.toJson(facilitator).stripMargin


        val role = ChannelModel(12778469,"Sabc1","mzantsi fo sho",3577)
        val jsonstring = gson.toJson(role).stripMargin



        val json: JsValue = JsObject(Seq
          (
              "object" -> JsString(jsonstring),
              "facobject" -> JsString(jsonstringf)

          )
        )

        val Some(result) = route(FakeRequest(
          POST, "/createChannel/:Channel").withJsonBody(json)
        )
        status(result) must equalTo(OK)
        Logger.debug(" The Result is " + result)
        contentType(result) must beSome("application/json")

      }
    }
      "Should Update Channel Object" in new WithApplication {
      val facilitator = FacilitatorModel(23577)
      val jsonstringf = gson.toJson(facilitator).stripMargin

      val model = ChannelModel(1478469,"Sabc3","mzantsi fo sho",2557)
      val jsonstring = gson.toJson(model).stripMargin

      val json: JsValue = JsObject(Seq
        (
            "object" -> JsString(jsonstring),
            "facobject" -> JsString(jsonstringf)
        )
      )
      val Some(result) = route(FakeRequest(
      PUT, "/updateChannel/:Channel").withBody(json)
    )
      status(result) must equalTo(OK)
      Logger.debug(" The Result is " + result)
      contentType(result) must beSome("application/json")
  }
  "Should Delete Channel Object" in new WithApplication{
    val Some(result) = route(FakeRequest(
      DELETE, "/deleteChannel/:id")
    )
    /*status(result) must equalTo(OK)
    Logger.debug(" The Result is  " + result)
    contentType(result) must beSome("text/plain")*/
  }

}