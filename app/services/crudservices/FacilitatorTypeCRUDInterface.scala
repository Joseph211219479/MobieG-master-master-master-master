package services.crudservices

import domain.people.Facilitator
import people.FacilitatorType
import repository.FacilitatorTypeRepository.FacilitatorTypeRepository

/**
 * Created by alex on 2014/10/09.
 */
trait FacilitatorTypeCRUDInterface {

  def create( fac: Facilitator, typ: FacilitatorType ): FacilitatorType
  def read(id: Long): FacilitatorType
  def update(desc: String, id: Long)
  def delete(id: Long)

}
