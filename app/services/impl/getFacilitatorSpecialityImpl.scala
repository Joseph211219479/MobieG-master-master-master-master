package services.impl

import repository.FacilitatorTypeRepository.FacilitatorTypeRepository
import repository.PersonRepository.PersonRepository
import repository.SpecialityRepository.SpecialityRepository
import services.getFacilitatorSpecialityInt

import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by akhona on 2014/09/25.
 */
class getFacilitatorSpecialityImpl extends getFacilitatorSpecialityInt{

  val prepo = TableQuery[PersonRepository]
  val ftrepo = TableQuery[SpecialityRepository]

  override def getSpeciality(speciality: String): List[PersonRepository#TableElementType] = {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession
    {
      implicit session =>

      val aList = prepo.list
      val bList = ftrepo.list

      val p1 = bList.filter(f => f.specialityName.equalsIgnoreCase(speciality))
      val p2 = aList.filter(p => p.facilitatorId != 0)
      p2
    }
  }

  override def getAll(): List[SpecialityRepository#TableElementType] =
  {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession
    {
      implicit session =>

        val bList = ftrepo.list
        bList
    }
  }

}
