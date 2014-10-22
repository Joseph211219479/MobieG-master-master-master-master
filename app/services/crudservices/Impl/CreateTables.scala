package services.crudservices.Impl

import repository.AdminRepository.AdminRepository
import repository.ChannelRepository.ChannelRepository
import repository.ConversationMessageRepository.ConversationMessageRepository
import repository.ConversationRepository.ConversationRepository
import repository.FacilitatorRepository.FacilitatorRepository
import repository.FacilitatorTypeRepository.FacilitatorTypeRepository
import repository.MemberEncountersRepository.MemberEncountersRepository
import repository.MembersRepository.MembersRepository
import repository.PersonRepository.PersonRepository
import repository.RatingRepository.RatingRepository
import repository.RoleRepository.RoleRepository
import repository.SpecialityRepository.SpecialityRepository
import repository.SponsorRepository.SponsorRepository
import services.crudservices.CreateTablesInt
import scala.slick.driver.MySQLDriver.simple._
import scala.slick.lifted.TableQuery

/**
 * Created by akhona on 2014/10/16.
 */
class CreateTables extends CreateTablesInt
{
  override def createTables() =
  {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val admin = TableQuery[AdminRepository]
      val channel = TableQuery[ChannelRepository]
      val convorepo = TableQuery[ConversationRepository]
      val convomessagerepo = TableQuery[ConversationMessageRepository]
      val peeps = TableQuery[PersonRepository]
      val ratrepo = TableQuery[RatingRepository]
      val role = TableQuery[RoleRepository]
      val specsd = TableQuery[SpecialityRepository]
      val spons = TableQuery[SponsorRepository]
      val facirepo = TableQuery[FacilitatorTypeRepository]
      val memrepo = TableQuery[MembersRepository]
      val memEncounterrepo = TableQuery[MemberEncountersRepository]
      val facilitator = TableQuery[FacilitatorRepository]

      //Create table Facilitator
      //facirepo.ddl.create

      //Create table Admin
      //admin.ddl.create

      //Create table Channel
      //channel.ddl.create

      //Create table Conversation
      //facilitator.ddl.create

      //Create table ConversationMessage
      convomessagerepo.ddl.create

      //Create table Person
      //peeps.ddl.create

      //Create table Ratings
      //ratrepo.ddl.create

      //Create table Role
      //role.ddl.create

      //Create table Speciality
      //specsd.ddl.create

      //Create table Sponsor
      //spons.ddl.create

      //Create table Members
      //memrepo.ddl.create

      //Create table MembersEncounter
      //memEncounterrepo.ddl.create

      //convorepo.ddl.create

    }
  }
}
