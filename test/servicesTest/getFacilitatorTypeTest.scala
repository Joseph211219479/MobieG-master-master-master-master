package servicesTest

import org.scalatest.{GivenWhenThen, FeatureSpec}
import repository.PersonRepository.PersonRepository
import services.getFacilitatorTypeInt
import services.impl.getFacilitatorTypeImpl

/**
 * Created by akhona on 2014/09/25.
 */
class getFacilitatorTypeTest extends FeatureSpec with GivenWhenThen {
  feature("Facilitators") {
    info("-----------------------------------------")
    info("Facilitators that encounters with a certain member")
    info("-----------------------------------------")
    scenario("How much times does a facilitator have an encounter with an specific member") {
      Given("Given a Connection to the Database Through a Repository")

      var facList: List[PersonRepository#TableElementType] = null
      val testfour: getFacilitatorTypeInt = new getFacilitatorTypeImpl

      facList = testfour.getType("Relationship")


      print( " Size: " + facList.size + "\n")
      for( d <- facList ) print( " Name:" + d.firstname + " Email: " + d.password + "\n" )
      assert(facList.head.firstname.contentEquals("Tom"))
    }
  }
}
