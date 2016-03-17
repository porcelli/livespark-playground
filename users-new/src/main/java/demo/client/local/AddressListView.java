package demo.client.local;

import com.google.gwt.user.cellview.client.TextColumn;
import demo.client.shared.Address;
import org.livespark.formmodeler.rendering.client.view.ListView;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.livespark.formmodeler.rendering.client.shared.meta.FormModel;
import demo.client.shared.AddressFormModel;
import demo.client.shared.AddressRestService;
import org.uberfire.ext.widgets.table.client.ColumnMeta;

import java.lang.Override;
import java.util.ArrayList;
import java.util.List;

@Templated
@FormModel("demo.client.shared.AddressFormModel")
public class AddressListView extends ListView<Address, AddressFormModel>
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

      ColumnMeta<Address> columnMeta = new ColumnMeta<Address>( new TextColumn<Address>() {
         @Override
         public String getValue( Address address ) {
            if ( address.getId() == null ) {
               return "";
            }
            return String.valueOf( address.getId() );
         }
      }, "ID #" );

      metas.add( columnMeta );

      columnMeta = new ColumnMeta<Address>( new TextColumn<Address>() {
         @Override
         public String getValue( Address address ) {
            return address.getStreet();
         }
      }, "Street Name" );

      metas.add( columnMeta );

      columnMeta = new ColumnMeta<Address>( new TextColumn<Address>() {
         @Override
         public String getValue( Address address ) {
            if ( address.getNum() == null ) {
               return "";
            }
            return String.valueOf( address.getNum() );
         }
      }, "#" );

      metas.add( columnMeta );

      columnMeta = new ColumnMeta<Address>( new TextColumn<Address>() {
         @Override
         public String getValue( Address address ) {
            return address.getCp();
         }
      }, "CP" );

      metas.add( columnMeta );

      columnMeta = new ColumnMeta<Address>( new TextColumn<Address>() {
         @Override
         public String getValue( Address address ) {
            return address.getCity();
         }
      }, "City" );

      metas.add( columnMeta );

      columnMeta = new ColumnMeta<Address>( new TextColumn<Address>() {
         @Override
         public String getValue( Address address ) {
            return address.getCountry();
         }
      }, "Country" );

      metas.add( columnMeta );

      return metas;
   }

   @Override
   public Address getModel( AddressFormModel addressFormModel ) {
      return addressFormModel.getAddress();
   }

   @Override
   public AddressFormModel createFormModel( Address address ) {
      return new AddressFormModel( address );
   }

   @Override
   protected Class<AddressRestService> getRemoteServiceClass()
   {
      return AddressRestService.class;
   }
}
