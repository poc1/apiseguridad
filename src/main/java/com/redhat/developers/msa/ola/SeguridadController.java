/**
 * JBoss, Home of Professional Open Source
 * Copyright 2016, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.redhat.developers.msa.ola;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.redhat.developers.msa.pojo1.CredencialAcessToken;
import com.redhat.developers.msa.pojo1.CredencialLogin;
import com.redhat.developers.msa.pojo1.RegresoAccessToken;
import com.redhat.developers.msa.pojo1.TokenCredencial;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
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
    @RequestMapping(method = RequestMethod.GET, value = "/credencial", produces = "application/json")
    @ApiOperation("Obtener el token credencial o corporativo")
    public String getTokenCredencial() {
		return service.getTokenCredencial();
    }
	
    /**
     * Obtener el token corporativo 
     * @param login
     * @return
     */
    @CrossOrigin
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
	@CrossOrigin
	@RequestMapping(value="/accesstoken/", method=RequestMethod.POST)
	@ApiOperation(value = "Obtener el AccessToken", response = RegresoAccessToken.class, produces = "application/json")
    public @ResponseBody String postaccesstoken(@RequestBody CredencialAcessToken credencial) {
		return service.postaccesstoken(credencial);
    }

   
}
