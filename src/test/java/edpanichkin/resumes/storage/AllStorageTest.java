package edpanichkin.resumes.storage;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
        ArrayStorageTest.class,
        SortedStorageTest.class,
        ListStorageTest.class,
        MapUuidStorageTest.class,
        MapResumeStorageTest.class,
        ObjectFileStorageTest.class,
        ObjectPathStorageTest.class,
        XMLPathStorageTest.class,
        JsonPathStorageTest.class,
        DataPathStorageTest.class,
        SqlStorageTest.class
})
public class AllStorageTest {
}
