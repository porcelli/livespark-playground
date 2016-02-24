package demo.server;

import demo.client.shared.UserFormModel;
import java.util.List;
import demo.client.shared.UserRestService;
import demo.server.UserEntityService;
import java.util.ArrayList;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.ejb.Stateless;
import java.lang.Override;

@Dependent
@Stateless
public class UserRestServiceImpl implements UserRestService
{

   @Inject
   private UserEntityService entityService;

   @Override
   public UserFormModel create(UserFormModel model)
   {
      entityService.createFromFormModel(model);
      return model;
   }

   @Override
   public List<UserFormModel> load()
   {
      List<demo.client.shared.User> dataModels = entityService
            .listAll(demo.client.shared.User.class);
      List<UserFormModel> formModels = new ArrayList(dataModels.size());
      for (demo.client.shared.User dataModel : dataModels)
      {
         formModels.add(new UserFormModel(dataModel));
      }
      return formModels;
   }

   @Override
   public Boolean update(UserFormModel model)
   {
      entityService.updateFromFormModel(model);
      return true;
   }

   @Override
   public Boolean delete(UserFormModel model)
   {
      entityService.deleteFromFormModel(model);
      return true;
   }
}
