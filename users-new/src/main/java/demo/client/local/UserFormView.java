package demo.client.local;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.inject.Named;

import com.google.gwt.user.cellview.client.TextColumn;
import demo.client.shared.Address;
import demo.client.shared.AddressFormModel;
import demo.client.shared.ShortAddressFormModel;
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
import org.livespark.formmodeler.rendering.client.shared.meta.FormModel;
import org.livespark.formmodeler.rendering.client.view.FormView;
import org.livespark.formmodeler.rendering.client.view.util.StringListBoxRenderer;
import org.uberfire.ext.widgets.common.client.tables.ColumnMeta;

@Dependent
@Default
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
      beforeDisplay();
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
      public AddressFormModel getFormModelForModel( Address model)
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
         adresses = new ArrayList<Address>();
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

   @Override
   public UserFormModel getModel() {
      return binder.getModel();
   }

   @Override
   public boolean isValid() {
      return validate() & doExtraValidations();
   }


   public class User_adressesMultipleSubFormModelAdapter implements
           MultipleSubFormModelAdapter<List<Address>, Address, AddressFormModel, ShortAddressFormModel>
   {

      @Override
      public Class<AddressFormView> getCreationForm() {
         return AddressFormView.class;
      }

      @Override
      public Class<ShortAddressFormView> getEditionForm() {
         return ShortAddressFormView.class;
      }

      @Override
      public ShortAddressFormModel getEditionFormModel( Address model ) {
         return new ShortAddressFormModel( model );
      }

      @Override
      public List<ColumnMeta> getCrudColumns() {
         List<ColumnMeta> columnMetas = new ArrayList<ColumnMeta>();

         ColumnMeta<Address> columnMeta = new ColumnMeta<Address>( new TextColumn<Address>() {
            @Override
            public String getValue( Address address ) {
               return address.getStreet();
            }
         }, "Street Name" );

         columnMetas.add( columnMeta );

         columnMeta = new ColumnMeta<Address>( new TextColumn<Address>() {
            @Override
            public String getValue( Address address ) {
               if ( address.getNum() == null ) {
                  return "";
               }
               return String.valueOf( address.getNum() );
            }
         }, "#" );

         columnMetas.add( columnMeta );

         return columnMetas;
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
