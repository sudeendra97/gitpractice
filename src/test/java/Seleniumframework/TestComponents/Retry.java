package Seleniumframework.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

    // Make count a class member to persist its value between retries
    private int count = 0;
    private int maxretry = 1;

    @Override
    public boolean retry(ITestResult result) {
        // Check if the count is less than maxretry
        if (count < maxretry) {
            count++; // Increment count only on retry attempt
            return true; // Retry the test
        }
        return false; // No more retries
    }
}

