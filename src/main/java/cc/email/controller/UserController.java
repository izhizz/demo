package cc.email.controller;



import cc.email.utils.CalculatorUtil;
import cc.email.utils.SendmailUtil;
import cc.email.utils.VerifyCodeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;

@Controller
@RequestMapping(value = "/email")
public class UserController {
    /**
     * 发送自由编辑的邮件
     *
     * @param toEmailAddress 收件人邮箱
     * @param emailTitle     邮件主题
     * @param emailContent   邮件内容
     * @return
     */
    @RequestMapping(value = {"/sendEmailOwn/"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String sendEmailOwn(@RequestParam("toEmailAddress") String toEmailAddress,
                               @RequestParam("emailTitle") String emailTitle,
                               @RequestParam("emailContent") String emailContent) {
        try {
            //发送邮件
            SendmailUtil.sendEmail(toEmailAddress, emailTitle, emailContent);
            return CalculatorUtil.getJSONString(0);
        } catch (Exception e) {
            return CalculatorUtil.getJSONString(1, "邮件发送失败！");
        }
    }

    /**
     * 发送系统验证
     *
     * @param toEmailAddress 收件人邮箱
     * @return
     */
    @RequestMapping(value = {"/sendEmailSystem/"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String sendEmailSystem(@RequestParam("toEmailAddress") String toEmailAddress) {
        try {
            //生成验证码
            String verifyCode = VerifyCodeUtil.generateVerifyCode(6);

            //邮件主题
            String emailTitle = "【好学堂】邮箱验证";

            //邮件内容
            String emailContent = "您正在【好学堂】进行邮箱验证，您的验证码为：" + verifyCode + "，请于10分钟内完成验证！";

            //发送邮件
            SendmailUtil.sendEmail(toEmailAddress, emailTitle, emailContent);
            return CalculatorUtil.getJSONString(0, verifyCode);
        } catch (Exception e) {
            return CalculatorUtil.getJSONString(1, "邮件发送失败！");
        }
    }


    /**
     * 发送系统验证
     *
     * @param
     * @return
     */
    @RequestMapping(value = {"/test"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String sendEmailSystemHH() {
        try {
            //生成验证码
            String verifyCode = VerifyCodeUtil.generateVerifyCode(6);

            //邮件主题
            String emailTitle = "我是杨丁爸爸";

            //邮件内容
            String emailContent = "AAAAAAA";

            //发送邮件
            SendmailUtil.sendEmail("1040651714@qq.com", emailTitle, emailContent);
            return CalculatorUtil.getJSONString(0, verifyCode);
        } catch (Exception e) {
            return CalculatorUtil.getJSONString(1, "邮件发送失败！");
        }
    }
}
