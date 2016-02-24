package demo.client.shared;

import javax.ws.rs.Path;

import org.livespark.formmodeler.rendering.client.shared.LiveSparkRestService;

@Path("department")
public interface DepartmentRestService extends LiveSparkRestService<DepartmentFormModel>
{
}
