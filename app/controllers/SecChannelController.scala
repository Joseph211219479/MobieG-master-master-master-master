package controllers

import controllers.ChannelController._
import domain.stuff.Channel
import play.api.libs.json.Json
import play.api.mvc._
import repository.ChannelRepository.ChannelRepository
import services.ChannelService
import services.impl.ChannelServiceImpl

/**
 * Created by akhona on 2014/10/01.
 */
object SecChannelController extends Controller {
  var channelListTwo: List[ChannelRepository#TableElementType] = null
  val chanObjs: ChannelService = new ChannelServiceImpl

  //channelListTwo = chanObjs.getAllChannels()

  implicit val absoNew = Json.reads[Channel]

  def listChannelTwo() = Action{

    val two = chanObjs.getAllChannels()
    val json = Json.toJson(two)
    Ok(json)
  }

  def index = Action {

    Ok(views.html.index("All is well!"))

  }
}
