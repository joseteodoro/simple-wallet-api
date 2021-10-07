package br.jteodoro.wallet.api;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.test.web.servlet.MvcResult;

public class ResponseHelper {
    
    private ResponseHelper() {}

    public static String getValueIdFromResponse(MvcResult response) throws UnsupportedEncodingException {
        String result = response.getResponse().getContentAsString();
        Matcher matcher = Pattern.compile("\"accountId\":([0-9]+)").matcher(result);
        matcher.find();
        return matcher.group().replace("\"accountId\":", "");
    }
}
