package services.crudservices.Impl

import domain.people.Facilitator
import domain.stuff.Conversation
import repository.ConversationRepository.ConversationRepository
import repository.FacilitatorRepository.FacilitatorRepository
import services.crudservices.ConversationCRUDInterface

import scala.slick.driver.MySQLDriver.simple._
import scala.slick.lifted.TableQuery
/**
 * Created by akhona on 2014/10/02.
 */
class ConversationCRUD extends ConversationCRUDInterface{
  val convorepo = TableQuery[ConversationRepository]
  val facilitator = TableQuery[FacilitatorRepository]

  override def create( fac: Facilitator, convo: Conversation ): Conversation= {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val other = facilitator.insert(fac)
      val valo = convorepo.insert(convo)
    }
    convo
  }

  override def read(id: Long) : Conversation ={
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

     val repo = convorepo.list
     val input = repo.filter( p => p.id == id ).head
     input
    }
  }

  override def update( desc: String, id: Long) =
  {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      convorepo.filter(_.id === id).map(_.message).update(desc)
    }
  }

  override def delete(id: Long) =
  {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      convorepo.filter(_.id === id).delete
      facilitator.filter(_.id === id).delete
    }
  }
}


