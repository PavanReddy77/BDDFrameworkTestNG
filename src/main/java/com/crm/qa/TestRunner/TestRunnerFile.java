package com.crm.qa.TestRunner;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;

@CucumberOptions
		(features = {"src/main/java/com/crm/qa/Features"}, 
		glue= {"com.crm.qa.StepDefinitions"}, 
		plugin = {"pretty", "html:test-output", "junit:junit_xml/cucumber.xml"}, 
		monochrome = true, 
		strict = true,
		dryRun=false,
		tags={"@CRMLoginTest"})
		//tags={"@SanityTest, @RegressionTest"})

public class TestRunnerFile 
{
	private TestNGCucumberRunner testNGCucumberRunner;
	 
    @BeforeClass(alwaysRun = true)
    public void setUpClass() throws Exception 
    {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }
 
    @Test(groups = "cucumber", description = "Runs Cucumber Feature", dataProvider = "features")
    public void feature(CucumberFeatureWrapper cucumberFeature) 
    {
        testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
    }
 
    @DataProvider
    public Object[][] features() 
    {
        return testNGCucumberRunner.provideFeatures();
    }
 
    @AfterClass(alwaysRun = true)
    public void tearDownClass() throws Exception 
    {
        testNGCucumberRunner.finish();
    }
}
