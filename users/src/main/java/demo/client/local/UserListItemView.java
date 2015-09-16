package demo.client.local;

import org.livespark.formmodeler.rendering.client.view.ListItemView;
import demo.client.shared.UserFormModel;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import com.google.gwt.user.client.Element;
import org.jboss.errai.ui.shared.api.annotations.Bound;
import org.jboss.errai.ui.shared.api.annotations.DataField;

@Templated("UserListView.html#User-row")
public class UserListItemView extends ListItemView<UserFormModel>
{

   @Bound(property = "user.id")
   @DataField
   private Element user_id = com.google.gwt.user.client.DOM.createTD();
   @Bound(property = "user.name")
   @DataField
   private Element user_name = com.google.gwt.user.client.DOM.createTD();
   @Bound(property = "user.lastName")
   @DataField
   private Element user_lastName = com.google.gwt.user.client.DOM.createTD();
   @Bound(property = "user.birthday")
   @DataField
   private Element user_birthday = com.google.gwt.user.client.DOM.createTD();
   @Bound(property = "user.married")
   @DataField
   private Element user_married = com.google.gwt.user.client.DOM.createTD();

   @Override
   public void initInputNames()
   {
      inputNames.add("user_id");
      inputNames.add("user_name");
      inputNames.add("user_lastName");
      inputNames.add("user_birthday");
      inputNames.add("user_married");
   }
}