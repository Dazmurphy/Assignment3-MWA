package assign.resources;

import java.io.StringReader;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.InputSource;


public class TestEavesdropResources {
	
	@Test
	public void testProject() throws Exception{
		Client client = ClientBuilder.newClient();
		String parsedXml = "";
		try{
			
			Response response = client.target("http://localhost:8080/assignment3/myeavesdrop/projects").request().get();
			
			if(response.getStatus() != 200) throw new RuntimeException("Failed to get projects");
			
			String xml = response.readEntity(String.class);
			
			InputSource source = new InputSource(new StringReader(xml));
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			org.w3c.dom.Document document = db.parse(source);

			XPathFactory xpathFactory = XPathFactory.newInstance();
			XPath xpath = xpathFactory.newXPath();
			
			parsedXml = xpath.evaluate("/", document);
			
			response.close();
			
			}finally{
				client.close();
			}
		
		Assert.assertTrue(parsedXml.contains("__poppy/"));
		Assert.assertTrue(parsedXml.contains("ci/"));
		Assert.assertTrue(parsedXml.contains("compass"));
		Assert.assertTrue(parsedXml.contains("docwebteam/"));
		Assert.assertTrue(parsedXml.contains("zaqar/"));
		Assert.assertTrue(parsedXml.contains("murano_sincup/"));
		Assert.assertTrue(parsedXml.contains("openstack-meeting-4/"));
		Assert.assertTrue(parsedXml.contains("python3/"));
		Assert.assertTrue(parsedXml.contains("test/"));
		Assert.assertTrue(parsedXml.contains("telcowg"));
	}
	
	@Test
	public void testExistingProject1(){
		Client client = ClientBuilder.newClient();
		String parsedXml = "";
		try{
			
			Response response = client.target("http://localhost:8080/assignment3/myeavesdrop/projects/solum_team_meeting/meetings").request().get();
			
			if(response.getStatus() != 200) throw new RuntimeException("Failed to get meetings");
			
			String xml = response.readEntity(String.class);
			
			InputSource source = new InputSource(new StringReader(xml));
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			org.w3c.dom.Document document = db.parse(source);

			XPathFactory xpathFactory = XPathFactory.newInstance();
			XPath xpath = xpathFactory.newXPath();
			
			parsedXml = xpath.evaluate("/", document);
			response.close();
			
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				client.close();
			}
		
		Assert.assertTrue(parsedXml.contains("2013/"));
		Assert.assertTrue(parsedXml.contains("2014/"));
		Assert.assertTrue(parsedXml.contains("2015/"));
		Assert.assertTrue(parsedXml.contains("2016/"));
	}
	
	@Test
	public void testExistingProject2(){
		Client client = ClientBuilder.newClient();
		String parsedXml = "";
		try{
			
			Response response = client.target("http://localhost:8080/assignment3/myeavesdrop/projects/3rd_party_ci/meetings").request().get();
			
			if(response.getStatus() != 200) throw new RuntimeException("Failed to get meetings");
			
			String xml = response.readEntity(String.class);
			
			InputSource source = new InputSource(new StringReader(xml));
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			org.w3c.dom.Document document = db.parse(source);

			XPathFactory xpathFactory = XPathFactory.newInstance();
			XPath xpath = xpathFactory.newXPath();
			
			parsedXml = xpath.evaluate("/", document);
			response.close();
			
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				client.close();
			}
	
		Assert.assertTrue(parsedXml.contains("2014/"));
	}
	
	@Test
	public void testNonExistentProject(){
		Client client = ClientBuilder.newClient();
		String parsedXml = "";
		try{
			
			Response response = client.target("http://localhost:8080/assignment3/myeavesdrop/projects/non-existent-project/meetings").request().get();
			
			if(response.getStatus() != 200) throw new RuntimeException("Failed to get meetings");
			
			String xml = response.readEntity(String.class);
			
			InputSource source = new InputSource(new StringReader(xml));
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			org.w3c.dom.Document document = db.parse(source);

			XPathFactory xpathFactory = XPathFactory.newInstance();
			XPath xpath = xpathFactory.newXPath();
			
			parsedXml = xpath.evaluate("/", document);
			response.close();
			
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				client.close();
			}
		
		Assert.assertTrue(parsedXml.contains("Project non-existent-project does not exist"));
	}
}
