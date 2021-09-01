package com.example.yanwei.mail.entity.vo;

import lombok.Data;

/**
 * @author yanwei
 */
@Data
public class SendUserInfoVO {

    private String sendMailUserName;

    private String sendMailPwd;


    public SendUserInfoVO() {
    }

    public SendUserInfoVO(String sendMailUserName, String sendMailPwd) {
        this.sendMailUserName = sendMailUserName;
        this.sendMailPwd = sendMailPwd;
    }
}
