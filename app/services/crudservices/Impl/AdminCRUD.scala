package services.crudservices.Impl

import people.Admin
import repository.AdminRepository.AdminRepository
import repository.ConversationMessageRepository.ConversationMessageRepository
import repository.MemberEncountersRepository.MemberEncountersRepository
import services.crudservices.AdminTestCRUDInterface

import scala.slick.driver.MySQLDriver.simple._
import scala.slick.lifted.TableQuery

/**
 * Created by akhona on 2014/10/02.
 */
class AdminCRUD extends AdminTestCRUDInterface {
  val adminrepo = TableQuery[AdminRepository]
  val memEncounterrepo = TableQuery[MemberEncountersRepository]
  val conversationMessage = TableQuery[ConversationMessageRepository]


  override def create(admin: Admin): Admin = {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val valo = adminrepo.insert(admin)
    }
    admin
  }

  override def read(id: Long): Admin = {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val admi = adminrepo.list

      val input = admi.filter( p => p.id == id ).head
      input
    }
  }

  override def update(id: Long)= {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      adminrepo.filter(_.id === id).map(_.id).update(id)

    }

  }

  override def delete(id: Long) = {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      adminrepo.filter(_.id === id).delete

    }

  }
}
