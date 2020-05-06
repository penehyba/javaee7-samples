package org.javaee7.servlet.protocolhandler;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import com.gargoylesoftware.htmlunit.TextPage;
import com.gargoylesoftware.htmlunit.WebClient;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Arjan Tijms
 */
@RunWith(Arquillian.class)
public class ProtocolHandlerTest {

    @ArquillianResource
    private URL base;

    WebClient webClient;

    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        return 
            ShrinkWrap.create(WebArchive.class)
                     .addClasses(
                         UpgradeServlet.class,
                         MyProtocolHandler.class);
    }
    @Before
    public void setup() {
        webClient = new WebClient();
    }
    @Test
    @RunAsClient
    public void testUpgradeProtocol() throws IOException, URISyntaxException {
        webClient.addRequestHeader("Upgrade","echo");
        final TextPage page = webClient.getPage(new URL(base, "UpgradeServlet"));
        System.out.println("PAGE: " +page.getContent());
//        addRequestHeader
//
//        System.out.println("XXX Starting test testUpgradeProtocol");
//        // Read more manually from the connection, as using the regular readers (JAX-RS client, HtmlUnit)
//        // typically hang when reading.
//        HttpClient client = HttpClients.custom().build();
//        HttpUriRequest request = RequestBuilder.get()
//                .setUri(new URL(base, "UpgradeServlet").toURI())
//                .setHeader("Upgrade", "echo")
//                .build();
//        final HttpResponse httpResponse = client.execute(request);
//        System.out.println(httpResponse.getStatusLine());
//        for (Header header: httpResponse.getAllHeaders()) {
//            System.out.println("header:");
//            System.out.println(Arrays.toString(header.getElements()));
//        }
//        System.out.println("Content of response: " + httpResponse.getEntity().getContent());
//        URLConnection connection = new URL(base, "UpgradeServlet").openConnection();
//        connection.setRequestProperty("Upgrade", "echo");
//        connection.setConnectTimeout(2000);
//        connection.setReadTimeout(2000);
//
//        StringBuilder response = new StringBuilder();
//
//        try (InputStream in = connection.getInputStream()) {
//            InputStreamReader reader = new InputStreamReader(in);
//
//            long startTime = System.currentTimeMillis();
//            while (System.currentTimeMillis() - startTime < 10000) {  // for at most 10 seconds
//                try {
//                    char[] buffer = new char[1];
//                    reader.read(buffer);
//
//                    System.out.println("Character read = " + buffer[0]);
//
//                    // Use the end of line character is this sample to signal end of transmission
//                    if (buffer[0] == '\n') {
//                        break;
//                    }
//                    response.append(buffer[0]);
//                } catch(Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        System.out.println("XXX response: " + response.toString());
//        assertTrue("In protocol handler".equals(response.toString()));
    }
    
}
