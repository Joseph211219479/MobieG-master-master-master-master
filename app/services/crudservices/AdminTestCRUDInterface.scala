package services.crudservices

import people.Admin
import repository.AdminRepository.AdminRepository

/**
 * Created by akhona on 2014/10/07.
 */
trait AdminTestCRUDInterface {

  def create( admin: Admin ): Admin
  def update(id: Long)
  def read(id: Long): Admin
  def delete(id: Long)


}
