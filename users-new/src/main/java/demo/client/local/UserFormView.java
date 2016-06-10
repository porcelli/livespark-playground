package demo.client.local;

import org.livespark.formmodeler.rendering.client.view.FormView;
import demo.client.shared.UserFormModel;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import javax.inject.Named;
import java.util.List;
import java.util.ArrayList;
import demo.client.shared.User;
import org.gwtbootstrap3.client.ui.TextBox;
import javax.inject.Inject;
import org.jboss.errai.ui.shared.api.annotations.Bound;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.gwtbootstrap3.extras.datetimepicker.client.ui.DateTimePicker;
import org.gwtbootstrap3.client.ui.SimpleCheckBox;
import org.livespark.formmodeler.rendering.client.shared.fields.SubForm;
import demo.client.shared.Address;
import demo.client.shared.AddressFormModel;
import demo.client.local.AddressFormView;
import org.livespark.formmodeler.rendering.client.shared.fields.SubFormModelAdapter;
import org.livespark.formmodeler.rendering.client.shared.fields.MultipleSubForm;
import org.livespark.formmodeler.rendering.client.shared.fields.MultipleSubFormModelAdapter;
import org.uberfire.ext.widgets.table.client.ColumnMeta;
import com.google.gwt.user.cellview.client.TextColumn;
import java.lang.Override;

@Templated
@Named("UserFormView")
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
   private DateTimePicker user_birthday;
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
   }

   @Override
   public void beforeDisplay()
   {
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

   public class User_adressesMultipleSubFormModelAdapter
           implements
           MultipleSubFormModelAdapter<List<Address>, Address, AddressFormModel, AddressFormModel>
   {
      @Override
      public Class<AddressFormView> getCreationForm()
      {
         return AddressFormView.class;
      }

      @Override
      public Class<AddressFormView> getEditionForm()
      {
         return AddressFormView.class;
      }

      @Override
      public AddressFormModel getEditionFormModel(Address model)
      {
         return new AddressFormModel(model);
      }

      @Override
      public List<ColumnMeta> getCrudColumns()
      {
         List<ColumnMeta> columnMetas = new ArrayList<ColumnMeta>();
         ColumnMeta<Address> street_columnMeta = new ColumnMeta<Address>(
                 new TextColumn<Address>()
                 {
                    @Override
                    public String getValue(Address model)
                    {
                       Object value = model.getStreet();
                       if (value == null)
                       {
                          return "";
                       }
                       return String.valueOf(value);
                    }
                 }, "Street Name");
         columnMetas.add(street_columnMeta);
         ColumnMeta<Address> num_columnMeta = new ColumnMeta<Address>(
                 new TextColumn<Address>()
                 {
                    @Override
                    public String getValue(Address model)
                    {
                       Object value = model.getNum();
                       if (value == null)
                       {
                          return "";
                       }
                       return String.valueOf(value);
                    }
                 }, "#");
         columnMetas.add(num_columnMeta);
         ColumnMeta<Address> cp_columnMeta = new ColumnMeta<Address>(
                 new TextColumn<Address>()
                 {
                    @Override
                    public String getValue(Address model)
                    {
                       Object value = model.getCp();
                       if (value == null)
                       {
                          return "";
                       }
                       return String.valueOf(value);
                    }
                 }, "CP");
         columnMetas.add(cp_columnMeta);
         ColumnMeta<Address> city_columnMeta = new ColumnMeta<Address>(
                 new TextColumn<Address>()
                 {
                    @Override
                    public String getValue(Address model)
                    {
                       Object value = model.getCity();
                       if (value == null)
                       {
                          return "";
                       }
                       return String.valueOf(value);
                    }
                 }, "City");
         columnMetas.add(city_columnMeta);
         ColumnMeta<Address> country_columnMeta = new ColumnMeta<Address>(
                 new TextColumn<Address>()
                 {
                    @Override
                    public String getValue(Address model)
                    {
                       Object value = model.getCountry();
                       if (value == null)
                       {
                          return "";
                       }
                       return String.valueOf(value);
                    }
                 }, "Country");
         columnMetas.add(country_columnMeta);
         return columnMetas;
      }
   }

   @Override
   public void setReadOnly(boolean readOnly)
   {
      user_name.setReadOnly(readOnly);
      user_lastName.setReadOnly(readOnly);
      user_birthday.setReadOnly(readOnly);
      user_married.setEnabled(!readOnly);
      user_address.setReadOnly(readOnly);
   }
}
