package servicesTest

import org.scalatest.{GivenWhenThen, FeatureSpec}
import repository.PersonRepository.PersonRepository
import services.getFacilitatorSpecialityInt
import services.impl.getFacilitatorSpecialityImpl

/**
 * Created by akhona on 2014/09/25.
 */
class getFacilitatorSpecialityTest extends FeatureSpec with GivenWhenThen {
  feature("Facilitators") {
    info("-----------------------------------------")
    info("Facilitators that encounters with a certain member")
    info("-----------------------------------------")
    scenario("How much times does a facilitator have an encounter with an specific member") {
      Given("Given a Connection to the Database Through a Repository")

      var facList: List[PersonRepository#TableElementType] = null
      val testfive: getFacilitatorSpecialityInt = new getFacilitatorSpecialityImpl

      facList = testfive.getSpeciality("jay")

      print( " Size: " + facList.size + "\n")
      for( e <- facList ) print( " Facilitator ID:" + e.facilitatorId +  " Name: "  + e.firstname + " Email: " + e.password + "\n" )
      assert(facList.head.firstname.contentEquals("Tom"))





    }
  }
}
