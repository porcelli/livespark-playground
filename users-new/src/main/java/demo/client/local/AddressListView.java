package demo.client.local;

import com.google.gwt.user.cellview.client.TextColumn;
import org.livespark.formmodeler.rendering.client.view.ListView;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.livespark.formmodeler.rendering.client.shared.meta.FormModel;
import demo.client.shared.AddressFormModel;
import demo.client.shared.AddressRestService;
import org.uberfire.ext.widgets.common.client.tables.ColumnMeta;

import java.lang.Override;
import java.util.ArrayList;
import java.util.List;

@Templated
@FormModel("demo.client.shared.AddressFormModel")
public class AddressListView extends ListView<AddressFormModel, AddressListItemView>
{

   @Override
   protected Class<AddressFormView> getFormType()
   {
      return AddressFormView.class;
   }

   @Override
   public String getListTitle()
   {
      return "Address";
   }

   @Override
   public String getFormTitle()
   {
      return "Address Form";
   }

   @Override
   protected String getFormId()
   {
      return "Address Form";
   }

   @Override
   public List<ColumnMeta> getCrudColumns() {
      List<ColumnMeta> metas = new ArrayList<ColumnMeta>();

      ColumnMeta<AddressFormModel> columnMeta = new ColumnMeta<AddressFormModel>( new TextColumn<AddressFormModel>() {
         @Override
         public String getValue( AddressFormModel addressFormModel ) {
            if ( addressFormModel.getAddress().getId() == null ) {
               return "";
            }
            return String.valueOf( addressFormModel.getAddress().getId() );
         }
      }, "ID #" );

      metas.add( columnMeta );

      columnMeta = new ColumnMeta<AddressFormModel>( new TextColumn<AddressFormModel>() {
         @Override
         public String getValue( AddressFormModel addressFormModel ) {
            return addressFormModel.getAddress().getStreet();
         }
      }, "Street Name" );

      metas.add( columnMeta );

      columnMeta = new ColumnMeta<AddressFormModel>( new TextColumn<AddressFormModel>() {
         @Override
         public String getValue( AddressFormModel addressFormModel ) {
            if ( addressFormModel.getAddress().getNum() == null ) {
               return "";
            }
            return String.valueOf( addressFormModel.getAddress().getNum() );
         }
      }, "#" );

      metas.add( columnMeta );

      columnMeta = new ColumnMeta<AddressFormModel>( new TextColumn<AddressFormModel>() {
         @Override
         public String getValue( AddressFormModel addressFormModel ) {
            return addressFormModel.getAddress().getCp();
         }
      }, "CP" );

      metas.add( columnMeta );

      columnMeta = new ColumnMeta<AddressFormModel>( new TextColumn<AddressFormModel>() {
         @Override
         public String getValue( AddressFormModel addressFormModel ) {
            return addressFormModel.getAddress().getCity();
         }
      }, "City" );

      metas.add( columnMeta );

      columnMeta = new ColumnMeta<AddressFormModel>( new TextColumn<AddressFormModel>() {
         @Override
         public String getValue( AddressFormModel addressFormModel ) {
            return addressFormModel.getAddress().getCountry();
         }
      }, "Country" );

      metas.add( columnMeta );

      return metas;
   }

   @Override
   protected Class<AddressRestService> getRemoteServiceClass()
   {
      return AddressRestService.class;
   }
}
