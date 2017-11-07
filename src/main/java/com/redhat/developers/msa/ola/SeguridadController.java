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
	
    @RequestMapping(method = RequestMethod.GET, value = "/credencial", produces = "application/json")
    @ApiOperation("regresa el token credencial o corporativo")
    public String getTokenCredencial() {
		return service.getTokenCredencial();
    }
	
    @CrossOrigin
	@RequestMapping(value="/credencial/", method=RequestMethod.POST)
	@ApiOperation(value = "Obtener el token credencial o corporativo ( en base en el login de un usuario y pass fijos )", response = TokenCredencial.class, produces = "application/json")
    public @ResponseBody TokenCredencial posttokencorporativo(@RequestBody CredencialLogin login) {
		//return service.getTokenCredencial(login);
    	return new TokenCredencial("QjQ2NTFDNjFGNTA5RkYwRjZGMEI0NEYyIzE4MC4xMDEuMTE2LjkjMTQ4MzYzMDgxMTMyNSNQRDk0Yld3Z2RtVnljMmx2YmowaU1TNHdJaUJsYm1OdlpHbHVaejBpU1ZOUExUZzROVGt0TVNJL1BqeDBiMnRsYmtSbFptbHVhWFJwYjI0K1BHNWhiV1UrUW1GeWNtbGxiblJ2Y3lCR1pYSnlkWHBqWVN3Z1JHRnRZWEpwY3p3dmJtRnRaVDQ4WVd4cFlYTStiakF4TURJM056d3ZZV3hwWVhNK1BIVnpaWEpKUkQ1dU1ERXdNamMzUEM5MWMyVnlTVVErUEM5MGIydGxia1JsWm1sdWFYUnBiMjQrI0RFU2VkZS9DQkMvUEtDUzVQYWRkaW5nI3YxI21leGljb2ludHJhbmV0I05PVF9VU0VEI1NIQTF3aXRoUlNBI3JOeitDV2xNUk1GQ3NSL2cwSUFNK0pQdUdlOUxFZjZEY29QSUJza2FKQmtXUVVPd3ovQ0phQktndndXcE5VVEEwL2dwdHQ3Z1hHM3hlZmVSeHdRR1ZWbnhmdEEyRWZ4bERmNkFDRkdKelFBdmZqVDNaVXgwM1YzMVJYZnllN3J6eSs5ZGR3c041cFl5eEtuVnZBL2lpQWRHc3RjRUF1UE8wck5lWjM3ZFpSND0=");
    }
	
	@CrossOrigin
	@RequestMapping(value="/accesstoken/", method=RequestMethod.POST)
	@ApiOperation(value = "Obtener el AccessToken", response = RegresoAccessToken.class, produces = "application/json")
    public @ResponseBody RegresoAccessToken postaccesstoken(@RequestBody CredencialAcessToken credencial) {
		return new RegresoAccessToken("bearer", "AAS2NTFDNjFGNTA5RkYwRjZGMEI0NEYyIzE4MC4xMDEuMTE2LjkjMTQ4MzYzMDgxMTMyNSNQRDk0Yld3Z2RtVnljMmx2YmowaU1TNHdJaUJsYm1OdlpHbHVaejBpU1ZOUExUZzROVGt0TVNJL1BqeDBiMnRsYmtSbFptbHVhWFJwYjI0K1BHNWhiV1UrUW1GeWNtbGxiblJ2Y3lCR1pYSnlkWHBqWVN3Z1JHRnRZWEpwY3p3dmJtRnRaVDQ4WVd4cFlYTStiakF4TURJM056d3ZZV3hwWVhNK1BIVnpaWEpKUkQ1dU1ERXdNamMzUEM5MWMyVnlTVVErUEM5MGIydGxia1JsWm1sdWFYUnBiMjQrI0RFU2VkZS9DQkMvUEtDUzVQYWRkaW5nI3YxI21leGljb2ludHJhbmV0I05PVF9VU0VEI1NIQTF3aXRoUlNBI3JOeitDV2xNUk1GQ3NSL2cwSUFNK0pQdUdlOUxFZjZEY29QSUJza2FKQmtXUVVPd3ovQ0phQktndndXcE5VVEEwL2dwdHQ3Z1hHM3hlZmVSeHdRR1ZWbnhmdEEyRWZ4bERmNkFDRkdKelFBdmZqVDNaVXgwM1YzMVJYZnllN3J6eSs5ZGR3c041cFl5eEtuVnZBL2lpQWRHc3RjRUF1UE8wck5lWjM3ZFpSND0=", 3600, "corporate_tables.read corporate_accounts.read corporate_cards.read corporate_customers.read customers.read customers_data.read retail_accounts.read retail_cards.read retail_customers.read audit.write");
    }

   
}
