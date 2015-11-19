package demo.client.shared;

import org.livespark.formmodeler.rendering.client.shared.LiveSparkRestService;
import javax.ws.rs.Path;
import demo.client.shared.AddressFormModel;
import java.util.List;

@Path("address")
public interface AddressRestService extends LiveSparkRestService<AddressFormModel>
{
}