package demo.client.local;

import com.google.gwt.user.client.Element;
import demo.client.shared.DepartmentFormModel;
import org.jboss.errai.ui.shared.api.annotations.Bound;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.livespark.formmodeler.rendering.client.view.ListItemView;

@Templated("DepartmentListView.html#Department-row")
public class DepartmentListItemView extends ListItemView<DepartmentFormModel>
{

   @Bound(property = "department.id")
   @DataField
   private Element department_id = com.google.gwt.user.client.DOM.createTD();
   @Bound(property = "department.name")
   @DataField
   private Element department_name = com.google.gwt.user.client.DOM.createTD();
   @Bound(property = "department.description")
   @DataField
   private Element department_description = com.google.gwt.user.client.DOM
         .createTD();

   @Override
   public void initInputNames()
   {
      inputNames.add("department_id");
      inputNames.add("department_name");
      inputNames.add("department_description");
   }
}
