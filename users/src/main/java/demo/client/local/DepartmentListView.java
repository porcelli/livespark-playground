package demo.client.local;

import demo.client.shared.DepartmentFormModel;
import demo.client.shared.DepartmentRestService;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.livespark.formmodeler.rendering.client.shared.meta.FormModel;
import org.livespark.formmodeler.rendering.client.view.ListView;

@Templated
@FormModel("demo.client.shared.DepartmentFormModel")
public class DepartmentListView extends ListView<DepartmentFormModel, DepartmentListItemView>
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
}
