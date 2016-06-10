package demo.client.local;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.cellview.client.TextColumn;
import demo.client.shared.Department;
import demo.client.shared.DepartmentFormModel;
import demo.client.shared.DepartmentRestService;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.livespark.formmodeler.rendering.client.view.ListView;
import org.uberfire.ext.widgets.table.client.ColumnMeta;

@Templated
public class DepartmentListView extends ListView<Department, DepartmentFormModel>
{

   @Override
   protected Class<DepartmentFormView> getFormType()
   {
      return DepartmentFormView.class;
   }

   @Override
   public String getListTitle()
   {
      return "Department";
   }

   @Override
   public String getFormTitle()
   {
      return "Department Form";
   }

   @Override
   protected String getFormId()
   {
      return "Department Form";
   }

   @Override
   protected Class<DepartmentRestService> getRemoteServiceClass()
   {
      return DepartmentRestService.class;
   }

   @Override
   public List<ColumnMeta> getCrudColumns()
   {
      List<ColumnMeta> columnMetas = new ArrayList<ColumnMeta>();
      ColumnMeta<Department> id_columnMeta = new ColumnMeta<Department>(
            new TextColumn<Department>()
            {
               @Override
               public String getValue(Department model)
               {
                  Object value = model.getId();
                  if (value == null)
                  {
                     return "";
                  }
                  return String.valueOf(value);
               }
            }, "ID");
      columnMetas.add(id_columnMeta);
      ColumnMeta<Department> name_columnMeta = new ColumnMeta<Department>(
            new TextColumn<Department>()
            {
               @Override
               public String getValue(Department model)
               {
                  Object value = model.getName();
                  if (value == null)
                  {
                     return "";
                  }
                  return String.valueOf(value);
               }
            }, "Dep. Name");
      columnMetas.add(name_columnMeta);
      ColumnMeta<Department> description_columnMeta = new ColumnMeta<Department>(
            new TextColumn<Department>()
            {
               @Override
               public String getValue(Department model)
               {
                  Object value = model.getDescription();
                  if (value == null)
                  {
                     return "";
                  }
                  return String.valueOf(value);
               }
            }, "Dep. Description");
      columnMetas.add(description_columnMeta);
      return columnMetas;
   }

   @Override
   public Department getModel( DepartmentFormModel formModel)
   {
      return formModel.getDepartment();
   }

   @Override
   public DepartmentFormModel createFormModel( Department department)
   {
      DepartmentFormModel formModel = new DepartmentFormModel();
      formModel.setDepartment(department);
      return formModel;
   }
}
