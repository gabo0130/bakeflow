package com.bake.BakeFLowBackend.controller;

import com.bake.BakeFLowBackend.response.BaseResponse;
import com.bake.BakeFLowBackend.response.ErrorResponse;
import com.bake.BakeFLowBackend.response.SuccessResponse;
import com.bake.BakeFLowBackend.response.TokenResponse;
import com.bake.BakeFLowBackend.security.AuthCredentials;
import com.bake.BakeFLowBackend.security.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
public class TokenController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<BaseResponse> getToken(@RequestBody AuthCredentials credentials) {
        TokenResponse tokenResponse = new TokenResponse();
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(credentials.getUsuario(), credentials.getPassword())
            );

            String token = TokenUtils.createToken(authentication.getName());
            tokenResponse.setToken(token);
            return ResponseEntity.status(200).body(new SuccessResponse("Success",tokenResponse));
        } catch (Exception e) {;
            return ResponseEntity.status(401).body(new ErrorResponse("Validacion errada",null));
        }
    }
}
