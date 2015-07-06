package demo.client.local.builtin;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.github.gwtbootstrap.client.ui.Tab;
import com.github.gwtbootstrap.client.ui.TabPanel;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import org.jboss.errai.ioc.client.container.IOCBeanDef;
import org.jboss.errai.ioc.client.container.SyncBeanManager;
import org.jboss.errai.ui.nav.client.local.DefaultPage;
import org.jboss.errai.ui.nav.client.local.Page;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.livespark.formmodeler.rendering.client.view.ListView;
import org.slf4j.Logger;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;

@Page( role = DefaultPage.class )
@Templated
@Dependent
@SuppressWarnings("rawtypes")
public class MainPage extends Composite {

    @Inject
    private Logger logger;

    @Inject
    @DataField
    private TabPanel container;

    @Inject
    private SyncBeanManager beanManager;

    @PostConstruct
    private void showing() {
        logger.debug( "Initializing MainPage" );
        final Collection<IOCBeanDef<ListView>> listViewBeans = beanManager.lookupBeans( ListView.class );
        logger.debug( "Found " + listViewBeans.size() + " ListView beans" );

        int tabCount = 0;

        for ( IOCBeanDef<ListView> listViewBean : listViewBeans ) {
            logger.debug( "Processing " + listViewBean.getBeanClass().getName() );
            if ( isInstantiable( listViewBean ) ) {
                logger.debug( "Instantiating " + listViewBean.getBeanClass().getName() );
                final ListView instance = listViewBean.getInstance();
                Tab tab = new Tab();
                tab.setHeading( instance.getListTitle() );
                tab.add( instance );
                tab.addClickHandler( new ClickHandler() {
                    @Override public void onClick( ClickEvent clickEvent ) {
                        instance.init();
                    }
                } );
                instance.init();
                container.add( tab );
                tabCount ++;
            }
            if (tabCount > 0) container.selectTab( 0 );
        }
    }

    private boolean isInstantiable( IOCBeanDef<ListView> listViewBean ) {
        boolean activated = listViewBean.isActivated();
        boolean instatiable = activated;

        if ( !activated )
            logger.trace( listViewBean.getBeanClass().getName() + " is not activated." );

        return instatiable;
    }

}
