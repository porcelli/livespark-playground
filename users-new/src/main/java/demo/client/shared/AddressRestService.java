package demo.client.shared;

import org.livespark.formmodeler.rendering.client.shared.LiveSparkRestService;
import javax.ws.rs.Path;

@Path("address")
public interface AddressRestService extends LiveSparkRestService<Address>
{
}
