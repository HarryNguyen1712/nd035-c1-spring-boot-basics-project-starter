package com.udacity.jwdnd.course1.cloudstorage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.File;
import java.time.Duration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

  @LocalServerPort
  private int port;

  private WebDriver driver;

  @BeforeAll
  static void beforeAll() {
    WebDriverManager.chromedriver().setup();
  }

  @BeforeEach
  public void beforeEach() {
    this.driver = new ChromeDriver();
  }

  @AfterEach
  public void afterEach() {
    if (this.driver != null) {
      driver.quit();
    }
  }

  @Test
  public void getLoginPage() {
    driver.get("http://localhost:" + this.port + "/login");
    Assertions.assertEquals("Login", driver.getTitle());
  }

  /**
   * PLEASE DO NOT DELETE THIS method.
   * Helper method for Udacity-supplied sanity checks.
   **/
  private void doMockSignUp(String firstName, String lastName, String userName, String password) {
    // Create a dummy account for logging in later.

    // Visit the sign-up page.
    WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    driver.get("http://localhost:" + this.port + "/signup");
    webDriverWait.until(ExpectedConditions.titleContains("Sign Up"));

    // Fill out credentials
    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputFirstName")));
    WebElement inputFirstName = driver.findElement(By.id("inputFirstName"));
    inputFirstName.click();
    inputFirstName.sendKeys(firstName);

    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputLastName")));
    WebElement inputLastName = driver.findElement(By.id("inputLastName"));
    inputLastName.click();
    inputLastName.sendKeys(lastName);

    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputUsername")));
    WebElement inputUsername = driver.findElement(By.id("inputUsername"));
    inputUsername.click();
    inputUsername.sendKeys(userName);

    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputPassword")));
    WebElement inputPassword = driver.findElement(By.id("inputPassword"));
    inputPassword.click();
    inputPassword.sendKeys(password);

    // Attempt to sign up.
    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("buttonSignUp")));
    WebElement buttonSignUp = driver.findElement(By.id("buttonSignUp"));
    buttonSignUp.click();

		/* Check that the sign up was successful. 
		// You may have to modify the element "success-msg" and the sign-up 
		// success message below depending on the rest of your code.
		*/
    assertTrue(
        driver.findElement(By.id("success-msg")).getText().contains("You successfully signed up!"));
  }


  /**
   * PLEASE DO NOT DELETE THIS method.
   * Helper method for Udacity-supplied sanity checks.
   **/
  private void doLogIn(String userName, String password) {
    // Log in to our dummy account.
    driver.get("http://localhost:" + this.port + "/login");
    WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));

    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputUsername")));
    WebElement loginUserName = driver.findElement(By.id("inputUsername"));
    loginUserName.click();
    loginUserName.sendKeys(userName);

    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputPassword")));
    WebElement loginPassword = driver.findElement(By.id("inputPassword"));
    loginPassword.click();
    loginPassword.sendKeys(password);

    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-button")));
    WebElement loginButton = driver.findElement(By.id("login-button"));
    loginButton.click();

    webDriverWait.until(ExpectedConditions.titleContains("Home"));

  }

  private void doInputNote(String title, String description) {
    // Log in to our dummy account.
    driver.get("http://localhost:" + this.port + "/home");
    WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));

    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-notes-tab")));
    WebElement navNote = driver.findElement(By.id("nav-notes-tab"));
    navNote.click();

    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("addNote")));
    WebElement addNote = driver.findElement(By.id("addNote"));
    addNote.click();

    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("note-title")));
    WebElement noteTitle = driver.findElement(By.id("note-title"));
    noteTitle.click();
    noteTitle.sendKeys(title);

    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("note-description")));
    WebElement noteDescription = driver.findElement(By.id("note-description"));
    noteDescription.click();
    noteDescription.sendKeys(description);

    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("submitNote")));
    WebElement submitNote = driver.findElement(By.id("submitNote"));
    submitNote.click();

    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-notes-tab")));
    WebElement navNoteAfterSubmit = driver.findElement(By.id("nav-notes-tab"));
    navNoteAfterSubmit.click();
  }

  private void doUpdateNote(String title, String description) {
    // Log in to our dummy account.
    WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));

    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-notes-tab")));
    WebElement navNote = driver.findElement(By.id("nav-notes-tab"));
    navNote.click();

    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btn-edit-note")));
    WebElement addNote = driver.findElement(By.id("btn-edit-note"));
    addNote.click();

    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("note-title")));
    WebElement noteTitle = driver.findElement(By.id("note-title"));
    noteTitle.click();
    noteTitle.clear();
    noteTitle.sendKeys(title);

    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("note-description")));
    WebElement noteDescription = driver.findElement(By.id("note-description"));
    noteDescription.click();
    noteDescription.clear();
    noteDescription.sendKeys(description);

    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("submitNote")));
    WebElement submitNote = driver.findElement(By.id("submitNote"));
    submitNote.click();

    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-notes-tab")));
    WebElement navNoteAfterSubmit = driver.findElement(By.id("nav-notes-tab"));
    navNoteAfterSubmit.click();
    webDriverWait.until(ExpectedConditions.titleContains("Home"));
    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(title)));

  }

  private void doMockLogOut() {
    driver.get("http://localhost:" + this.port + "/home");
    WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));

    // Attempt to logout.
    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("buttonLogout")));
    WebElement buttonSignUp = driver.findElement(By.id("buttonLogout"));
    buttonSignUp.click();

  }

  private void doDeleteNote(String title) {
    driver.get("http://localhost:" + this.port + "/home");
    WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));

    // Attempt to logout.
    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-notes-tab")));
    WebElement navNote = driver.findElement(By.id("nav-notes-tab"));
    navNote.click();

    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(title)));
    WebElement addNote = driver.findElement(By.id(title));
    addNote.click();

  }

  /**
	 * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the
	 * rest of your code.
	 * This test is provided by Udacity to perform some basic sanity testing of
	 * your code to ensure that it meets certain rubric criteria.
	 * <p>
	 * If this test is failing, please ensure that you are handling redirecting users
	 * back to the login page after a successful sign-up.
	 * Read more about the requirement in the rubric:
	 * <a href="https://review.udacity.com/#!/rubrics/2724/view">...</a>
	 */
  @Test
  public void testRedirection() {
    // Create a test account
    doMockSignUp("Redirection", "Test", "RT", "123");

    // Check if we have been redirected to the log in page.
    Assertions.assertEquals("http://localhost:" + this.port + "/login", driver.getCurrentUrl());
  }

  /**
	 * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the
	 * rest of your code.
	 * This test is provided by Udacity to perform some basic sanity testing of
	 * your code to ensure that it meets certain rubric criteria.
	 * <p>
	 * If this test is failing, please ensure that you are handling bad URLs
	 * gracefully, for example with a custom error page.
	 * <p>
	 * Read more about custom error pages at:
	 * <a href="https://attacomsian.com/blog/spring-boot-custom-error-page#displaying-custom-error-page">...</a>
	 */
  @Test
  public void testBadUrl() {
    // Create a test account
    doMockSignUp("URL", "Test", "UT", "123");
    doLogIn("UT", "123");

    // Try to access a random made-up URL.
    driver.get("http://localhost:" + this.port + "/some-random-page");
    Assertions.assertFalse(driver.getPageSource().contains("Whitelabel Error Page"));
  }


  /**
	 * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the
	 * rest of your code.
	 * This test is provided by Udacity to perform some basic sanity testing of
	 * your code to ensure that it meets certain rubric criteria.
	 * <p>
	 * If this test is failing, please ensure that you are handling uploading large files (>1MB),
	 * gracefully in your code.
	 * <p>
	 * Read more about file size limits here:
	 * <a href="https://spring.io/guides/gs/uploading-files/">...</a> under the "Tuning File Upload Limits" section.
	 */
  @Test
  public void testLargeUpload() {
    // Create a test account
    doMockSignUp("Large File", "Test", "LFT", "123");
    doLogIn("LFT", "123");

    // Try to upload an arbitrary large file
    WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    String fileName = "upload5m.zip";

    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fileUpload")));
    WebElement fileSelectButton = driver.findElement(By.id("fileUpload"));
    fileSelectButton.sendKeys(new File(fileName).getAbsolutePath());

    WebElement uploadButton = driver.findElement(By.id("uploadButton"));
    uploadButton.click();
    try {
      webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("success")));
    } catch (org.openqa.selenium.TimeoutException e) {
      System.out.println("Large File upload failed");
    }
    Assertions.assertFalse(driver.getPageSource().contains("HTTP Status 403 – Forbidden"));
  }

  @Test
  public void unauthorizedUser() {
    driver.get("http://localhost:" + this.port + "/home");
    Assertions.assertEquals("Login", driver.getTitle());
    driver.get("http://localhost:" + this.port + "/login");
    Assertions.assertEquals("Login", driver.getTitle());
    driver.get("http://localhost:" + this.port + "/signup");
    Assertions.assertEquals("Sign Up", driver.getTitle());
  }

  @Test
  public void homePageIsUnavailableWhenLogOut() {
    doMockSignUp("Large File", "Test", "LFT", "123");
    doLogIn("LFT", "123");
    driver.get("http://localhost:" + this.port + "/home");
    Assertions.assertEquals("Home", driver.getTitle());
    doMockLogOut();
    Assertions.assertEquals("Login", driver.getTitle());
  }

  @Test
  public void createNote() {
    doLogIn("LFT", "123");
    doInputNote("test","test add note");
    assertTrue(driver.getPageSource().contains("test"));
  }

  @Test
  public void updateNote() {
    doLogIn("LFT", "123");
    doInputNote("test","test add note");
    doUpdateNote("update note","test update note");
    assertTrue(driver.getPageSource().contains("update note"));
  }

  @Test
  public void deleteNote() {
    doLogIn("LFT", "123");
    doInputNote("test delete","test delete note");
    doDeleteNote("test delete");
    assertFalse(driver.getPageSource().contains("test delete"));
  }
}
