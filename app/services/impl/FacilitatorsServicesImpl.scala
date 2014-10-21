package services.impl

import people.Person
import repository.FacilitatorRepository.FacilitatorRepository
import repository.MembersRepository.MembersRepository
import repository.PersonRepository.PersonRepository
import services.FacilitatorServices
import scala.slick.driver.MySQLDriver.simple._
import scala.slick.lifted.TableQuery

/**
 * Created by joseph on 2014/09/24.
 */
class FacilitatorsServicesImpl extends FacilitatorServices
{
  val memberRepo = TableQuery[MembersRepository]
  val per = TableQuery[PersonRepository]
  val facilitatorRepo = TableQuery[FacilitatorRepository]

//  override def getAllMembersServed(facilitatorID : String): List[MembersRepository#TableElementType] =
//  {
//    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "root").withSession {
//      implicit session =>
//      val memberList = memberRepo.list
//      val member = memberList.filter(_.facilitatorId == facilitatorID)
//        member
//    }
//  }

  override def hasMemberBeenServedByFacilitator(memberId: Long , facilitatorID : Long):Boolean =
  {
    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession
    {
      implicit session =>

      var fount = false
      val memberList = memberRepo.list

        val member = memberList.filter(_.id == memberId )
        if(member.head.facilitatorId == facilitatorID)
        {
          fount = true
        }
        fount
    }
  }

  override def getAllFacilitators(): List[FacilitatorRepository#TableElementType] =
  {
    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession
    {
      implicit session =>

        val list = facilitatorRepo.list
        list
    }
  }
  override def getByID(id : Long ): Person =
    {
      Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession {
      implicit session =>
      val list = per.list
    val person = list.filter(_.facilitatorId == id).head
        person
    }
    }


}
