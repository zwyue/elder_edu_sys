/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.34
 * Generated at: 2019-02-27 08:06:16 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("jar:file:/C:/zwy/environment/Tomcat/apache-tomcat-8.5.34/webapps/zrtjoa/WEB-INF/lib/jstl-1.2.jar!/META-INF/c.tld", Long.valueOf(1153356282000L));
    _jspx_dependants.put("/WEB-INF/lib/jstl-1.2.jar", Long.valueOf(1551058674000L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");

    pageContext.setAttribute("path",request.getContextPath());

      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("    <meta charset=\"UTF-8\">\r\n");
      out.write("    <title>老年大学系统——登录页</title>\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"../../static/plugins/layui-v2.4.3/layui/css/layui.css\" />\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"../../static/css/common.css\" />\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"../../static/css/login.css\" />\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<div class=\"all\">\r\n");
      out.write("    <div class=\"login\">\r\n");
      out.write("        <div class=\"login_cont\">\r\n");
      out.write("            <h2>天津老年大学OA管理系统</h2>\r\n");
      out.write("            <!--登录框-->\r\n");
      out.write("            <div class=\"loginContent\">\r\n");
      out.write("                <section class=\"fl loginContentLeft\"> </section>\r\n");
      out.write("                <!--表单-->\r\n");
      out.write("                <section class=\"fr loginContentRight\">\r\n");
      out.write("                    <form id=\"login_form\" method=\"post\" action=\"/index/login\">\r\n");
      out.write("                        <section>\r\n");
      out.write("                            <label class=\"fl\">\r\n");
      out.write("                                <i class=\"layui-icon layui-icon-username\"></i>\r\n");
      out.write("                            </label>\r\n");
      out.write("                            <input type=\"text\" class=\"input username\" name=\"tnumber\" id=\" login_user_name\" aria-label=\"用户名\" placeholder=\"用户名\">\r\n");
      out.write("                        </section>\r\n");
      out.write("                        <div class=\"hint\">请输入用户名</div>\r\n");
      out.write("\r\n");
      out.write("                        <section>\r\n");
      out.write("                            <label class=\"fl\">\r\n");
      out.write("                                <i class=\"layui-icon layui-icon-password\"></i>\r\n");
      out.write("                            </label>\r\n");
      out.write("                            <input type=\"password\" class=\"input passward\" name=\"password\" id=\"login_password\" aria-label=\"密码\" placeholder=\"密码\">\r\n");
      out.write("                        </section>\r\n");
      out.write("                        <div class=\"hint\">请输入密码</div>\r\n");
      out.write("\r\n");
      out.write("                        <div class=\"radio\">\r\n");
      out.write("                            <div><input type=\"radio\" name=\"juese\" value=\"老师\" /><label>老师</label></div>\r\n");
      out.write("                            <div><input type=\"radio\" name=\"juese\" value=\"管理员\"  /><label>管理员</label></div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <input type=\"submit\" id=\"dlButton\" class=\"btn_login\" name=\"button\" value=\"登录\">\r\n");
      out.write("                    </form>\r\n");
      out.write("                    <p class=\"warn\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${msg}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("</p>\r\n");
      out.write("                    <div class=\"login_bottom\">\r\n");
      out.write("                    </div>\r\n");
      out.write("\r\n");
      out.write("                </section>\r\n");
      out.write("                <!--表单 end-->\r\n");
      out.write("            </div>\r\n");
      out.write("            <!--登录框 end-->\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\" src=\"/static/plugins/jQuery/jquery-3.3.1.min.js\"></script>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<script>\r\n");
      out.write("    //aiax请求数据\r\n");
      out.write("    function ajax_get(){\r\n");
      out.write("        $(\"#dlButton\").click(function (){\r\n");
      out.write("\r\n");
      out.write("            var form = $(\"#login_form\") ;\r\n");
      out.write("            form.submit();\r\n");
      out.write("\r\n");
      out.write("        })\r\n");
      out.write("    };\r\n");
      out.write("    ajax_get();\r\n");
      out.write("</script>\r\n");
      out.write("<script>\r\n");
      out.write("       $(\".username\").focus();\r\n");
      out.write("       $(\".username\").keydown(function (event) {\r\n");
      out.write("           if (event.keyCode==\"13\") {//回车键,移动光标到密码框\r\n");
      out.write("               $(\".passward\").focus();\r\n");
      out.write("           }\r\n");
      out.write("       });\r\n");
      out.write("       $(\".passward\").keydown(function (event) {\r\n");
      out.write("           if (event.keyCode ==\"13\") {//回车键，用.ajax提交表单\r\n");
      out.write("               $(\".btn_login\").focus();\r\n");
      out.write("           }\r\n");
      out.write("       });\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
