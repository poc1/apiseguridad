package com.redhat.developers.msa.ola;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.redhat.developers.msa.pojo.CredencialAcessToken;
import com.redhat.developers.msa.pojo.CredencialLogin;
import com.redhat.developers.msa.pojo.RegresoAccessToken;
import com.redhat.developers.msa.pojo.TokenCredencial;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(description = "Seguridad Controller")
public class SeguridadController {

	public final SeguridadService service;

	
	@Autowired
	public SeguridadController(SeguridadService service) {
			this.service = service;
	}
	
	/**
	 * Obtener el token corporativo
	 * @return
	 */
    @RequestMapping(value = "/credencial", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation("Obtener el token credencial o corporativo")
    public String getTokenCredencial() {
		return service.getTokenCredencial();
    }
	
    /**
     * Obtener el token corporativo 
     * @param login
     * @return
     */
	@RequestMapping(value="/credencial/", method=RequestMethod.POST)
	@ApiOperation(value = "Obtener el token credencial o corporativo ( en base en el login de un usuario y pass fijos )", response = TokenCredencial.class, produces = "application/json")
    public @ResponseBody String posttokencorporativo(@RequestBody CredencialLogin login) {
    	return service.getTokenCredencial(login);    	
    }
	
    /**
     * Obtener el access token
     * @param credencial
     * @return
     */
	@RequestMapping(value="/accesstoken/", method=RequestMethod.POST)
	@ApiOperation(value = "Obtener el AccessToken", response = RegresoAccessToken.class, produces = "application/json")
    public @ResponseBody String postaccesstoken(@RequestBody CredencialAcessToken credencial) {
		return service.postaccesstoken(credencial);
    }

   
}
