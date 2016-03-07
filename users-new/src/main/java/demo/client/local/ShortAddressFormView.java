package demo.client.local;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.inject.Named;

import demo.client.shared.Address;
import demo.client.shared.AddressFormModel;
import demo.client.shared.ShortAddressFormModel;
import org.gwtbootstrap3.client.ui.TextBox;
import org.jboss.errai.ui.shared.api.annotations.Bound;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.livespark.formmodeler.rendering.client.shared.meta.FormModel;
import org.livespark.formmodeler.rendering.client.view.FormView;

@Dependent
@Default
@Templated
@Named("ShortAddressFormView")
@FormModel("demo.client.shared.ShortAddressFormModel")
public class ShortAddressFormView extends FormView<ShortAddressFormModel>
{

   @Inject
   @Bound(property = "address.street")
   @DataField
   private TextBox address_street;
   @Inject
   @Bound(property = "address.num")
   @DataField
   private TextBox address_num;

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
         entities.add( address );
      return entities;
   }

   @Override
   protected void initEntities()
   {
      if (getModel().getAddress() == null)
         getModel().setAddress(new Address());
   }

   @Override
   protected void initForm()
   {
      validator.registerInput("address_street", address_street);
      validator.registerInput("address_num", address_num);
   }

   @Override
   public void beforeDisplay()
   {
   }

   @Override
   public boolean doExtraValidations()
   {
      boolean valid = true;
      return valid;
   }

   @Override
   public void setReadOnly(boolean readOnly)
   {
      address_street.setReadOnly(readOnly);
      address_num.setReadOnly(readOnly);
   }

   @Override
   public ShortAddressFormModel getModel() {
      return binder.getModel();
   }

   @Override
   public boolean isValid() {
      return validate() & doExtraValidations();
   }
}
