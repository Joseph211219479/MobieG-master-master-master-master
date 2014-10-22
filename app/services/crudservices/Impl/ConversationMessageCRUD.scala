package services.crudservices.Impl

import domain.stuff.ConversationMessage
import repository.ConversationMessageRepository.ConversationMessageRepository
import services.crudservices.ConversationMessageCRUDInterface

import scala.slick.lifted.TableQuery
import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by joseph on 2014/10/22.
 */
class ConversationMessageCRUD extends ConversationMessageCRUDInterface{

  val convomessage = TableQuery[ConversationMessageRepository]

  override def create(convoMessage: ConversationMessage): ConversationMessage = {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>
          val convo = convomessage.insert(convoMessage)
    }
    convoMessage
  }

  override def read(id: Long): List[ConversationMessageRepository#TableElementType] = {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val repo = convomessage.list
      val input = repo.filter(p => p.id == id)
      input
    }
  }

  override def update(mess: String, id: Long) = {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      convomessage.filter(_.id === id).map(_.message).update(mess)
    }
  }

  override def delete(id: Long) = {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      convomessage.filter(_.id === id).delete
    }
  }



}
