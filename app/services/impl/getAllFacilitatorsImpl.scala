package services.impl

import java.util

import domain.people.Facilitator
import people.Person
import repository.PersonRepository
import repository.PersonRepository.PersonRepository

import scala.slick.driver.MySQLDriver.simple._
import repository.FacilitatorRepository.FacilitatorRepository
import services.getAllFacilitatorsInt

/**
 * Created by akhona on 2014/09/24.
 */
class getAllFacilitatorsImpl extends getAllFacilitatorsInt{

  val allF = TableQuery[FacilitatorRepository]
  val allP = TableQuery[PersonRepository]

  override def getAll(): List[PersonRepository#TableElementType] =
  {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val peeps = allP.list
      val facs = allF.list



          val showF = facs.filter( f => f.id == f.id )

          val temp = peeps.filter(_.facilitatorId != 0  )




      peeps

    }
  }
}
