package com.bossgiay.testListeners;

import com.bossgiay.testScripts.TestAbstractClass;
import com.google.errorprone.annotations.ForOverride;
import com.google.errorprone.annotations.Var;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListeners extends TestAbstractClass
        implements ITestListener {


    @Var
    public static boolean checkIfTestCaseIsPassed;
    public static boolean checkIfTestCaseIsFailed;
    public static boolean checkIfTestCaseIsSkipped;

    private void getErrorFailTest(ITestResult iTestResult) {
        log.error(iTestResult.getTestClass().getName());
        log.error(iTestResult.getThrowable());
    }

    @ForOverride
    public void onFinish(ITestContext onFinishTestCase) {
        log.info("\n\nFINISHED TESTING");
    }

    @ForOverride()
    public void onStart(ITestContext onStartTestCase) {
        log.info("\n\nSTART TESTING\n");
    }

    @ForOverride()
    public void onTestFailedButWithinSuccessPercentage(ITestResult successPercent) {
        log.info("\n Test case's status: "+ successPercent.getStatus());
    }

    @Override()
    public void onTestFailure(ITestResult failedTestCase) {
        System.out.println("\nTest case "+ failedTestCase.getName()+ " being failed!\n");
        getErrorFailTest(failedTestCase);
        checkIfTestCaseIsFailed=true;
    }

    @Override()
    public void onTestSkipped(ITestResult skippedTestCase) {
        System.out.println("\nTest case "+ skippedTestCase.getName()+ " was flown! \n");
        checkIfTestCaseIsSkipped=true;
    }

    @Override()
    public void onTestStart(ITestResult startedTestCase) {
        System.out.println("Test case "+ startedTestCase.getName()+ " was triggered!\n");
    }

    @ForOverride()
    public void onTestSuccess(ITestResult passedTestCase) {
        System.out.println("\nTest case "+ passedTestCase.getName()+ " was successful!\n");
        checkIfTestCaseIsPassed=true;
    }
}
