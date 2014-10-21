package services.impl

import people.{Person, Admin}
import repository.AdminRepository.AdminRepository
import repository.PersonRepository.PersonRepository
import services.AdminService
import scala.slick.driver.MySQLDriver.simple._
import scala.slick.lifted.TableQuery

/**
 * Created by joseph on 2014/10/21.
 */
class AdminServiceImpl extends AdminService
{

  val AdminRepo = TableQuery[AdminRepository]
  val personRepo = TableQuery[PersonRepository]

  override def getAdminById(Adminid: Long): List[PersonRepository#TableElementType] =
  {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession {
      implicit session =>
        val list = personRepo.list
        val person = list.filter(_.adminId == Adminid )
        person
    }
  }

    override def getAllAdmin(): List[PersonRepository#TableElementType] =
    {
      Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession {
        implicit session =>
          val list = personRepo.list
          list
      }
    }

  }
