package repository

import domain.stuff.Conversation
import repository.FacilitatorRepository.FacilitatorRepository

import scala.slick.driver.MySQLDriver.simple._
import scala.slick.lifted.{ProvenShape, ForeignKeyQuery}

/**
 * Created by joseph on 2014/09/08.
 */
object ConversationRepository
{
    class ConversationRepository(tag:Tag) extends Table[Conversation](tag,"Conversation")
    {
      def id = column[Long]("CONVERSATION_ID", O.PrimaryKey, O.AutoInc)
      def message = column[String]("CONVERSATION_MESSAGE")
      def facilitatorID = column[Long]("CONVERSATION_FACILITATORID")

      def * = (id, message, facilitatorID) <> (Conversation.tupled, Conversation.unapply)

      //override def * : ProvenShape[(String, String, String)] = (id, message, facilitatorID)


      def facilitator = foreignKey("FAC_FK", facilitatorID, TableQuery[FacilitatorRepository])(_.id)
    }

  val conversation = TableQuery[ConversationRepository]
}
