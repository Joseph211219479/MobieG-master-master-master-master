package services.impl

import repository.SponsorRepository.SponsorRepository
import services.getAllSponsorInt

import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by akhona on 2014/09/25.
 */
class getAllSponsorImpl extends getAllSponsorInt{

  val allS = TableQuery[SponsorRepository]

  override def getusersponsor(): List[SponsorRepository#TableElementType] = {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val peeps = allS.list


      val showS = peeps.filter(s => s.id == s.id)

      showS
    }
  }
}
