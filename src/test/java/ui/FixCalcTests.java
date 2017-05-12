package ui;

/**
 *
 * @author NamelessOne
 */

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Ignore;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import org.openqa.selenium.By;


public class FixCalcTests {
    @BeforeClass
    public static void setUp(){
    System.setProperty("webdriver.chrome.driver", "D:\\UsersFolders\\Tzeentch"
            + "\\Downloads\\chromedriver_win32\\chromedriver.exe");
    System.setProperty("selenide.browser", "Chrome");
    open("http://rulkov.ru/qa");  
  }
    @Test
    public void Test1() {
        String username = "WQA";
        String password = "12345";
                
        //StartApplet
        $(byXpath("//*[@id=\"bs-example-navbar-collapse-6\"]/ul/li[2]/a"))
                .click();
        $(byXpath("/html/body/div[2]/div/div/div/form/div[3]/button"))
                .shouldHave(attribute("disabled"));
        
        FillCredentials(username, password);
        ClearCredentials();
        //Check Authorization form in scope of Test 2
        $(byXpath("/html/body/div[2]/div/div/div/form/div[1]/span"))
                .shouldHave(text("Поле Логин обязательное для заполнения"));
        $(byXpath("/html/body/div[2]/div/div/div/form/div[2]/span"))
                .shouldHave(text("Поле Пароль обязательное для заполнения"));
        $(byXpath("/html/body/div[2]/div/div/div/form/div[3]/button"))
                .shouldHave(attribute("disabled"));
        
        //Proceed to Test1
        FillCredentials(username, password);
        HitLoginButton();
        // Checks 
        $(byXpath("/html/body/div[2]/div/h1"))
                .shouldHave(text("Операция сложения"));
        $(By.name("value1")).shouldBe(visible);
        $(By.name("value2")).shouldBe(visible);
        $(By.name("value3")).shouldBe(visible);
        $(byXpath("/html/body/div[2]/div/div/div[2]"
                + "/fix-calculator/div/div[2]/input[1]"))
                .shouldHave(attribute("disabled"));
        $(byXpath("/html/body/div[2]/div/div/div[2]"
                + "/fix-calculator/div/div[2]/input[2]"))
                .shouldHave(attribute("disabled"));
    }
    
    @Ignore ("Functionality is not implemented yet")
    @Test
    public void Test3() {
        String username = "WQAaaaa";
        String password = "123455555";
        
        FillCredentials(username, password);
        HitLoginButton();
        /*
        Сообщение об ошибке авторизации не имплементировано
        */
        
    }
    
    @Test
    public void Test4() {
        //MakeAddition
        Integer value1 = 0;
        Integer value2 = 9999;
        Integer value3 = 10000;
        Integer result1 = value1 + value2 + value3;
        String string = "Some string of symbols";

        $(By.name("value1")).shouldBe(visible);
        $(By.name("value2")).shouldBe(visible);
        $(By.name("value3")).shouldBe(visible);
        
        //Test 4
        EnterValues(string, string, string);
        //Buttons must be disabled
        $(byXpath("/html/body/div[2]/div/div/div[2]/fix-calculator"
                + "/div/div[2]/input[1]")).shouldHave(attribute("disabled"));
        $(byXpath("/html/body/div[2]/div/div/div[2]/fix-calculator"
                + "/div/div[2]/input[2]")).shouldHave(attribute("disabled"));
        
        //Test 5: Steps1-4
        EnterValues(value1.toString(), value2.toString(), value3.toString());
        HitAdvanceButton();
        ConfirmationStep(true);
        WaitCalculation();
        CheckResult(result1.toString());
        ReturnToStart();
        //Test 5: Steps5.1
        EnterValues(value2.toString(), value1.toString(), value3.toString());
        HitAdvanceButton();
        ConfirmationStep(true);
        WaitCalculation();
        CheckResult(result1.toString());
        ReturnToStart();
        //Test 5: Steps5.2
        EnterValues(value1.toString(), value3.toString(), value2.toString());
        HitAdvanceButton();
        ConfirmationStep(true);
        WaitCalculation();
        CheckResult(result1.toString());
        ReturnToStart();
        //Test 5: Steps5.3
        EnterValues(value3.toString(), value1.toString(), value2.toString());
        HitAdvanceButton();
        ConfirmationStep(true);
        WaitCalculation();
        CheckResult(result1.toString());
        ReturnToStart();
  }
    //Fail test
    @Ignore("Functionality is not implemented yet") 
    @Test
    public void Test6() {
        //EditValues
        Integer value1 = 0;
        Integer value2 = 9999;
        Integer value3 = 10000;
        Integer NewValue = 1;
        Integer result1 = NewValue + value2 + value3;
        
        $(By.name("value1")).shouldBe(visible);
        $(By.name("value2")).shouldBe(visible);
        $(By.name("value3")).shouldBe(visible);
        
        EnterValues(value1.toString(), value2.toString(), value3.toString());
        HitAdvanceButton();
        ConfirmationStep(false);
        EnterValues(NewValue.toString(), value2.toString(), value3.toString());
        HitAdvanceButton();
        ConfirmationStep(true);
        WaitCalculation();
        CheckResult(result1.toString());
      
    }

    @Test
    public void Test7() {
        //EvenCheck
        Integer value1 = 1;
        Integer value2 = 2;
        Integer value3 = 3;
        Integer result1 = value1 + value2 + value3;
        
        EnterValues(value3.toString(), value1.toString(), value2.toString());
        HitAdvanceButton();
        ConfirmationStep(true);
        WaitCalculation();
        $(byXpath("/html/body/div[2]/div/div/div[2]/fix-calculator"
                + "/div/div[1]/div/table/tbody/tr[4]/td"))
                .shouldHave(text(result1.toString())); 
        //bg-success
        $(byXpath("/html/body/div[2]/div/div/div[2]/fix-calculator"
                + "/div/div[1]/div/table/tbody/tr[4]"))
                .shouldHave(cssClass("bg-success"));
        ReturnToStart();
    }
    
    @Test
    public void Test8() {
        //InfinityCheck
        String value = "89884656743115795386465259539451236680898848947115328636"
                + "7150405788663379027504815663542386612037680105600569399356966"
                + "7882939488440720831124642371531973706218888394671243274263815"
                + "1109800623047059726541476042502884419075341171231440736956555"
                + "2704136185816752553422931491199736229692398581524176781648121"
                + "12068608"; // 2**1023
        String result1 = "Infinity";
        
        EnterValues(value, value, value);
        HitAdvanceButton();
        ConfirmationStep(true);
        WaitCalculation();
        CheckResult(result1);
        ReturnToStart();
    }
    
    @Test
    public void Test9() {
        //LogOutApp
        $(byXpath("//*[@id=\"bs-example-navbar-collapse-6\"]/ul/li[4]/a"))
                .click();
        $(byXpath("/html/body/div[2]/div/h1")).shouldHave(text("Welcome"));
        $(byXpath("//*[@id=\"bs-example-navbar-collapse-6\"]/ul/li[2]/a"))
                .shouldBe(visible);
    }
    
    public static void FillCredentials(String user, String pwd) {
        $(By.name("username")).val(user);
        $(By.name("password")).val(pwd);
    }
    public static void ClearCredentials() {
        $(By.name("username")).clear();
        $(By.name("password")).clear();
    }
    public static void HitLoginButton() {
        $(byXpath("/html/body/div[2]/div/div/div/form/div[3]/button")).click();
    }
    
    public static void EnterValues(String value1, String value2, String value3){
        //Ввод значений
        $(By.name("value1")).val(value1);
        $(By.name("value2")).val(value2);
        $(By.name("value3")).val(value3);
               
    }
    
    public static void HitAdvanceButton() {
        $(byXpath("/html/body/div[2]/div/div/div[2]"
                + "/fix-calculator/div/div[2]/input[2]")).click(); 
    }
    
    public static void ConfirmationStep(boolean proceed){
        if (proceed) {
            $(byXpath("/html/body/div[2]/div/div/div[2]"
                    + "/fix-calculator/div/div[1]/div/h4")).
                    shouldHave(text("Подтверждение данных"));
            $(byXpath("/html/body/div[2]/div/div/div[2]"
                    + "/fix-calculator/div/div[2]/input[2]")).click();
        }else {
            
            $(byXpath("/html/body/div[2]/div/div/div[2]"
                    + "/fix-calculator/div/div[1]/div/h4")).
                    shouldHave(text("Подтверждение данных"));
            $(byXpath("/html/body/div[2]/div/div/div[2]"
                    + "/fix-calculator/div/div[2]/input[1]")).click();
        }
        
    }
    
    public static void WaitCalculation(){
        $(byText("Производятся расчеты, пожалуйста подождите..."))
            .waitUntil(disappears, 6000);
    }
  
    public static void CheckResult(String res){
        //Check result 
        $(byXpath("/html/body/div[2]/div/div/div[2]/fix-calculator"
                + "/div/div[1]/div/table/tbody/tr[4]/td"))
                .shouldHave(text(res)); 
    }
    public static void ReturnToStart() {
        //And return to start
        $(byXpath("/html/body/div[2]/div/div/div[2]/fix-calculator"
                + "/div/div[2]/input[1]"))
                .click();
    } 
  
}