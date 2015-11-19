package demo.client.local;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

import demo.client.shared.Address;
import demo.client.shared.AddressFormModel;
import demo.client.shared.User;
import demo.client.shared.UserFormModel;
import org.gwtbootstrap3.client.ui.SimpleCheckBox;
import org.gwtbootstrap3.client.ui.TextBox;
import org.gwtbootstrap3.client.ui.ValueListBox;
import org.gwtbootstrap3.extras.datepicker.client.ui.DatePicker;
import org.jboss.errai.ui.shared.api.annotations.Bound;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.livespark.formmodeler.rendering.client.shared.fields.MultipleSubForm;
import org.livespark.formmodeler.rendering.client.shared.fields.MultipleSubFormModelAdapter;
import org.livespark.formmodeler.rendering.client.shared.fields.SubForm;
import org.livespark.formmodeler.rendering.client.shared.fields.SubFormModelAdapter;
import org.livespark.formmodeler.rendering.client.shared.fields.util.StringListBoxRenderer;
import org.livespark.formmodeler.rendering.client.shared.meta.FormModel;
import org.livespark.formmodeler.rendering.client.view.FormView;

@Templated
@Named("UserFormView")
@FormModel("demo.client.shared.UserFormModel")
public class UserFormView extends FormView<UserFormModel>
{

   @Inject
   @Bound(property = "user.name")
   @DataField
   private TextBox user_name;
   @Inject
   @Bound(property = "user.lastName")
   @DataField
   private TextBox user_lastName;
   @Inject
   @Bound(property = "user.birthday")
   @DataField
   private DatePicker user_birthday;
   @Inject
   @Bound(property = "user.married")
   @DataField
   private SimpleCheckBox user_married;
   @DataField
   private SubForm user_address = new SubForm(
         new User_addressSubFormModelAdapter());
   @DataField
   private MultipleSubForm user_adresses = new MultipleSubForm(
         new User_adressesMultipleSubFormModelAdapter());
   @Bound(property = "user.title")
   @DataField
   private ValueListBox user_title = new ValueListBox<String>(
         new StringListBoxRenderer());

   @Override
   protected int getEntitiesCount()
   {
      return 1;
   }

   @Override
   protected List getEntities()
   {
      List entities = new ArrayList();
      Object user = getModel().getUser();
      if (user != null)
         entities.add(user);
      return entities;
   }

   @Override
   protected void initEntities()
   {
      if (getModel().getUser() == null)
         getModel().setUser(new User());
   }

   @Override
   protected void doInit()
   {
      List<String> user_titleListValues = new ArrayList<String>();
      user_titleListValues.add("Mr.");
      user_titleListValues.add("Mrs.");
      user_titleListValues.add("Ms.");
      user_titleListValues.add("Miss.");
      user_title.setAcceptableValues(user_titleListValues);
   }

   @Override
   protected void updateNestedModels(boolean init)
   {
      demo.client.shared.Address address = getModel().getUser().getAddress();
      if (address == null && init)
      {
         address = new demo.client.shared.Address();
         getModel().getUser().setAddress(address);
      }
      user_address.setModel(address);
      List adresses = getModel().getUser().getAdresses();
      if (adresses == null && init)
      {
         adresses = new ArrayList<demo.client.shared.Address>();
         getModel().getUser().setAdresses(adresses);
      }
      user_adresses.setModel(adresses);
   }

   @Override
   public boolean doExtraValidations()
   {
      boolean valid = true;
      valid = user_address.validate();
      return valid;
   }

   public class User_addressSubFormModelAdapter implements
         SubFormModelAdapter<Address, AddressFormModel>
   {
      @Override
      public Class<AddressFormView> getFormViewType()
      {
         return AddressFormView.class;
      }

      @Override
      public AddressFormModel getFormModelForModel(Address model)
      {
         return new AddressFormModel(model);
      }
   }

   public class User_adressesMultipleSubFormModelAdapter implements
         MultipleSubFormModelAdapter<List<Address>, AddressFormModel>
   {
      @Override
      public Class<AddressListView> getListViewType()
      {
         return AddressListView.class;
      }

      @Override
      public List<AddressFormModel> getListModelsForModel(List<Address> models)
      {
         List<AddressFormModel> result = new ArrayList<AddressFormModel>();
         if (models != null)
         {
            for (Address model : models)
            {
               result.add(new AddressFormModel(model));
            }
         }
         return result;
      }
   }

   @Override
   public void initInputNames()
   {
      inputNames.add("user_name");
      inputNames.add("user_lastName");
      inputNames.add("user_birthday");
      inputNames.add("user_married");
      inputNames.add("user_address");
      inputNames.add("user_adresses");
      inputNames.add("user_title");
   }

   @Override
   public void setReadOnly(boolean readOnly)
   {
      user_name.setReadOnly(readOnly);
      user_lastName.setReadOnly(readOnly);
      user_birthday.setReadOnly(readOnly);
      user_married.setEnabled( !readOnly );
      user_address.setReadOnly(readOnly);
      user_title.setEnabled(!readOnly);
   }
}
