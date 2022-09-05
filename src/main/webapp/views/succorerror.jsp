<%
	if (session.getAttribute("succMsg") != null) {
%>
<p class="alert alert-success text-center"><%=session.getAttribute("succMsg")%></p>
<%
	session.removeAttribute("succMsg");
	}
%>
<%
	if (session.getAttribute("failMsg") != null) {
%>
<p class="alert alert-danger text-center"><%=session.getAttribute("failMsg")%></p>
<%
	session.removeAttribute("failMsg");
	}
%>
<!-- Message of success or fail  End-->