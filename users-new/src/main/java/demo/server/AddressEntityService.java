package demo.server;

import org.livespark.formmodeler.rendering.server.rest.BaseEntityService;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.enterprise.context.Dependent;

@Dependent
@Stateless
@TransactionAttribute(javax.ejb.TransactionAttributeType.REQUIRES_NEW)
public class AddressEntityService extends BaseEntityService
{
}
