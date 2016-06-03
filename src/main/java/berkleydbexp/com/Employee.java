package berkleydbexp.com;

import com.sleepycat.persist.model.Entity;
import com.sleepycat.persist.model.PrimaryKey;

@Entity
public class Employee {
	@PrimaryKey
	public String empID;
	public String lastname;

//	@SecondaryKey(relate = Relationship.MANY_TO_MANY, onRelatedEntityDelete = DeleteAction.NULLIFY)
	public Long projects;

	public Employee() {
	}

	public Employee(String empID, String lastname, Long projects) {
		this.empID = empID;
		this.lastname = lastname;
		this.projects = projects;
	}
}
