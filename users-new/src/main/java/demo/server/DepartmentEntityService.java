package demo.server;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.enterprise.context.Dependent;

import org.livespark.formmodeler.rendering.server.rest.BaseEntityService;

@Dependent
@Stateless
@TransactionAttribute(javax.ejb.TransactionAttributeType.REQUIRES_NEW)
public class DepartmentEntityService extends BaseEntityService
{
}
