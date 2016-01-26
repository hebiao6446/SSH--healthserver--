<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="org.slf4j.Logger,org.slf4j.LoggerFactory"%>

<%
	Throwable ex = null;
	if (exception != null)
		ex = exception;
	if (request.getAttribute("javax.servlet.error.exception") != null)
		ex = (Throwable) request.getAttribute("javax.servlet.error.exception");

	//记录日志
	Logger logger = LoggerFactory.getLogger("500.jsp");
	logger.error(ex.getMessage(), ex);

	StackTraceElement[] exStr = ex.getStackTrace();
	StringBuffer exStrs = new StringBuffer(ex.getMessage() + "<br/>");
	for (int i = 0; i < exStr.length; i++) {
		exStrs.append(exStr[i].toString()).append("<br/>");
	}
	response.setStatus(200);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>500 - 系统内部错误</title>
</head>

<body>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>

				<div id="rightbar24">
					<table width="100%" border="0" cellspacing="1" cellpadding="1"
						bgcolor="#FFFFFF" height="100%">
						<tr>
							<td>
								<TABLE cellSpacing=0 cellPadding=0 width=540 align=center
									border=0>
									<TBODY>
										<TR>
											<TD vAlign=top height=270>
												<DIV align="center">
													<br /> <IMG height=211 src="${ctx }/images/500.gif"
														width=329> <br /> <br />
													<TABLE cellSpacing=0 cellPadding=0 width="80%" border=0>
														<TBODY>
															<TR>
																<TD><font color="#ffffff"><%=exStrs.toString()%></font>
																</TD>
															</TR>
														</TBODY>
													</TABLE>
												</DIV>
											</TD>
										</TR>
									</TBODY>
								</TABLE>
								<P align=center></P>
								<P align=center></P>
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>

</body>
</html>
