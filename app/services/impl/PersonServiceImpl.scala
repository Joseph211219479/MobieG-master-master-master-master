package services.impl

import people.Person
import repository.PersonRepository.PersonRepository
import services.PersonServices
import scala.slick.driver.MySQLDriver.simple._
import scala.slick.lifted.TableQuery

/**
 * Created by joseph on 2014/09/26.
 */
class PersonServiceImpl extends PersonServices
{
  val personRepo = TableQuery[PersonRepository]

  override def getAllPeople(): List[PersonRepository#TableElementType] =
  {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession
    {
      implicit session =>
        val personObj = personRepo.list
        personObj
    }
  }
//
  override def getPersonWithId(id: Long) : Person=
  {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession {
      implicit session =>
        val personObj = personRepo.list.filter(_.id == id )
        val person = personObj.head
        person
    }
  }

  override def getPersonByName(name: String): List[PersonRepository#TableElementType] = {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession {
      implicit session =>
        val personObj = personRepo.list.filter(_.firstname == name)
        val person = personObj
        person
    }
  }

  override def getByUsername(name: String):List[PersonRepository#TableElementType] = {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession {
      implicit session =>
        val personObj = personRepo.list.filter(_.username == name)
        val person = personObj
        person
    }
  }

  override def getByTitle(title: String): List[PersonRepository#TableElementType] = {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession {
      implicit session =>
        val personObj = personRepo.list.filter(_.title == title)
        val person = personObj
        person
    }
  }

  override def getByEmail(email: String): List[PersonRepository#TableElementType] =
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession {
      implicit session =>
        val personObj = personRepo.list.filter(_.email == email)
        val person = personObj
        person
    }

  override def getPersonByFacilId(Facilid: Long): List[PersonRepository#TableElementType] = {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession {
      implicit session =>
        val personObj = personRepo.list.filter(_.facilitatorId == Facilid)
        val person = personObj
        person
    }
  }

}
