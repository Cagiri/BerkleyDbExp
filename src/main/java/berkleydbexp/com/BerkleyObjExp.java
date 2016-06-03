package berkleydbexp.com;

import java.io.File;

import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;
import com.sleepycat.persist.EntityStore;
import com.sleepycat.persist.PrimaryIndex;
import com.sleepycat.persist.StoreConfig;
import com.sleepycat.persist.model.Entity;

public class BerkleyObjExp {
	public static void main(String[] args) {
		EnvironmentConfig envConfig = new EnvironmentConfig();
		envConfig.setAllowCreate(true);
		Environment dbEnv = new Environment(new File("C://Temp//berkley2"),envConfig);
		StoreConfig stConf = new StoreConfig();
		stConf.setAllowCreate(true);
		EntityStore store = new EntityStore(dbEnv, "DPLSample", stConf);
		PrimaryIndex< String, Employee> userIndex;
		userIndex = store.getPrimaryIndex(String.class, Employee.class);
		userIndex.putNoReturn(new Employee("1", "Joe", null));//insert
		Employee user = userIndex.get("1");//retrieve
		userIndex.putNoReturn(new Employee("1", "Locker", null));//Update
		
//		Employee s = userIndex.get("1");
		System.out.println(user.empID + " " + user.lastname);
//		userIndex.delete("1");//delete
		store.close();
		dbEnv.close();
	}
}




@Entity 
class Project {
	public Long projects;
}