import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.redhat.developers.msa.ola.SeguridadApplication;
import com.redhat.developers.msa.ola.SeguridadService;
import com.redhat.developers.msa.pojo.CredencialAcessToken;
import com.redhat.developers.msa.pojo.CredencialLogin;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = SeguridadApplication.class)
public class SeguridadApplicationTest {
	
	// protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	SeguridadService service;
	
	private static WebDriver driver;
	private static StringBuffer verificationErrors = new StringBuffer();
			
	
	@BeforeClass
	public static void setUp() throws Exception {
		
		// configuracion de ruta chromedriver
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\paul\\Documents\\Librerias\\chromedriver_win32\\chromedriver.exe");
		System.out.println("webdriver.chrome.driver : "  + System.getProperty("webdriver.chrome.driver"));
		
		String error = "";
		try {
			driver = new ChromeDriver();
		    // baseUrl = "https://www.katalon.com/";
		    driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		    //error = "no hay error...";
		}catch(Exception ext) {
			error = ext.getMessage();
		}
		System.out.println(error);
	}
	
	
	@Test
	public void TokenCredencial_Get_Test()
	{
		String tokencredencial = service.getTokenCredencial();
		// estructura esperada
//		{	
//			"token":"9oi02i23948fdjfY0YTIyN2NmIjZCZbQGJr49jnoHYW669ucg5qn21LSIIHptIqIgCZaN687HZn4ymyvzAdUp97lNu57hM_2uDLDlvqxP0pe-r-XdK8eVxpdFMR0kB200d9PHwEuH_3BAgWdHnWAUDqG6lAyz-cNk6AdD-yC3Q8zK-R5zbpBw1A-TAw-B86Ry4-drTPcbQGkQZ1dq6lbg3Vm7H3aUyfHdFVyEmrmTbueqN1ZpaSEFRGkaChxw_LHzGoBLr_ohK7-782es52y9GpcGXuuTYHsjDS_sXTKl8A3z1jjjlow4X7yvs8ga1ElN-Ql4LsISqZzmzv93RkZqfl_ch-P_dbCf49Yol_tuImqpl2pk0X_XEQ3SQTKON8DCOHvsGXAjpktRYJyBzcIfZ4AiFGFGsd5445ffh45gdgFGd436566"
//		}
		Assert.assertNotNull("Se esperaba un valor diferente de nulo",  tokencredencial);
		Assert.assertFalse("La respuesta es incorrecta", !tokencredencial.contains("token"));
	}
	
	
	@Test
	public void TokenCredencial_POST_Test()
	{
		String tokencredencial = service.getTokenCredencial(new CredencialLogin("jose123","jose"));
		// estructura esperada
//		{	
//			"token":"9oi02i23948fdjfY0YTIyN2NmIjZCZbQGJr49jnoHYW669ucg5qn21LSIIHptIqIgCZaN687HZn4ymyvzAdUp97lNu57hM_2uDLDlvqxP0pe-r-XdK8eVxpdFMR0kB200d9PHwEuH_3BAgWdHnWAUDqG6lAyz-cNk6AdD-yC3Q8zK-R5zbpBw1A-TAw-B86Ry4-drTPcbQGkQZ1dq6lbg3Vm7H3aUyfHdFVyEmrmTbueqN1ZpaSEFRGkaChxw_LHzGoBLr_ohK7-782es52y9GpcGXuuTYHsjDS_sXTKl8A3z1jjjlow4X7yvs8ga1ElN-Ql4LsISqZzmzv93RkZqfl_ch-P_dbCf49Yol_tuImqpl2pk0X_XEQ3SQTKON8DCOHvsGXAjpktRYJyBzcIfZ4AiFGFGsd5445ffh45gdgFGd436566"
//		}
		Assert.assertNotNull("Se esperaba un valor diferente de nulo",  tokencredencial);
		Assert.assertFalse("La respuesta es incorrecta", !tokencredencial.contains("token"));
	}
	
	@Test
	public void AccessToken_POST_Test()
	{
		String accesstoken = service.postaccesstoken(new CredencialAcessToken("corporate_tables.read corporate_accounts.read corporate_cards.read corporate_customers.read customers.read customers_data.read retail_accounts.read retail_cards.read retail_customers.read audit.write", "password", ""/*token*/, "dfba0a15-4f12-402f-9795-f604f4a227cf", "xU4sT2iT4sD2fB3aO4bS2pR4bJ4fN5pA7mL8uB7dN5wT8pJ5uP"));
		//  estructura esperada
//		{
//		    "token_type": "bearer",
//		    "access_token": "fGftRReed434532dewrwe7HZn4ymyvzAdUp97lNu57hM_2uDLDlvqxP0pe-r-XdK8eVxpdFMR0kB200d9PHwEuH_3BAgWdHnWAUDqG6lAyz-cNk6AdD-yC3Q8zK-R5zbpBw1A-TAw-B86Ry4-drTPcbQGkQZ1dq6lbg3Vm7H3aUyfHdFVyEmrmTbueqN1ZpaSEFRGkaChxw_LHzGoBLr_ohK7-782es52y9GpcGXuuTYHsjDS_sXTKl8A3z1jjjlow4X7yvs8ga1ElN-Ql4LsISqZzmzv93RkZqfl_ch-P_dbCf49Yol_tuImqpl2pk0X_XEQ3SQTKON8DCOHvsGXAjpktRYJyBzcIfZ4AiFGFGsd5445ffh45gdgFGd436566454576gdfgBfgdfgdGSDGFhghdghyTdfgh565635EGhdfghfTg",
//		    "expires_in": 3600,
//		    "scope": "corporate_tables.read corporate_accounts.read corporate_cards.read corporate_customers.read customers.read customers_data.read retail_accounts.read retail_cards.read retail_customers.read audit.write"
//		}	
		Assert.assertNotNull("Se esperaba un valor diferente de nulo",  accesstoken);
		Assert.assertFalse("La respuesta es incorrecta", !accesstoken.contains("scope"));
	}
	
	
	@Test
	public void testUntitledTestCase() throws Exception {
	    driver.get("https://www.google.com.mx/");
	    driver.findElement(By.id("lst-ib")).click();
	    driver.findElement(By.id("lst-ib")).clear();
	    driver.findElement(By.id("lst-ib")).sendKeys("casa en la playa");
	    driver.findElement(By.id("lst-ib")).sendKeys(Keys.ENTER);	    
	    esperar(2);
	    driver.findElement(By.linkText("Imágenes")).click();
	    esperar(2);
	    driver.findElement(By.name("rF0KFpsUZc9ZkM:")).click();
	    esperar(2);
	    System.out.println("exito en la prueba");
	    Assert.assertTrue(true); 
	    
	}
	
	
	
	@AfterClass
	public static void tearDown() throws Exception {
	    driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	}


	
	public void esperar (int segundos) {
		try {
		Thread.sleep (segundos*1000);
		} catch (Exception e) {
		// Mensaje en caso de que falle
		}
	}

}
