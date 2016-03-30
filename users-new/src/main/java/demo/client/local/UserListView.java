package demo.client.local;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.cellview.client.TextColumn;
import demo.client.shared.User;
import demo.client.shared.UserFormModel;
import demo.client.shared.UserRestService;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.livespark.formmodeler.rendering.client.view.ListView;
import org.uberfire.ext.widgets.table.client.ColumnMeta;

@Templated
public class UserListView extends ListView<User, UserFormModel>
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
   public String getFormTitle()
   {
      return "User Form";
   }

   @Override
   protected String getFormId()
   {
      return "User Form";
   }

   @Override
   public List<ColumnMeta> getCrudColumns() {
      List<ColumnMeta> metas = new ArrayList<ColumnMeta>();

      ColumnMeta<User> columnMeta = new ColumnMeta<User>( new TextColumn<User>() {
         @Override
         public String getValue( User user ) {
            if ( user.getId() == null ) {
               return "";
            }
            return String.valueOf( user.getId() );
         }
      }, "ID #" );

      metas.add( columnMeta );

      columnMeta = new ColumnMeta<User>( new TextColumn<User>() {
         @Override
         public String getValue( User user ) {
            if ( user.getName() == null ) {
               return "";
            }
            return user.getName();
         }
      }, "Name" );

      metas.add( columnMeta );

      columnMeta = new ColumnMeta<User>( new TextColumn<User>() {
         @Override
         public String getValue( User user ) {
            if ( user.getLastName() == null ) {
               return "";
            }
            return user.getLastName();
         }
      }, "Lastname" );

      metas.add( columnMeta );

      columnMeta = new ColumnMeta<User>( new TextColumn<User>() {
         @Override
         public String getValue( User user ) {
            if ( user.getBirthday() == null ) {
               return "";
            }
            return String.valueOf( user.getBirthday() );
         }
      }, "Birthday" );

      metas.add( columnMeta );

      return metas;
   }

   @Override
   public User getModel( UserFormModel userFormModel ) {
      return userFormModel.getUser();
   }

   @Override
   public UserFormModel createFormModel( User user ) {
      return new UserFormModel( user );
   }

   @Override
   protected Class<UserRestService> getRemoteServiceClass()
   {
      return UserRestService.class;
   }
}
