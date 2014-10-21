package models.crudmodels

import domain.stuff.Sponsor
import play.api.libs.json.Json

/**
 * Created by alex on 2014/10/09.
 */
case class SponsorModel(id:Long, name:String, url:String, message:String, imageId:String, channelId:Long) {
      def getDomain(): Sponsor = SponsorModel.domain(this)
}

object SponsorModel{
  implicit val sposnsorfmt = Json.format[SponsorModel]

  def domain(model: SponsorModel) ={
    Sponsor(model.id, model.name, model.url, model.message, model.imageId, model.channelId)
  }
}
