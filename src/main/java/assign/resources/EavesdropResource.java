package assign.resources;

import java.io.IOException;
import java.io.OutputStream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import assign.domain.Meetings;
import assign.domain.Output;
//import assign.domain.Project;
import assign.domain.Projects;
import assign.services.EavesdropService;

@Path("/myeavesdrop")
public class EavesdropResource {

	EavesdropService eavesdropService;
	Projects projects;
	Meetings meetings;
	Output error;

	public EavesdropResource() {
		this.eavesdropService = new EavesdropService();
	}

	@GET
	@Path("/projects")
	@Produces("application/xml")
	public StreamingOutput getProjects() {
		projects = this.eavesdropService.retrieveProjects();

		return new StreamingOutput() {
			public void write(OutputStream outputStream) throws IOException, WebApplicationException {
				outputProjects(outputStream, projects);
			}
		};
	}

	@GET
	@Path("/projects/{project}/meetings")
	@Produces("application/xml")
	public StreamingOutput getProjectMeetings(@PathParam("project") String project) {
		meetings = this.eavesdropService.retrieveProjectMeetings(project);

		if (meetings == null) {
			error = new Output();
			error.addError("Project " + project + " does not exist");

			return new StreamingOutput() {
				public void write(OutputStream outputStream) throws IOException, WebApplicationException {
					outputError(outputStream, error);
				}
			};
		} else {
			return new StreamingOutput() {
				public void write(OutputStream outputStream) throws IOException, WebApplicationException {
					outputMeetings(outputStream, meetings);
				}
			};
		}
	}

	protected void outputProjects(OutputStream os, Projects projects) throws IOException {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Projects.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(projects, os);
		} catch (JAXBException jaxb) {
			jaxb.printStackTrace();
			throw new WebApplicationException();
		}
	}

	protected void outputMeetings(OutputStream os, Meetings meetings) throws IOException {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Meetings.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(meetings, os);
		} catch (JAXBException jaxb) {
			jaxb.printStackTrace();
			throw new WebApplicationException();
		}
	}

	protected void outputError(OutputStream os, Output output) throws IOException {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Output.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(output, os);
		} catch (JAXBException jaxb) {
			jaxb.printStackTrace();
			throw new WebApplicationException();
		}
	}
}