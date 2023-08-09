package ProfilePage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;

public class TestProfile {
    WebDriver driver;

    @AfterMethod
    void cleanAll(){
        driver.quit();
    }

    @BeforeMethod
    void setup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @DataProvider(name = "generateUserData")
    public Object[][] generateUserData(){
        File image = new File("src/main/resources/images/cow.png");
        return new Object[][]{
                {"panda23", "123456789Aa-", image}
        };
    }

    @Test(dataProvider = "generateUserData")
    public void testProfile(String userName, String password, File file) {
        ProfileMethod profileMethod = new ProfileMethod(driver);

        driver.get("http://training.skillo-bg.com:4300/posts/all");
        profileMethod.clickLoginLink();
        profileMethod.enterUsername(userName);
        profileMethod.enterPassword(password);
        profileMethod.clickSignInButton();
        profileMethod.clickProfileLink();
        profileMethod.clickFollowersCount();
        profileMethod.clickFollowingCount();
        profileMethod.clickModifyProfile();
        profileMethod.hoverModifyProfileElement();
        profileMethod.hoverProfilePicture();
        profileMethod.hoverNewPost();
        profileMethod.publicPost(file);
        profileMethod.clickPost();
        profileMethod.privatePost(file);
        profileMethod.uploadProfileImage(file);
    }
}
