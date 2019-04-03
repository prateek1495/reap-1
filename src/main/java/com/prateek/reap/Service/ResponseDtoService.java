package com.prateek.reap.Service;


import com.prateek.reap.Entity.ResponseDto;
import com.prateek.reap.Entity.UserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class ResponseDtoService {

    public ResponseDto getUser(Authentication authentication) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setData(((UserPrincipal) authentication.getPrincipal()).getUser());
        responseDto.setStatus(true);
        return responseDto;
    }
}
