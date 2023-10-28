package com.WebTesting.WebTesting_Hybrid_Framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        WebDriver driver = new ChromeDriver();
        driver.get("https://wipro.udemy.com/course/selenium-real-time-examplesinterview-questions/learn/lecture/33469186#overview");
    }
}
