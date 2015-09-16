package demo.client.local;

import org.livespark.formmodeler.rendering.client.view.ListView;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.livespark.formmodeler.rendering.client.shared.meta.FormModel;
import demo.client.shared.UserFormModel;
import demo.client.local.UserFormView;
import demo.client.local.UserListItemView;
import demo.client.shared.UserRestService;
import java.lang.Override;

@Templated
@FormModel("demo.client.shared.UserFormModel")
public class UserListView extends ListView<UserFormModel, UserListItemView>
{

   @Override
   protected Class<UserFormView> getFormType()
   {
      return UserFormView.class;
   }

   @Override
   public String getListTitle()
   {
      return "User";
   }

   @Override
   protected String getFormTitle()
   {
      return "User Form";
   }

   @Override
   protected String getFormId()
   {
      return "User Form";
   }

   @Override
   protected Class<UserRestService> getRemoteServiceClass()
   {
      return UserRestService.class;
   }
}