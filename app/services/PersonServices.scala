package services

import people.Person
import repository.PersonRepository
import repository.PersonRepository.PersonRepository
import repository.PersonRepository.PersonRepository

/**
 * Created by joseph on 2014/09/26.
 */
trait PersonServices
{
    def getAllPeople() : List[PersonRepository#TableElementType]

    def getPersonWithId(id : Long) : List[PersonRepository#TableElementType]

  def getPersonByName(name : String) : List[PersonRepository#TableElementType]

  def getByUsername(name : String) : List[PersonRepository#TableElementType]

  def getByEmail(email: String): List[PersonRepository#TableElementType]

  def getByTitle(title : String) : List[PersonRepository#TableElementType]

  def getPersonByFacilId(Facilid : Long) : List[PersonRepository#TableElementType]

}
