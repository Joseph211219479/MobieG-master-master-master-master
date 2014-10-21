package repository

import people.{Members, Admin, Person}
import repository.AdminRepository.AdminRepository
import repository.FacilitatorRepository.FacilitatorRepository
import repository.MembersRepository.MembersRepository


import scala.slick.driver.MySQLDriver.simple._
import scala.slick.lifted.{ProvenShape, ForeignKeyQuery}

/**
 * Created by joseph on 2014/09/08.
 */
object PersonRepository {
    class PersonRepository(tag: Tag) extends Table[Person](tag, "Person"){
        def id = column[Long]("PERSON_ID", O.PrimaryKey, O.AutoInc)

        def title = column[String]("TITLE")

        def firstname = column[String]("FIRSTNAME")

        def surname = column[String]("SURNAME")

        def othername = column[String]("OTHER_NAME")

        def username = column[String]("USERNAME")

        def email = column[String]("EMAIL")

        def password = column[String]("PASSWORD")

        def admin_id = column[Long]("ADMIN_ID")

        def facilitator_id = column[Long]("FACILITATOR_ID")

        def members_id = column[Long]("MEMBERS_ID")

        def * = (id, title, firstname, surname, othername, username, email, password, admin_id, facilitator_id, members_id) <> (Person.tupled, Person.unapply)


      //override def * : ProvenShape[(String,String,String,String,String,String,String,String,String,String,String)] = (id, title,firstname,surname,othername, username,email,password,members_id,admin_id,facilitator_id)

      def facilitator = foreignKey("FAC_FK", facilitator_id, TableQuery[FacilitatorRepository])(_.id)

      def admin = foreignKey("ADM_FK", admin_id, TableQuery[AdminRepository])(_.id)

      def members = foreignKey("MEM_ID", members_id, TableQuery[MembersRepository])(_.id)
    }

    val person = TableQuery[PersonRepository]

}
