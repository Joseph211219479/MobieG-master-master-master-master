package services.crudservices

import people.Role
import repository.RoleRepository.RoleRepository

/**
 * Created by joseph on 2014/10/09.
 */
trait RoleCRUDInterface
{
    def create (role :Role) : Role
    def read(id: Long): Role
    def update( desc: String, id: Long)
    def delete(id: Long)
}
