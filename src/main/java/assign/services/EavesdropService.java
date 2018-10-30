package assign.services;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import assign.domain.Meetings;
import assign.domain.Projects;

public class EavesdropService {

	public Projects retrieveProjects() {
		Projects y = new Projects();
		Document doc = null;

		try {
			doc = Jsoup.connect("http://eavesdrop.openstack.org/meetings/").get();
		} catch (Exception e) {
			e.printStackTrace();
		}

		Elements links = doc.getElementsByTag("a");

		for (Element link : links) {
			if (!link.text().equals("Name") && !link.text().equals("Last modified") && !link.text().equals("Size")
					&& !link.text().equals("Description") && !link.text().equals("Parent Directory")) {
				y.addProject(link.text());
			}
		}

		return y;
	}

	public Meetings retrieveProjectMeetings(String project) {
		Meetings m = new Meetings();

		Document doc = null;
		Elements links = new Elements();

		try {
			doc = Jsoup.connect("http://eavesdrop.openstack.org/meetings/" + project).get();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (doc != null) {
			links = doc.getElementsByTag("a");
		} else {
			return null;
		}

		for (Element link : links) {
			if (!link.text().equals("Name") && !link.text().equals("Last modified") && !link.text().equals("Size")
					&& !link.text().equals("Description") && !link.text().equals("Parent Directory")) {
				m.addYears(link.text());
			}
		}

		if (m.getYears().isEmpty()) {
			m.addYears("Project " + project + " does not exist");
		}

		return m;
	}

}
