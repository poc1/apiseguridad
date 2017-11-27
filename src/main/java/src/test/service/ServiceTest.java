package src.test.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.redhat.developers.msa.ola.SeguridadService;
import com.redhat.developers.msa.pojo.CredencialLogin;
import com.redhat.developers.msa.pojo.CredencialAcessToken;

import src.test.java.AbstractTest;

//@Transactional
public class ServiceTest extends AbstractTest {
	
	@Autowired
	SeguridadService service;

	@Before
	public void setUp()
	{
		
	}
	
	
	@After
	public void tearDown()
	{
		
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
	
}
