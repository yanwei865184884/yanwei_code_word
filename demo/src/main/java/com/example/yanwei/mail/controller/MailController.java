package com.example.yanwei.mail.controller;

import com.example.yanwei.common.BaseResponse;
import com.example.yanwei.common.SuccessResponse;
import com.example.yanwei.mail.MailApi;
import com.example.yanwei.mail.entity.vo.SendMailRequest;
import com.example.yanwei.mail.entity.vo.SendUserInfoVO;
import com.example.yanwei.mail.tool.MailTools;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yanwei
 */
@RestController
public class MailController implements MailApi {

    /**
     * 邮件发送
     */
    @Override
    public ResponseEntity<BaseResponse> sendMail(SendMailRequest request){
        MailTools.sendMailService(request.getEmailName(),"");
        return ResponseEntity.ok(SuccessResponse.success());
    }

    /**
     * 设置邮件发送人
     */
    @Override
    public ResponseEntity<BaseResponse> setSendUser(SendUserInfoVO sendUserInfoVO){
        MailTools.updateProperties(sendUserInfoVO);
        return ResponseEntity.ok(SuccessResponse.success());
    }




}
