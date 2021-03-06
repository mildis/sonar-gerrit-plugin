package fr.techad.sonar.gerrit.factory;

import fr.techad.sonar.GerritConfiguration;
import fr.techad.sonar.GerritConstants;
import fr.techad.sonar.gerrit.GerritConnector;
import fr.techad.sonar.gerrit.network.rest.GerritRestConnector;
import fr.techad.sonar.gerrit.network.ssh.GerritSshConnector;
import fr.techad.sonar.mockito.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class GerritConnectorFactoryTest {
    @Mock
    private GerritConfiguration gerritConfiguration;

    @Test
    public void shouldGetConnectorAsGerritRestConnectorWhenSchemeIsHttp() throws Exception {
        Mockito.when(gerritConfiguration.getScheme()).thenReturn(GerritConstants.SCHEME_HTTP);
        GerritConnectorFactory gerritConnectorFactory = new GerritConnectorFactory(gerritConfiguration);
        GerritConnector gerritConnector = gerritConnectorFactory.getConnector();
        assertEquals(true, gerritConnector instanceof GerritRestConnector);
    }

    @Test
    public void shouldGetConnectorAsGerritRestConnectorWhenSchemeIsHttps() throws Exception {
        Mockito.when(gerritConfiguration.getScheme()).thenReturn(GerritConstants.SCHEME_HTTPS);
        GerritConnectorFactory gerritConnectorFactory = new GerritConnectorFactory(gerritConfiguration);
        GerritConnector gerritConnector = gerritConnectorFactory.getConnector();
        assertEquals(true, gerritConnector instanceof GerritRestConnector);
    }

    @Test
    public void shouldGetConnectorAsGerritSshConnectorWhenSchemeIsSSh() throws Exception {
        Mockito.when(gerritConfiguration.getScheme()).thenReturn(GerritConstants.SCHEME_SSH);
        Mockito.when(gerritConfiguration.getUsername()).thenReturn("user");
        Mockito.when(gerritConfiguration.getHost()).thenReturn("localhost");
        Mockito.when(gerritConfiguration.getPort()).thenReturn(22);
        GerritConnectorFactory gerritConnectorFactory = new GerritConnectorFactory(gerritConfiguration);
        GerritConnector gerritConnector = gerritConnectorFactory.getConnector();
        assertEquals(true, gerritConnector instanceof GerritSshConnector);
    }

    @Test
    public void shouldGetConnectorAsNullWhenSchemeIsUnknown() throws Exception {
        Mockito.when(gerritConfiguration.getScheme()).thenReturn("UnknownProtocol");
        GerritConnectorFactory gerritConnectorFactory = new GerritConnectorFactory(gerritConfiguration);
        GerritConnector gerritConnector = gerritConnectorFactory.getConnector();
        assertEquals(null, gerritConnector);
    }

}
