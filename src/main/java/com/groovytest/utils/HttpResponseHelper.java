package com.groovytest.utils;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * LIUY
 */
public class HttpResponseHelper {

    private static Log logger = LogFactory.getLog(HttpResponseHelper.class);

    /**
     * TOUTF8
     *
     * @param response
     * @param resStr
     */
    public static void outUTF8(HttpServletResponse response, String resStr) {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=utf-8");
            response.getWriter().write(resStr);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * TOGBK
     *
     * @param response
     * @param resStr
     */
    public static void outGBK(HttpServletResponse response, String resStr) {
        try {
            response.setCharacterEncoding("GBK");
            response.setContentType("text/html; charset=gbk");
            response.getWriter().write(resStr);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 重定向
     *
     * @param response
     * @param redirectUrl
     * @throws IOException
     */
    public static void sendRedirect(HttpServletResponse response,
                                    String redirectUrl) throws IOException {
        //TODO  test for ie9
        //response.setHeader("Cache-Control", "no-cache");
        //response.setHeader("Pragma", "no-cache");
        ServletOutputStream out = response.getOutputStream();

        String redirectHtml = "<html>\n" + "<head>\n"
                + "<title>Redirect</title>\n"
                + "<script type=\"text/javascript\">\n" + "window.location.href='"
                + redirectUrl + "';\n" + "</script>\n" + "</head>\n" + "<body>\n"
                + "<div></div>\n" + "</body>\n" + "</html>";

        out.write(redirectHtml.getBytes());
        out.flush();
        out.close();
    }
}
