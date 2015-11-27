package demo.client.local;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

import demo.client.shared.Address;
import demo.client.shared.AddressFormModel;
import demo.client.shared.Department;
import demo.client.shared.DepartmentFormModel;
import demo.client.shared.User;
import demo.client.shared.UserFormModel;
import org.gwtbootstrap3.client.ui.TextBox;
import org.jboss.errai.ui.shared.api.annotations.Bound;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.livespark.formmodeler.rendering.client.shared.fields.MultipleSubForm;
import org.livespark.formmodeler.rendering.client.shared.fields.MultipleSubFormModelAdapter;
import org.livespark.formmodeler.rendering.client.shared.fields.SubForm;
import org.livespark.formmodeler.rendering.client.shared.fields.SubFormModelAdapter;
import org.livespark.formmodeler.rendering.client.shared.meta.FormModel;
import org.livespark.formmodeler.rendering.client.view.FormView;

@Templated
@Named("DepartmentFormView")
@FormModel("demo.client.shared.DepartmentFormModel")
public class DepartmentFormView extends FormView<DepartmentFormModel>
{

   @Inject
   @Bound(property = "department.name")
   @DataField
   private TextBox department_name;
   @Inject
   @Bound(property = "department.description")
   @DataField
   private TextBox department_description;
   @DataField
   private SubForm department_address = new SubForm(
         new Department_addressSubFormModelAdapter());
   @DataField
   private MultipleSubForm department_employees = new MultipleSubForm(
         new Department_employeesMultipleSubFormModelAdapter());

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
   protected void doInit()
   {
   }

   @Override
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
   public boolean doExtraValidations()
   {
      boolean valid = true;
      valid = department_address.validate();
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
      public AddressFormModel getFormModelForModel(Address model)
      {
         return new AddressFormModel(model);
      }
   }

   public class Department_employeesMultipleSubFormModelAdapter implements
         MultipleSubFormModelAdapter<List<User>, UserFormModel>
   {
      @Override
      public Class<UserListView> getListViewType()
      {
         return UserListView.class;
      }

      @Override
      public List<UserFormModel> getListModelsForModel(List<User> models)
      {
         List<UserFormModel> result = new ArrayList<UserFormModel>();
         if (models != null)
         {
            for (User model : models)
            {
               result.add(new UserFormModel(model));
            }
         }
         return result;
      }
   }

   @Override
   public void initInputNames()
   {
      inputNames.add("department_name");
      inputNames.add("department_description");
      inputNames.add("department_address");
      inputNames.add("department_employees");
   }

   @Override
   public void setReadOnly(boolean readOnly)
   {
      department_name.setReadOnly(readOnly);
      department_description.setReadOnly(readOnly);
      department_address.setReadOnly(readOnly);
   }
}
