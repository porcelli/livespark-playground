package demo.client.local;

import org.livespark.formmodeler.rendering.client.view.ListView;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.livespark.formmodeler.rendering.client.shared.meta.FormModel;
import demo.client.shared.AddressFormModel;
import demo.client.local.AddressFormView;
import demo.client.local.AddressListItemView;
import demo.client.shared.AddressRestService;
import java.lang.Override;

@Templated
@FormModel("demo.client.shared.AddressFormModel")
public class AddressListView extends ListView<AddressFormModel, AddressListItemView>
{

   @Override
   protected Class<AddressFormView> getFormType()
   {
      return AddressFormView.class;
   }

   @Override
   public String getListTitle()
   {
      return "Address";
   }

   @Override
   protected String getFormTitle()
   {
      return "Address Form";
   }

   @Override
   protected String getFormId()
   {
      return "Address Form";
   }

   @Override
   protected Class<AddressRestService> getRemoteServiceClass()
   {
      return AddressRestService.class;
   }
}