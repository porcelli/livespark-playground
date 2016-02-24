package demo.client.shared;

import org.livespark.formmodeler.rendering.client.shared.FormModel;
import java.util.List;
import java.util.Arrays;
import org.jboss.errai.common.client.api.annotations.Portable;
import org.jboss.errai.databinding.client.api.Bindable;
import javax.inject.Named;
import javax.validation.Valid;
import org.jboss.errai.common.client.api.annotations.MapsTo;
import java.lang.Override;

@Portable
@Bindable
@Named("UserFormModel")
public class UserFormModel extends FormModel
{

   @Valid
   private User user;

   public User getUser()
   {
      return user;
   }

   public void setUser(User user)
   {
      this.user = user;
   }

   public UserFormModel()
   {
   }

   public UserFormModel(@MapsTo("user") User user)
   {
      this.user = user;
   }

   @Override
   public List<Object> getDataModels()
   {
      return Arrays.<Object> asList(user);
   }
}