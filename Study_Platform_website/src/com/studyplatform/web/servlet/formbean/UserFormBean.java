package com.studyplatform.web.servlet.formbean;

import java.util.HashMap;
import java.util.Map;

/**
 * 页面数据提交封装Bean
 * Title: UserFormBean
 * @date 2018年2月26日
 * @author Freedom0013
 */
public class UserFormBean {
    /** 用户名 */
    private String regusername;
    /** 用户密码 */
    private String regpassword;
    /** 用户重复输入密码 */
    private String sec_regpassword;
    /** 用户注册时间 */
    private String token;
    /** 用户昵称 */
    private String nickname;
    /** 页面提交检查错误集合 */
    private Map<String,String> errors = new HashMap<String,String>();
    
    /**
     * 服务端验证用户提交表单
     */
    public boolean validate(){
        //验证用户名
        if(regusername == "" || regusername == null){
            errors.put("username", "用户名不能为空") ;
        }else{
            if(regusername.length() > 11 || regusername.length() < 3){
                errors.put("username", "用户名长度必须在3~11位之间") ;
            }
        }
        
        //验证昵称
        if(nickname == "" || nickname == null){
            errors.put("nickname", "昵称不能为空") ;
        }else{
            if(nickname.length() > 12 || nickname.length() < 3){
                errors.put("nickname", "昵称长度必须在3~12位之间") ;
            }
        }
        
        //验证密码
        if(regpassword == "" || regpassword == null){
            errors.put("password", "密码不能为空") ;
        }else{
            if(regpassword.length() > 8 || regpassword.length() < 3){
                errors.put("password", "密码长度必须在3~8位之间") ;
            }
        }
        
        if(!sec_regpassword.equals(regpassword)){
            errors.put("password", "两次密码输入不一致") ;
        }
        
        return errors.isEmpty() ;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

    public String getSec_regpassword() {
        return sec_regpassword;
    }

    public void setSec_regpassword(String sec_regpassword) {
        this.sec_regpassword = sec_regpassword;
    }

    public String getRegusername() {
        return regusername;
    }

    public void setRegusername(String regusername) {
        this.regusername = regusername;
    }

    public String getRegpassword() {
        return regpassword;
    }

    public void setRegpassword(String regpassword) {
        this.regpassword = regpassword;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "UserFormBean [regusername=" + regusername + ", regpassword=" + regpassword + ", sec_regpassword="
                + sec_regpassword + ", token=" + token + ", nickname=" + nickname + ", errors=" + errors + "]";
    }
}
