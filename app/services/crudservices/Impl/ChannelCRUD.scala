package services.crudservices.Impl

import domain.people.Facilitator
import domain.stuff.Channel
import repository.ChannelRepository.ChannelRepository
import repository.FacilitatorRepository.FacilitatorRepository
import services.crudservices.ChannelCRUDInterface

import scala.slick.driver.MySQLDriver.simple._
import scala.slick.lifted.TableQuery

/**
 * Created by akhona on 2014/10/02.
 */
class ChannelCRUD extends ChannelCRUDInterface {
  val channel = TableQuery[ChannelRepository]
  val facilitator = TableQuery[FacilitatorRepository]

  override def create(chan: Channel, fac: Facilitator): Channel = {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>


      val other = facilitator.insert(fac)

      val valo = channel.insert(chan)
    }
    chan
  }


  override def read(id: Long): Channel = {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      val chan = channel.list
      val one = chan.filter( p => p.id == id ).head

      one
    }
  }


  override def update( desc: String, id: Long ) =
  {
    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      channel.filter(_.id === id).map(_.description).update(desc)

    }
  }


  override def delete(id: Long) =
  {

    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      channel.filter(_.id === id).delete
      facilitator.filter(_.id === id).delete

    }
  }
}

