import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testcontainers.containers.BrowserWebDriverContainer;

// from https://github.com/testcontainers/testcontainers-java
@SuppressWarnings("resource")
public class FirefoxWebDriverContainerTest extends BaseWebDriverContainerTest {

    @Rule
    public BrowserWebDriverContainer<?> firefox =
            new BrowserWebDriverContainer<>()
            .withCapabilities(new FirefoxOptions())
            .withNetwork(NETWORK);

    @Before
    public void checkBrowserIsIndeedFirefox() {
        assertBrowserNameIs(firefox, "firefox");
    }

    @Test
    public void simpleExploreTest() {
        doSimpleExplore(firefox);
    }

    @Test
    public void rickRollingIsAMeme() {
        checkRickRolling(firefox);
    }
}