package repository

import domain.stuff.Channel
import repository.FacilitatorRepository.FacilitatorRepository

import scala.slick.driver.MySQLDriver.simple._
import scala.slick.lifted.{ProvenShape, ForeignKeyQuery}

/**
 * Created by joseph on 2014/09/08.
 */
object ChannelRepository {

  class ChannelRepository(tag:Tag) extends Table[Channel](tag,"Channel")
  {
    def id = column[Long]("CHANNEL_ID", O.PrimaryKey, O.AutoInc)
    def name = column[String]("CHANNEL_NAME")
    def description = column[String]("CHANNEL_DESCRIPTION")
    def facilitatorID = column[Long]("CHANNEL_FACILITATORID")

    def * = (id, name, description, facilitatorID) <> ( Channel.tupled, Channel.unapply )

    //override def * : ProvenShape[(String, String, String, String)] = (id, name, description, facilitatorID)

    def facilitator = foreignKey("FAC_FK", facilitatorID, TableQuery[FacilitatorRepository])(_.id)

  }

  val channel = TableQuery[ChannelRepository]

}
