package berkleydbexp.com;

import java.io.File;

import com.sleepycat.bind.tuple.IntegerBinding;
import com.sleepycat.bind.tuple.StringBinding;
import com.sleepycat.je.Database;
import com.sleepycat.je.DatabaseConfig;
import com.sleepycat.je.DatabaseEntry;
import com.sleepycat.je.DatabaseException;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;
import com.sleepycat.je.OperationStatus;
import com.sleepycat.persist.EntityStore;
import com.sleepycat.persist.PrimaryIndex;
import com.sleepycat.persist.StoreConfig;
import com.sleepycat.persist.model.DeleteAction;
import com.sleepycat.persist.model.Entity;
import com.sleepycat.persist.model.PrimaryKey;
import com.sleepycat.persist.model.Relationship;
import com.sleepycat.persist.model.SecondaryKey;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		Environment db = openBerkleyCon();
		saveData(db);
	}

	private static void saveData(Environment dbEnv) {
		DatabaseConfig dbConf = new DatabaseConfig();
		// db will be created if not exits test
		dbConf.setAllowCreate(true);

		// create/open testDB using config
		Database testDB = dbEnv.openDatabase(null, "testDB", dbConf);

		DatabaseEntry key = new DatabaseEntry();
		DatabaseEntry data = new DatabaseEntry();

		IntegerBinding.intToEntry(2, key);
		StringBinding.stringToEntry("FirstEntry", data);
		testDB.putNoOverwrite(null, key, data);
		IntegerBinding.intToEntry(2, key);
		StringBinding.stringToEntry("SecondEntry", data);
		testDB.putNoOverwrite(null, key, data);
		if (testDB.count() <= 0) {
			IntegerBinding.intToEntry(2, key);
			// assign my name to data
			StringBinding.stringToEntry("SecondEntry", data);

			// insert key/value pair to database
			testDB.put(null, key, data);
		} else {
			IntegerBinding.intToEntry(2, key);

			if ((testDB.get(null, key, data, null) == OperationStatus.SUCCESS)) {
				System.out.println("Key  :" + IntegerBinding.entryToInt(key));
				System.out.println("Data :" + StringBinding.entryToString(data));

			}

		}
		testDB.close();
		dbEnv.close();
	}

	private static Environment openBerkleyCon() {
		try {
			// create a configuration for DB environment
			EnvironmentConfig envConf = new EnvironmentConfig();
			// environment will be created if not exists
			envConf.setAllowCreate(true);

			// open/create the DB environment using config
			return new Environment(new File("C://Temp//berkley"), envConf);

		} catch (DatabaseException dbe) {
			System.out.println("Error :" + dbe.getMessage());
		}
		return null;
	}
}

