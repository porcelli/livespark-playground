package demo.client.shared;

import org.livespark.formmodeler.rendering.client.shared.LiveSparkRestService;
import javax.ws.rs.Path;
import demo.client.shared.UserFormModel;
import java.util.List;

@Path("user")
public interface UserRestService extends LiveSparkRestService<User>
{
}
