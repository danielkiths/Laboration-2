package com.example.Laboration2;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Laboration2ApplicationTests {

    private static WebDriver driver;

    @BeforeAll
    static void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("incognito");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://svtplay.se");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"__next\"]/div[2]/div/div[2]/button[2]")));
        WebElement cookieButton = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[2]/div/div[2]/button[2]"));
        cookieButton.click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("fHHyBJ")));
    }




    @DisplayName("Kontrollera att webbplatsens titel stämmer")
    @Test
        void checkWebsiteTitle() {
        String websiteTitle = driver.getTitle();
        assertEquals("SVT Play", websiteTitle, "Titeln verkar inte stämma");
    }

    @DisplayName("Kontrollera att webbplatsens logotyp är synlig")
    @Test
        void checkLogo() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"__next\"]/div/div[3]/div/header/div[2]/div/div/nav/a")));
        WebElement logo = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[3]/div/header/div[2]/div/div/nav/a"));
        boolean result = logo.isDisplayed();
        assertTrue(result, "Loggan verkar inte synas");
    }

    @DisplayName("Kontrollera att namnet på länken 'Start' stämmer")
    @Test
        void checkLinkText1() {
        WebElement linkElement = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[3]/div/header/div[2]/div/div/nav/ul/li[1]/a"));
        String linkText = linkElement.getText();
        assertEquals("START", linkText, "Länktexten verkar inte stämma");
    }

    @DisplayName("Kontrollera att namnet på länken 'Program' stämmer")
    @Test
        void checkLinktext2() {
        WebElement linkElement = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[3]/div/header/div[2]/div/div/nav/ul/li[2]"));
        String linkText = linkElement.getText();
        assertEquals("PROGRAM", linkText, "Länktexten verkar inte stämma");
    }

    @DisplayName("Kontrollera att namnet på länken 'Kanaler' stämmer")
    @Test
        void checkLinktext3() {
        WebElement linkElement = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[3]/div/header/div[2]/div/div/nav/ul/li[3]"));
        String linkText = linkElement.getText();
        assertEquals("KANALER", linkText, "Länktexten verkar inte stämma");
    }


    @DisplayName("Kontrollera att länken för 'Tillgänglighet i SVT Play är synlig")
    @Test
        void checkFooterLInk() {
        WebElement footerlink = driver.findElement(By.xpath("//a[@href=\"https://kontakt.svt.se/guide/tillganglighet\"]"));
        boolean result = footerlink.isDisplayed();
        assertTrue(result, "Länken verkar inte synas");
    }

    @DisplayName("Kontrollera att rätt länktext visas för länken 'Tillgänglighet i SVT Play'")
    @Test
        void checkFooterLinkText() {
        WebElement footerlink = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[3]/div/footer/div/div[5]/div[2]/p[1]/a/span[2]"));
        String text = footerlink.getText();
        assertEquals("Tillgänglighet i SVT Play", text, "Länkens text verkar inte stämma");
    }

    @DisplayName("Följ länken 'Tillgänglighet i SVT Play' och kontrollera huvudrubriken")
    @Test
        void checkHeadLine() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href=\"https://kontakt.svt.se/guide/tillganglighet\"]")));
        driver.findElement(By.xpath("//a[@href=\"https://kontakt.svt.se/guide/tillganglighet\"]")).click();
        String text = driver.findElement(By.className("text-3xl")).getText();
        assertEquals("Så arbetar SVT med tillgänglighet", text, "Rubriken verkar inte stämma");
        driver.navigate().back();
    }

    @DisplayName("Navigera till sidan 'Program' och kontrollera antalet kategorier som visas")
    @Test
        void checkNumberOfCategories() {
        WebElement programElement = driver.findElement(By.xpath("//a[@href=\"/program\"]"));
        programElement.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("article.sc-a9073dc0-0")));
        List<WebElement> categories = driver.findElements(By.cssSelector("article.sc-a9073dc0-0"));
        assertEquals(17, categories.size(), "Antalet kategorier verkar inte stämma");
        driver.navigate().back();
    }

    @DisplayName("Kontrollera antalet stripes som visas i start sidan")
    @Test
        void checkNumberOfStripes() {
        List<WebElement> stripes = driver.findElements(By.cssSelector("a.sc-f796aafd-4"));
        assertEquals(33, stripes.size(), "Antalet stripes verkar inte stämma");
    }

    @DisplayName("Kontrollera titeln i sidan 'Kanaler'")
    @Test
        void checkTitleInKanaler() {
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[3]/div/header/div[2]/div/div/nav/ul/li[3]/a")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"play_main-content\"]/div/h1")));
        String title = driver.findElement(By.cssSelector("h1.sc-c358b5df-0")).getText();
        assertEquals("På SVT just nu", title, "Rubriken i Kanaler verkar inte stämma");
        driver.navigate().back();
    }

    @DisplayName("Kontrollera att länken för dataanvändning visas i 'Inställningar' sidan")
    @Test
        void checkLinkInSettings() {
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[3]/div/footer/div/div[3]/a[1]")).click();
        WebElement link = driver.findElement(By.xpath("//*[@id=\"play_main-content\"]/div/div[1]/div/div/p[2]/a"));
        boolean result = link.isDisplayed();
        assertTrue(result, "Länken verkar inte synas");
        driver.navigate().back();
    }

    @DisplayName("Kontrollera antal genvägar i 'Kontakt' sidan")
    @Test
        void checkNumberOfShortcuts() {
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[3]/div/footer/div/div[3]/a[2]")).click();
        List<WebElement> shortcuts = driver.findElements(By.cssSelector("a.py-6"));
        assertEquals(5, shortcuts.size(), "Antalet genvägar verkar inte stämma");
        driver.navigate().back();
    }

    @DisplayName("Kontrollera att SVT loggan i sidfoten är synlig")
    @Test
        void checkLogotypeInFooter() {
        WebElement logoType = driver.findElement(By.className("sc-87f10045-1"));
        boolean result = logoType.isDisplayed();
        assertTrue(result, "Loggan i sidfoten syns inte");

    }

}
