package com.example.yanwei.mail;

import com.example.yanwei.common.BaseResponse;
import com.example.yanwei.mail.entity.vo.SendMailRequest;
import com.example.yanwei.mail.entity.vo.SendUserInfoVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author yanwei
 */
public interface MailApi {

    @RequestMapping(value = "/api/sendMail",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<BaseResponse> sendMail( @RequestBody SendMailRequest request);



    @RequestMapping(value = "/api/setSendUser",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<BaseResponse> setSendUser( @RequestBody SendUserInfoVO request);
}
