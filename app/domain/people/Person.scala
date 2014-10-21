package people

import play.api.libs.json.Json

/**
 * Created by alex on 2014/09/02.
 */
case class Person(id:Long,
                  title:String,
                  firstname:String,
                  surname:String,
                  othername:String,
                  username:String,
                  password:String,
                  email:String,
                  adminId:Long,
                  facilitatorId:Long,
                  membersId:Long
                   )

object Persons{
  implicit lazy val personfmt = Json.format[Person]
}