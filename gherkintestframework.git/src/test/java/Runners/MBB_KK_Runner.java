package Runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.automation.listener.ListenerTestNG;
import org.testng.annotations.Listeners;

@CucumberOptions(
        features ="src/test/resources/features/MBB/menuInformasiKartuKredit.feature",
        glue = {"StepDefinitions"},
        dryRun = false
        ,tags = "@test0"
)
@Listeners(ListenerTestNG.class)

public class MBB_KK_Runner extends AbstractTestNGCucumberTests {

}
