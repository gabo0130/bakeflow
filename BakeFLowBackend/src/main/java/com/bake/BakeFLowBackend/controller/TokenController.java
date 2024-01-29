package com.bake.BakeFLowBackend.controller;

import com.bake.BakeFLowBackend.dto.response.BaseResponse;
import com.bake.BakeFLowBackend.dto.response.ErrorResponse;
import com.bake.BakeFLowBackend.dto.response.SuccessResponse;
import com.bake.BakeFLowBackend.dto.response.TokenResponse;
import com.bake.BakeFLowBackend.security.AuthCredentials;
import com.bake.BakeFLowBackend.security.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/token")
@CrossOrigin(origins = "http://localhost:5173")
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
            return ResponseEntity.status(200).body(BaseResponse.success("Token generado con exito", tokenResponse));
        } catch (Exception e) {;
            return ResponseEntity.status(401).body(BaseResponse.error("Credenciales incorrectas", null));
        }
    }
}
