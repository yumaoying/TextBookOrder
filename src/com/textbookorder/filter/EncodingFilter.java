package com.textbookorder.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//解决乱码问题过滤器

public class EncodingFilter implements Filter {
	private String charset = "UTF-8";

	public void destroy() {

	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		if (this.charset == null || charset.isEmpty()) {
			this.charset = "UTF-8";
		}
		HttpServletResponse response = (HttpServletResponse) res;
		HttpServletRequest request = (HttpServletRequest) req;
		// EncodingRequest er = new EncodingRequest(request, charset);
		if (request.getMethod().equalsIgnoreCase("GET")) {
			if (!(request instanceof EncodingRequest)) {
				request = new EncodingRequest(request, charset);// 处理get请求编码
			} else {
				response.setCharacterEncoding(charset);
				response.setContentType("text/html;charset=" + charset);
			}
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		String charset = filterConfig.getInitParameter("charset");
		if (charset != null && !charset.isEmpty()) {
			this.charset = charset;
		}
	}

}
