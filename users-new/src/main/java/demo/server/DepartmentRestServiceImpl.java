package demo.server;

import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import demo.client.shared.Department;
import demo.client.shared.DepartmentRestService;

@Dependent
@Stateless
public class DepartmentRestServiceImpl implements DepartmentRestService
{

   @Inject
   private DepartmentEntityService entityService;

   @Override
   public Department create(Department model)
   {
      entityService.create(model);
      return model;
   }

   @Override
   public List<Department> load()
   {
      return entityService.listAll(demo.client.shared.Department.class);
   }

   @Override
   public Boolean update(Department model)
   {
      entityService.update(model);
      return true;
   }

   @Override
   public Boolean delete(Department model)
   {
      entityService.delete(model);
      return true;
   }
}
