package demo.client.local;

import org.livespark.formmodeler.rendering.client.view.FormView;
import demo.client.shared.UserFormModel;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import javax.inject.Named;
import org.livespark.formmodeler.rendering.client.shared.meta.FormModel;
import java.util.List;
import java.util.ArrayList;
import demo.client.shared.User;
import org.gwtbootstrap3.client.ui.TextBox;
import javax.inject.Inject;
import org.jboss.errai.ui.shared.api.annotations.Bound;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.gwtbootstrap3.extras.datepicker.client.ui.DatePicker;
import org.gwtbootstrap3.client.ui.SimpleCheckBox;
import org.livespark.formmodeler.rendering.client.shared.fields.SubForm;
import demo.client.shared.Address;
import demo.client.shared.AddressFormModel;
import demo.client.local.AddressFormView;
import org.livespark.formmodeler.rendering.client.shared.fields.SubFormModelAdapter;
import org.livespark.formmodeler.rendering.client.shared.fields.MultipleSubForm;
import demo.client.local.AddressListView;
import org.livespark.formmodeler.rendering.client.shared.fields.MultipleSubFormModelAdapter;
import org.livespark.formmodeler.rendering.client.view.util.StringListBoxRenderer;
import org.gwtbootstrap3.client.ui.ValueListBox;
import java.util.Map;
import java.util.HashMap;

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
   private StringListBoxRenderer user_title_ListValueRenderer = new StringListBoxRenderer();
   @Bound(property = "user.title")
   @DataField
   private ValueListBox user_title = new ValueListBox<String>(
           user_title_ListValueRenderer);

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
   protected void initForm()
   {
      validator.registerInput("user_name", user_name);
      validator.registerInput("user_lastName", user_lastName);
      validator.registerInput("user_birthday", user_birthday);
      validator.registerInput("user_married", user_married);
      validator.registerInput("user_address", user_address);
      updateNestedModels(true);
      validator.registerInput("user_adresses", user_adresses);
      validator.registerInput("user_title", user_title);
   }

   @Override
   public void beforeDisplay()
   {
      loadListValues_user_title();
   }

   @Override
   public boolean doExtraValidations()
   {
      boolean valid = true;
      if (!user_address.validate() && valid)
      {
         valid = false;
      }
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
   public void setModel(UserFormModel model)
   {
      super.setModel(model);
      updateNestedModels(false);
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

   protected void loadListValues_user_title()
   {
      Map<String, String> values = new HashMap<String, String>();
      values.put("UNKNOWN", "");
      values.put("Mister", "Mr.");
      values.put("Mistress", "Ms.");
      values.put("Miss", "Miss");
      user_title.setValue("Mister", true);
      user_title_ListValueRenderer.setValues(values);
      user_title.setAcceptableValues(values.keySet());
   }

   @Override
   public void setReadOnly(boolean readOnly)
   {
      user_name.setReadOnly(readOnly);
      user_lastName.setReadOnly(readOnly);
      user_birthday.setReadOnly(readOnly);
      user_married.setEnabled(!readOnly);
      user_address.setReadOnly(readOnly);
      user_title.setEnabled( !readOnly );
   }
}
