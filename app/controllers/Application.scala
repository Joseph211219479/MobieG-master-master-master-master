package controllers

import play.api._
import play.api.mvc._
import repository.ConversationRepository.ConversationRepository
import repository.FacilitatorRepository.FacilitatorRepository
import repository.FacilitatorTypeRepository.FacilitatorTypeRepository

import repository.MemberEncountersRepository.MemberEncountersRepository
import repository.PersonRepository.PersonRepository

import services.{getFacilitatorTypeInt, getAllFacilitatorsInt, getMessageByDateInt, getUserHistoryInt}
import services.impl.{getFacilitatorTypeImpl, getAllFacilitatorsImpl, getMessageByDateImpl, getUserHistoryImpl}


object Application extends Controller {
  var encoList:List[MemberEncountersRepository#TableElementType] = null
  var convoList: List[ConversationRepository#TableElementType] = null
  var facList: List[PersonRepository#TableElementType] = null
  //var newL: List[String] = null
  //var typList: List[FacilitatorTypeRepository#TableElementType] = null


  def index = Action {
   // println(" ")
    //println(" All is well \n")

    //Count the encounters

    //val test: getUserHistoryInt = new getUserHistoryImpl

    //encoList = test.counter(5)


   // print(" Number of facilitators: " + encoList.size + " \n")
   // for ( i <- encoList ) print(" MID: " + i.memberId + " FID: " + i.facilitatorId + " Start time: " + i.startTime + " End time: " + i.endTime + "\n\n")

    //Get messages by specific date

   // val testtwo: getMessageByDateInt = new getMessageByDateImpl

  //  convoList = testtwo.getMessage("23 September 2014 12:00", 5)


   // print(" Print Messages \n")

    //for ( z <- convoList ) println(" Messages: " + z.message + " FID: " + z.facilitatorId + "\n")






    //get and list all facilitators

    //val testthree: getAllFacilitatorsInt = new getAllFacilitatorsImpl

   // facList = testthree.getAll()
    //newL = testthree.changeType(facList)



   // print( " The size iss " + facList.size + "\n\n" )
   // for ( j <- facList ) println(  " Name: " + j.firstname + " Surname: " + j.surname + " Email: " + j.password  + "\n" )


    //get specific facilitator type

    //val testfour: getFacilitatorTypeInt = new getFacilitatorTypeImpl

   // facList = testfour.getType("Relationship")


   // print( " Size: " + facList.size + "\n")

   // for( d <- facList ) print( " Name:" + d.firstname + " Email: " + d.password + "\n" )

    Ok(views.html.index("Your new application is ready."))
 }

}