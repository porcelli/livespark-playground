package demo.client.local.builtin;

import java.util.Collection;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Composite;
import org.gwtbootstrap3.client.ui.NavTabs;
import org.gwtbootstrap3.client.ui.TabContent;
import org.gwtbootstrap3.client.ui.TabListItem;
import org.gwtbootstrap3.client.ui.TabPane;
import org.jboss.errai.ioc.client.container.SyncBeanDef;
import org.jboss.errai.ioc.client.container.SyncBeanManager;
import org.jboss.errai.ui.nav.client.local.DefaultPage;
import org.jboss.errai.ui.nav.client.local.Page;
import org.jboss.errai.ui.nav.client.local.api.LoginPage;
import org.jboss.errai.ui.nav.client.local.api.SecurityError;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.livespark.formmodeler.rendering.client.view.ListView;
import org.slf4j.Logger;

@Page( role = {DefaultPage.class, LoginPage.class, SecurityError.class} )
@Templated
@Dependent
@SuppressWarnings("rawtypes")
public class MainPage extends Composite {

    @Inject
    private Logger logger;

    @Inject
    @DataField
    NavTabs tabs;

    @Inject
    @DataField
    TabContent tabsContent;

    @Inject
    private SyncBeanManager beanManager;

    @PostConstruct
    private void showing() {
        logger.debug( "Initializing MainPage" );
        final Collection<SyncBeanDef<ListView>> listViewBeans = beanManager.lookupBeans( ListView.class );
        logger.debug( "Found " + listViewBeans.size() + " ListView beans" );

        boolean firstItem = true;

        for ( SyncBeanDef<ListView> listViewBean : listViewBeans ) {
            logger.debug( "Processing " + listViewBean.getBeanClass().getName() );
            if ( isInstantiable( listViewBean ) ) {
                logger.debug("Instantiating " + listViewBean.getBeanClass().getName());
                final ListView instance = listViewBean.getInstance();

                final TabPane tabPane = new TabPane();
                tabPane.add(instance);

                final TabListItem tabListItem = new TabListItem( instance.getListTitle() );
                tabListItem.setDataTargetWidget(tabPane);
                tabListItem.addClickHandler( new ClickHandler() {
                    @Override
                    public void onClick( ClickEvent clickEvent ) {
                        instance.init();
                    }
                } );

                tabPane.setActive(true);
                tabsContent.add(tabPane);
                tabs.add(tabListItem);

                instance.init();

                tabListItem.setActive(firstItem);
                tabPane.setActive(firstItem);
                firstItem = false;
            }
        }
    }

    private boolean isInstantiable( SyncBeanDef<ListView> listViewBean ) {
        boolean activated = listViewBean.isActivated();
        boolean instatiable = activated;

        if ( !activated )
            logger.trace( listViewBean.getBeanClass().getName() + " is not activated." );

        return instatiable;
    }

}
