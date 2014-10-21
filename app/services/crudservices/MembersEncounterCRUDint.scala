package services.crudservices

import domain.stuff.MemberEncounter
import repository.MemberEncountersRepository.MemberEncountersRepository

/**
 * Created by akhona on 2014/10/20.
 */
trait MembersEncounterCRUDint {
  def create( membersEncounter: MemberEncounter ): MemberEncounter
  def read(id: Long): List[MemberEncountersRepository#TableElementType]
  def update(id: Long, desc: String)
  def delete(id: Long)
}
