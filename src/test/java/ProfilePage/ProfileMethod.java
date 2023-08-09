package ProfilePage;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.time.Duration;

public class ProfileMethod {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public ProfileMethod(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void clickLoginLink() {
        WebElement loginLink = driver.findElement(By.id("nav-link-login"));
        wait.until(ExpectedConditions.elementToBeClickable(loginLink));
        loginLink.click();
    }

    public void enterUsername(String username) {
        wait.until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4300/users/login"));
        WebElement usernameInput = driver.findElement(By.id("defaultLoginFormUsername"));
        usernameInput.sendKeys(username);
    }

    public void enterPassword(String password) {
        WebElement passwordInput = driver.findElement(By.id("defaultLoginFormPassword"));
        passwordInput.sendKeys(password);
    }

    public void clickSignInButton() {
        WebElement signInButton = driver.findElement(By.id("sign-in-button"));
        wait.until(ExpectedConditions.elementToBeClickable(signInButton));
        signInButton.click();
    }

    public void clickProfileLink() {
        wait.until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4300/posts/all"));
        WebElement profileLink = driver.findElement(By.id("nav-link-profile"));
        wait.until(ExpectedConditions.elementToBeClickable(profileLink));
        profileLink.click();
    }

    public void clickFollowersCount() {
        wait.until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4300/users/4619"));
        WebElement followersCount = driver.findElement(By.id("followers"));
        wait.until(ExpectedConditions.elementToBeClickable(followersCount));
        followersCount.click();
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ESCAPE).perform();
    }

    public void clickFollowingCount() {
        WebElement followingCount = driver.findElement(By.id("following"));
        wait.until(ExpectedConditions.elementToBeClickable(followingCount));
        followingCount.click();
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ESCAPE).perform();
    }

    public void clickModifyProfile(){
        WebElement modifyProfile = driver.findElement(By.xpath("//i[@class='fas fa-user-edit ng-star-inserted']"));
        wait.until(ExpectedConditions.elementToBeClickable(modifyProfile));
        modifyProfile.click();
        WebElement text = driver.findElement(By.xpath("//h4[contains (text(), 'Modify Your Profile')]"));
        wait.until(ExpectedConditions.visibilityOf(text));
        WebElement saveButton = driver.findElement(By.xpath("//button[@class='btn btn-primary']"));
        wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        saveButton.click();
    }

    public void hoverModifyProfileElement(){
        wait.until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4300/users/4619"));
        WebElement modifyProfile = driver.findElement(By.xpath("//i[@class='fas fa-user-edit ng-star-inserted']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(modifyProfile).perform();
    }

    public void hoverProfilePicture(){
        WebElement profilePicture = driver.findElement(By.xpath("//div[@class='image-container']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(profilePicture).perform();
    }

    public void hoverNewPost(){
        WebElement newPost = driver.findElement(By.xpath("//div[@class='new-post-btn btn btn-primary ng-star-inserted']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(newPost).perform();
    }

    public void publicPost(File file){
        try {
            WebElement newPost = driver.findElement(By.xpath("//i[@class='far fa-plus-square fa-lg']"));
            newPost.click();
            wait.until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4300/posts/create"));
            WebElement uploadImage = driver.findElement(By.cssSelector(".file[type='file']"));
            uploadImage.sendKeys(file.getAbsolutePath());
            WebElement caption = driver.findElement(By.name("caption"));
            caption.sendKeys("I'm a cow");
            WebElement createPostButton = driver.findElement(By.id("create-post"));
            createPostButton.click();
            driver.navigate().back();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void clickPost(){
        try {
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
            Thread.sleep(2000);
            WebElement post = driver.findElement(By.xpath("(//div[@class='post-img'])[1]"));
            post.click();
            WebElement makePostPrivate = driver.findElement(By.xpath("//i[@class='fas fa-unlock ng-star-inserted']"));
            wait.until(ExpectedConditions.elementToBeClickable(makePostPrivate));
            makePostPrivate.click();
            WebElement postMessage = driver.findElement(By.xpath("//div[@class='toast-bottom-right toast-container']"));
            wait.until(ExpectedConditions.visibilityOf(postMessage));
            WebElement writeComment = driver.findElement(By.xpath("(//input[@type='text'])[2]"));
            wait.until(ExpectedConditions.elementToBeClickable(writeComment));
            writeComment.sendKeys("Nice cow");
            WebElement deleteButton = driver.findElement(By.xpath("//a[contains( text(), 'Delete post')]"));
            wait.until(ExpectedConditions.elementToBeClickable(deleteButton));
            deleteButton.click();
            WebElement confirmMessage = driver.findElement(By.xpath(" //button[contains (text(), 'Yes')]"));
            confirmMessage.click();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void privatePost(File file){
        try {
            wait.until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4300/users/4619"));
            WebElement newPost = driver.findElement(By.xpath("//i[@class='far fa-plus-square fa-lg']"));
            wait.until(ExpectedConditions.elementToBeClickable(newPost));
            newPost.click();
            wait.until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4300/posts/create"));
            WebElement uploadImage = driver.findElement(By.cssSelector(".file[type='file']"));
            uploadImage.sendKeys(file.getAbsolutePath());
            WebElement caption = driver.findElement(By.name("caption"));
            caption.sendKeys("Black and White cow");
            WebElement privateSwitch = driver.findElement(By.xpath("//label[@class='post-status-label custom-control-label active']"));
            privateSwitch.click();
            WebElement createPostButton = driver.findElement(By.id("create-post"));
            createPostButton.click();
            driver.navigate().back();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void uploadProfileImage(File file){
        WebElement profileImage = driver.findElement(By.id("upload-img"));
        profileImage.sendKeys(file.getAbsolutePath());
        WebElement confirmText = driver.findElement(By.id("toast-container"));
        wait.until(ExpectedConditions.visibilityOf(confirmText));
    }
}



