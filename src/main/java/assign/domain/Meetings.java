package assign.domain;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "meetings")
@XmlAccessorType(XmlAccessType.FIELD)
public class Meetings {
	List<String> year = new ArrayList<String>();
	
	public List<String> getYears(){
		return year;
	}
	
	public void addYears(String year_new){
		year.add(year_new);
	}
}
