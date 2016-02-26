package demo.server;

import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import demo.client.shared.User;
import demo.client.shared.UserRestService;

@Dependent
@Stateless
public class UserRestServiceImpl implements UserRestService
{

   @Inject
   private UserEntityService entityService;

   @Override
   public User create(User model)
   {
      entityService.create(model);
      return model;
   }

   @Override
   public List<User> load()
   {
      return entityService.listAll(demo.client.shared.User.class);
   }

   @Override
   public Boolean update( User model )
   {
      entityService.update( model );
      return true;
   }

   @Override
   public Boolean delete( User model )
   {
      entityService.delete( model );
      return true;
   }
}
