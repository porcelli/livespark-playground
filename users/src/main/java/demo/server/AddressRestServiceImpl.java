package demo.server;

import demo.client.shared.AddressFormModel;
import java.util.List;
import demo.client.shared.AddressRestService;
import demo.server.AddressEntityService;
import java.util.ArrayList;
import javax.inject.Inject;
import javax.ejb.Stateless;
import java.lang.Override;

@Stateless
public class AddressRestServiceImpl implements AddressRestService
{

   @Inject
   private AddressEntityService entityService;

   @Override
   public AddressFormModel create(AddressFormModel model)
   {
      entityService.createFromFormModel(model);
      return model;
   }

   @Override
   public List<AddressFormModel> load()
   {
      List<demo.client.shared.Address> dataModels = entityService
            .listAll(demo.client.shared.Address.class);
      List<AddressFormModel> formModels = new ArrayList(dataModels.size());
      for (demo.client.shared.Address dataModel : dataModels)
      {
         formModels.add(new AddressFormModel(dataModel));
      }
      return formModels;
   }

   @Override
   public Boolean update(AddressFormModel model)
   {
      entityService.updateFromFormModel(model);
      return true;
   }

   @Override
   public Boolean delete(AddressFormModel model)
   {
      entityService.deleteFromFormModel(model);
      return true;
   }
}