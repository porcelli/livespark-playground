package demo.client.local;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

import demo.client.shared.User;
import demo.client.shared.UserFormModel;
import org.gwtbootstrap3.client.ui.CheckBox;
import org.gwtbootstrap3.client.ui.TextBox;
import org.gwtbootstrap3.client.ui.ValueListBox;
import org.gwtbootstrap3.extras.datetimepicker.client.ui.DateTimePicker;
import org.jboss.errai.ui.shared.api.annotations.Bound;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.livespark.formmodeler.rendering.client.shared.fields.util.StringListBoxRenderer;
import org.livespark.formmodeler.rendering.client.shared.meta.FormModel;
import org.livespark.formmodeler.rendering.client.view.FormView;

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
   private DateTimePicker user_birthday;
   @Inject
   @Bound(property = "user.married")
   @DataField
   private CheckBox user_married;
   @Bound(property = "user.title")
   @DataField
   private ValueListBox<String> user_title = new ValueListBox<String>( new StringListBoxRenderer() );

   @Override
   protected void doInit() {
      List<String> user_title_values = new ArrayList<String>();
      user_title_values.add("Mr.");
      user_title_values.add("Mrs.");
      user_title_values.add("Ms.");
      user_title_values.add("Miss");

      user_title.setAcceptableValues( user_title_values );
      //user_title.setValue( "Mr.", true );
   }

   @Override
   protected void updateNestedModels(boolean init)
   {
   }

   @Override
   protected int getEntitiesCount() {
      return 1;
   }

   @Override
   protected List getEntities() {
      List result = new ArrayList();
      result.add(getModel().getUser());
      return result;
   }

   @Override
   protected void initEntities() {
      getModel().setUser(new User());
   }

   @Override
   public void initInputNames()
   {
      inputNames.add("user_name");
      inputNames.add("user_lastName");
      inputNames.add("user_birthday");
      inputNames.add("user_married");
      inputNames.add("user_title");
   }

   @Override
   public void setReadOnly(boolean readOnly)
   {
      user_name.setReadOnly(readOnly);
      user_lastName.setReadOnly(readOnly);
      user_married.setEnabled(!readOnly);
      user_title.setEnabled( !readOnly );
   }
   @Override
   public boolean doExtraValidations()
   {
      boolean valid = true;
      return valid;
   }
}
