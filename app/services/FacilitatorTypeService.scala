package services

import repository.FacilitatorTypeRepository.FacilitatorTypeRepository

/**
 * Created by joseph on 2014/10/21.
 */
trait FacilitatorTypeService {

  def getall() : List[FacilitatorTypeRepository#TableElementType]

  def getById(facilTypeID : Long) : List[FacilitatorTypeRepository#TableElementType]

  def getByFacilId(facilID : Long) : List[FacilitatorTypeRepository#TableElementType]

  def getByName(name : String) : List[FacilitatorTypeRepository#TableElementType]

}
