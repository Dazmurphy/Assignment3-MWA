package assign.domain;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;

@XmlRootElement(name = "projects")
@XmlAccessorType(XmlAccessType.FIELD)
public class Projects {

    private List<String> project = new ArrayList<String>();

    public List<String> getProjects() {
        return project;
    }

    public void addProject(String pr) {
        project.add(pr);
    }
}
