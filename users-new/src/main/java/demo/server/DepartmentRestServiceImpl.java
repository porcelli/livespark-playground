package demo.server;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import demo.client.shared.DepartmentFormModel;
import demo.client.shared.DepartmentRestService;

@Dependent
@Stateless
public class DepartmentRestServiceImpl implements DepartmentRestService
{

   @Inject
   private DepartmentEntityService entityService;

   @Override
   public DepartmentFormModel create(DepartmentFormModel model)
   {
      entityService.createFromFormModel(model);
      return model;
   }

   @Override
   public List<DepartmentFormModel> load()
   {
      List<demo.client.shared.Department> dataModels = entityService
            .listAll(demo.client.shared.Department.class);
      List<DepartmentFormModel> formModels = new ArrayList(dataModels.size());
      for (demo.client.shared.Department dataModel : dataModels)
      {
         formModels.add(new DepartmentFormModel(dataModel));
      }
      return formModels;
   }

   @Override
   public Boolean update(DepartmentFormModel model)
   {
      entityService.updateFromFormModel(model);
      return true;
   }

   @Override
   public Boolean delete(DepartmentFormModel model)
   {
      entityService.deleteFromFormModel(model);
      return true;
   }
}
