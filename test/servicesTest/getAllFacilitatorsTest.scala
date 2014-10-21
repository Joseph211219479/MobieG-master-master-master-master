package servicesTest

import org.scalatest.{GivenWhenThen, FeatureSpec}
import repository.PersonRepository.PersonRepository
import services.getAllFacilitatorsInt
import services.impl.getAllFacilitatorsImpl

/**
 * Created by akhona on 2014/09/25.
 */
class getAllFacilitatorsTest extends FeatureSpec with GivenWhenThen {
  feature("Facilitators") {
    info("-----------------------------------------")
    info("Facilitators that encounters with a certain member")
    info("-----------------------------------------")
    scenario("How much times does a facilitator have an encounter with an specific member") {
      Given("Given a Connection to the Database Through a Repository")

      //Count the encounters

      var facList: List[PersonRepository#TableElementType] = null

      val testthree: getAllFacilitatorsInt = new getAllFacilitatorsImpl

      facList = testthree.getAll()
      print( " The size is " + facList.size + "\n\n" )
      for ( j <- facList ) println(" Name: " + j.firstname + " Surname: " + j.surname + " Email: " + j.password  + "\n" )

      assert(facList.head.firstname.contentEquals("Tom"))
    }
  }

}
