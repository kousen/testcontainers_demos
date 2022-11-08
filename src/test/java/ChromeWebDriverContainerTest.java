import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testcontainers.containers.BrowserWebDriverContainer;

// from https://github.com/testcontainers/testcontainers-java
@SuppressWarnings("resource")
public class ChromeWebDriverContainerTest extends BaseWebDriverContainerTest {

    @Rule
    public BrowserWebDriverContainer<?> chrome =
            new BrowserWebDriverContainer<>()
            .withCapabilities(new ChromeOptions())
            .withNetwork(NETWORK);

    @Before
    public void checkBrowserIsIndeedChrome() {
        assertBrowserNameIs(chrome, "chrome");
    }

    @Test
    public void simpleExploreTest() {
        doSimpleExplore(chrome);
    }

    @Test
    public void rickRollingIsAMeme() {
        checkRickRolling(chrome);
    }
}