package demo.client.local;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

import com.google.gwt.user.cellview.client.TextColumn;
import demo.client.shared.Address;
import demo.client.shared.AddressFormModel;
import demo.client.shared.Department;
import demo.client.shared.DepartmentFormModel;
import demo.client.shared.User;
import demo.client.shared.UserFormModel;
import org.gwtbootstrap3.client.ui.TextArea;
import org.gwtbootstrap3.client.ui.TextBox;
import org.jboss.errai.ui.shared.api.annotations.Bound;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.livespark.formmodeler.rendering.client.shared.fields.MultipleSubForm;
import org.livespark.formmodeler.rendering.client.shared.fields.MultipleSubFormModelAdapter;
import org.livespark.formmodeler.rendering.client.shared.fields.SubForm;
import org.livespark.formmodeler.rendering.client.shared.fields.SubFormModelAdapter;
import org.livespark.formmodeler.rendering.client.view.FormView;
import org.uberfire.ext.widgets.table.client.ColumnMeta;

@Templated
@Named("DepartmentFormView")
public class DepartmentFormView extends FormView<DepartmentFormModel>
{

   @Inject
   @Bound(property = "department.name")
   @DataField
   private TextBox department_name;
   @DataField
   private SubForm department_address = new SubForm(
         new Department_addressSubFormModelAdapter());
   @DataField
   private MultipleSubForm department_employees = new MultipleSubForm(
         new Department_employeesMultipleSubFormModelAdapter());
   @Inject
   @Bound(property = "department.description")
   @DataField
   private TextArea department_description;

   @Override
   protected int getEntitiesCount()
   {
      return 1;
   }

   @Override
   protected List getEntities()
   {
      List entities = new ArrayList();
      Object department = getModel().getDepartment();
      if (department != null)
         entities.add(department);
      return entities;
   }

   @Override
   protected void initEntities()
   {
      if (getModel().getDepartment() == null)
         getModel().setDepartment(new Department());
   }

   @Override
   protected void initForm()
   {
      validator.registerInput("department_name", department_name);
      validator.registerInput("department_address", department_address);
      updateNestedModels(true);
      validator.registerInput("department_employees", department_employees);
      validator.registerInput("department_description",
            department_description);
   }

   @Override
   public void beforeDisplay()
   {
   }

   @Override
   public boolean doExtraValidations()
   {
      boolean valid = true;
      if (!department_address.validate() && valid)
      {
         valid = false;
      }
      return valid;
   }

   public class Department_addressSubFormModelAdapter implements
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
      demo.client.shared.Address address = getModel().getDepartment()
            .getAddress();
      if (address == null && init)
      {
         address = new demo.client.shared.Address();
         getModel().getDepartment().setAddress(address);
      }
      department_address.setModel(address);
      List employees = getModel().getDepartment().getEmployees();
      if (employees == null && init)
      {
         employees = new ArrayList<User>();
         getModel().getDepartment().setEmployees(employees);
      }
      department_employees.setModel(employees);
   }

   @Override
   public void setModel(DepartmentFormModel model)
   {
      super.setModel(model);
      updateNestedModels(false);
   }

   public class Department_employeesMultipleSubFormModelAdapter
         implements
         MultipleSubFormModelAdapter<List<User>, User, UserFormModel, UserFormModel>
   {
      @Override
      public Class<UserFormView> getCreationForm()
      {
         return UserFormView.class;
      }

      @Override
      public Class<UserFormView> getEditionForm()
      {
         return UserFormView.class;
      }

      @Override
      public UserFormModel getEditionFormModel( User model)
      {
         return new UserFormModel(model);
      }

      @Override
      public List<ColumnMeta> getCrudColumns()
      {
         List<ColumnMeta> columnMetas = new ArrayList<ColumnMeta>();
         ColumnMeta<User> name_columnMeta = new ColumnMeta<User>(
               new TextColumn<User>()
               {
                  @Override
                  public String getValue(User model)
                  {
                     Object value = model.getName();
                     if (value == null)
                     {
                        return "";
                     }
                     return String.valueOf(value);
                  }
               }, "Name");
         columnMetas.add(name_columnMeta);
         ColumnMeta<User> lastName_columnMeta = new ColumnMeta<User>(
               new TextColumn<User>()
               {
                  @Override
                  public String getValue(User model)
                  {
                     Object value = model.getLastName();
                     if (value == null)
                     {
                        return "";
                     }
                     return String.valueOf(value);
                  }
               }, "Last Name");
         columnMetas.add(lastName_columnMeta);
         ColumnMeta<User> birthday_columnMeta = new ColumnMeta<User>(
               new TextColumn<User>()
               {
                  @Override
                  public String getValue(User model)
                  {
                     Object value = model.getBirthday();
                     if (value == null)
                     {
                        return "";
                     }
                     return String.valueOf(value);
                  }
               }, "Birthday");
         columnMetas.add(birthday_columnMeta);
         ColumnMeta<User> married_columnMeta = new ColumnMeta<User>(
               new TextColumn<User>()
               {
                  @Override
                  public String getValue(User model)
                  {
                     Object value = model.getMarried();
                     if (value == null)
                     {
                        return "";
                     }
                     return String.valueOf(value);
                  }
               }, "Married");
         columnMetas.add(married_columnMeta);
         ColumnMeta<User> address_columnMeta = new ColumnMeta<User>(
               new TextColumn<User>()
               {
                  @Override
                  public String getValue(User model)
                  {
                     Object value = model.getAddress();
                     if (value == null)
                     {
                        return "";
                     }
                     return String.valueOf(value);
                  }
               }, "Address");
         columnMetas.add(address_columnMeta);
         ColumnMeta<User> adresses_columnMeta = new ColumnMeta<User>(
               new TextColumn<User>()
               {
                  @Override
                  public String getValue(User model)
                  {
                     Object value = model.getAdresses();
                     if (value == null)
                     {
                        return "";
                     }
                     return String.valueOf(value);
                  }
               }, "Other Adresses");
         columnMetas.add(adresses_columnMeta);
         return columnMetas;
      }
   }

   @Override
   public void setReadOnly(boolean readOnly)
   {
      department_name.setReadOnly(readOnly);
      department_address.setReadOnly(readOnly);
      department_description.setReadOnly(readOnly);
   }
}
