package domain.stuff

import play.api.libs.json.Json

/**
 * Created by alex on 2014/09/02.
 */
case class Channel(id:Long,
                   name:String,
                   description:String,
                   facilitatorId:Long
                    )

object Channels{
    implicit lazy val channelfmt = Json.format[Channel]
}


