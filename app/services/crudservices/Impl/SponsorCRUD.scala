package services.crudservices.Impl

import domain.people.Facilitator
import domain.stuff.{Channel, Speciality, Sponsor}
import repository.ChannelRepository.ChannelRepository
import repository.FacilitatorRepository.FacilitatorRepository
import repository.SpecialityRepository.SpecialityRepository
import repository.SponsorRepository.SponsorRepository
import services.crudservices.SponsorCRUDInterface

import scala.slick.driver.MySQLDriver.simple._
import scala.slick.lifted.TableQuery

/**
 * Created by akhona on 2014/10/02.
 *
 * */

 class SponsorCRUD extends SponsorCRUDInterface{
  val channel = TableQuery[ChannelRepository]
  val spons = TableQuery[SponsorRepository]
  val fac = TableQuery[FacilitatorRepository]
  val specs = TableQuery[SpecialityRepository]

  override def create( facs: Facilitator, chan: Channel, spec: Speciality, spo: Sponsor ): Sponsor = {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>


      val other = fac.insert(facs)

      val valo = channel.insert(chan)

      val vho = specs.insert(spec)

      val nser = spons.insert(spo)
    }
    spo
  }

  //Testing for extraction
  override def read(id: Long): Sponsor = {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val repo = spons.list
      val input = repo.filter( p => p.id == id).head
      input
    }
  }


  override def update( desc: String, id: Long) =
  {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      spons.filter(_.id === id).map(_.message).update(desc)

    }
  }

  override def delete(id: Long) =
  {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      spons.filter(_.id === id).delete
      //channel.filter(_.id === id).delete
      //fac.filter(_.id === id).delete
      //specs.filter(_.id === id).delete

    }
  }
}

