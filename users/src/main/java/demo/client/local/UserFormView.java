package demo.client.local;

import org.livespark.formmodeler.rendering.client.view.FormView;
import demo.client.shared.UserFormModel;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import javax.inject.Named;
import org.livespark.formmodeler.rendering.client.shared.meta.FormModel;
import demo.client.shared.User;
import org.gwtbootstrap3.client.ui.TextBox;
import javax.inject.Inject;
import org.jboss.errai.ui.shared.api.annotations.Bound;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import com.google.gwt.user.datepicker.client.DatePicker;
import org.gwtbootstrap3.client.ui.CheckBox;

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
   @Bound(property = "user.birthday")
   @DataField
   private DatePicker user_birthday = new DatePicker();
   @Inject
   @Bound(property = "user.married")
   @DataField
   private CheckBox user_married;

   @Override
   protected Object getEntity()
   {
      return getModel().getUser();
   }

   @Override
   protected void setNewEntity()
   {
      getModel().setUser(new User());
   }

   @Override
   protected void updateNestedModels(boolean init)
   {
   }

   @Override
   public void initInputNames()
   {
      inputNames.add("user_name");
      inputNames.add("user_lastName");
      inputNames.add("user_birthday");
      inputNames.add("user_married");
   }

   @Override
   public void setReadOnly(boolean readOnly)
   {
      user_name.setReadOnly(readOnly);
      user_lastName.setReadOnly(readOnly);
      user_married.setEnabled(!readOnly);
   }
}