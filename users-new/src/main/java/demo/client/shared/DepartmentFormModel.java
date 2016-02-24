package demo.client.shared;

import java.util.Arrays;
import java.util.List;
import javax.inject.Named;
import javax.validation.Valid;

import org.jboss.errai.common.client.api.annotations.MapsTo;
import org.jboss.errai.common.client.api.annotations.Portable;
import org.jboss.errai.databinding.client.api.Bindable;
import org.livespark.formmodeler.rendering.client.shared.FormModel;

@Portable
@Bindable
@Named("DepartmentFormModel")
public class DepartmentFormModel extends FormModel
{

   @Valid
   private Department department;

   public Department getDepartment()
   {
      return department;
   }

   public void setDepartment(Department department)
   {
      this.department = department;
   }

   public DepartmentFormModel()
   {
   }

   public DepartmentFormModel(@MapsTo("department") Department department)
   {
      this.department = department;
   }

   @Override
   public List<Object> getDataModels()
   {
      return Arrays.<Object> asList(department);
   }
}
