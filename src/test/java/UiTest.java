import com.setup.Configuration;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;

public class UiTest {

    @BeforeTest
    public void setup() throws IOException {
        Configuration.getBrowser();
    }


    @Parameters("noOfRecords")
    @Test
    public void flipkartTest(int noOfRecords) {
        Configuration.flipkartFetchIpadPrice(noOfRecords);
    }

    @AfterTest
    public void closeBrowser() {
        Configuration.driver.close();

    }
}
