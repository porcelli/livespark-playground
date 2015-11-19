package demo.client.local;

import org.livespark.formmodeler.rendering.client.view.FormView;
import demo.client.shared.AddressFormModel;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import javax.inject.Named;
import org.livespark.formmodeler.rendering.client.shared.meta.FormModel;
import java.util.List;
import java.util.ArrayList;
import demo.client.shared.Address;
import org.gwtbootstrap3.client.ui.TextBox;
import javax.inject.Inject;
import org.jboss.errai.ui.shared.api.annotations.Bound;
import org.jboss.errai.ui.shared.api.annotations.DataField;

@Templated
@Named("AddressFormView")
@FormModel("demo.client.shared.AddressFormModel")
public class AddressFormView extends FormView<AddressFormModel>
{

   @Inject
   @Bound(property = "address.street")
   @DataField
   private TextBox address_street;
   @Inject
   @Bound(property = "address.num")
   @DataField
   private TextBox address_num;
   @Inject
   @Bound(property = "address.cp")
   @DataField
   private TextBox address_cp;
   @Inject
   @Bound(property = "address.city")
   @DataField
   private TextBox address_city;
   @Inject
   @Bound(property = "address.country")
   @DataField
   private TextBox address_country;

   @Override
   protected int getEntitiesCount()
   {
      return 1;
   }

   @Override
   protected List getEntities()
   {
      List entities = new ArrayList();
      Object address = getModel().getAddress();
      if (address != null)
         entities.add(address);
      return entities;
   }

   @Override
   protected void initEntities()
   {
      if (getModel().getAddress() == null)
         getModel().setAddress(new Address());
   }

   @Override
   protected void doInit()
   {
   }

   @Override
   protected void updateNestedModels(boolean init)
   {
   }

   @Override
   public boolean doExtraValidations()
   {
      boolean valid = true;
      return valid;
   }

   @Override
   public void initInputNames()
   {
      inputNames.add("address_street");
      inputNames.add("address_num");
      inputNames.add("address_cp");
      inputNames.add("address_city");
      inputNames.add("address_country");
   }

   @Override
   public void setReadOnly(boolean readOnly)
   {
      address_street.setReadOnly(readOnly);
      address_num.setReadOnly(readOnly);
      address_cp.setReadOnly(readOnly);
      address_city.setReadOnly(readOnly);
      address_country.setReadOnly(readOnly);
   }
}