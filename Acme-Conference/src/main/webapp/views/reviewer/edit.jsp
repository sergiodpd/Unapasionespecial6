<%--
 * action-1.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!-- Función comprobar phoneNumber -->

<script type="text/javascript">
	function checkPhoneNumber() {
		var pN = $("input#phoneNumber").val();
		//[+],[(],[)] coincide carï¿½cteres de dentro de los corchetes
		//\d cualquier nï¿½mero de 0-9
		//? puede ser 0 o 1,esdecir,puede aparecer el +CC o el +CC (AC)
		//{4,} mï¿½nimo 4 dï¿½gitos
		var regex = /^(([+]\d{1,3})\s(([(]\d{1,3}[)]\s)?))?\d{4,}$/;
		if (pN != '') {
			if (regex.test(pN)) {
				return true;
			} else {
				return confirm("<spring:message code="administrator.checkPhoneNumber"/>");
			}
		}
	}
</script>
<security:authorize access="hasRole('REVIEWER')">
	<jstl:set var="url" value="reviewer/reviewer/edit.do" />
</security:authorize>
<security:authorize access="isAnonymous()">
	<jstl:set var="url" value="reviewer/edit.do" />
</security:authorize>


<form:form action="${url}" modelAttribute="reviewer"
	onsubmit="return checkPhoneNumber()">


	<form:hidden path="id" />
	<form:hidden path="version" />

	<form:hidden path="userAccount.id" />
	<form:hidden path="userAccount.version" />
	<form:hidden path="userAccount.Authorities" />
	<form:hidden path="messages" />
	<form:hidden path="reports" />
	<form:hidden path="finder" />


	<jstl:if test="${reviewer.id != 0}">
		<form:hidden path="userAccount.username" />
		<form:hidden path="userAccount.password" />
	</jstl:if>


	<%-- UserAccount--%>

	<jstl:if test="${reviewer.id == 0}">
		<%-- username--%>
		<form:label path="userAccount.username">
			<spring:message code="reviewer.username" />
		</form:label>
		<form:input path="userAccount.username" />
		<form:errors class="error" path="userAccount.username" />
		<br>
		<br>

		<%-- password--%>
		<form:label path="userAccount.password">
			<spring:message code="reviewer.password" />
		</form:label>
		<form:password path="userAccount.password" />
		<form:errors class="error" path="userAccount.password" />
		<br>
		<br>
	</jstl:if>


	<%-- Name --%>
	<form:label path="name">
		<spring:message code="reviewer.name" />
	</form:label>
	<form:input path="name" />
	<form:errors class="error" path="name" />
	<br>
	<br>

	<%-- Middlename --%>
	<form:label path="middleName">
		<spring:message code="reviewer.middleName" />
	</form:label>
	<form:input path="middleName" />
	<form:errors class="error" path="middleName" />
	<br>
	<br>

	<%-- Surname --%>
	<form:label path="surname">
		<spring:message code="reviewer.surname" />
	</form:label>
	<form:input path="surname" />
	<form:errors class="error" path="surname" />
	<br>
	<br>

	<%-- optionalPhoto --%>
	<form:label path="optionalPhoto">
		<spring:message code="reviewer.optionalPhoto" />
	</form:label>
	<form:input path="optionalPhoto" />
	<form:errors class="error" path="optionalPhoto" />
	<br>
	<br>

	<%-- Phone --%>
	<form:label path="phoneNumber">
		<spring:message code="reviewer.phoneNumber" />
	</form:label>
	<form:input path="phoneNumber" pattern="[0-9+()\s]+" />
	<form:errors class="error" path="phoneNumber" />
	<br>
	<br>

	<%-- Email --%>
	<form:label path="email">
		<spring:message code="reviewer.email" />
	</form:label>
	<form:input path="email" />
	<form:errors class="error" path="email" />
	<br>
	<br>

	<%-- Address --%>
	<form:label path="address">
		<spring:message code="reviewer.address" />
	</form:label>
	<form:input path="address" />
	<form:errors class="error" path="address" />
	<br>
	<br>

	<%-- Keywords --%>
	<form:label path="keywords">
		<spring:message code="reviewer.keywords" />
	</form:label>
	<form:input path="keywords" />
	<form:errors class="error" path="keywords" />
	<br>
	<br>


	<%-- Buttons --%>
	<input type="submit" name="save"
		value="<spring:message code="reviewer.save"/>" />

	<input type="button" name="cancel"
		value="<spring:message code="reviewer.cancel" />"
		onClick="javascript: window.location.replace('welcome/index.do');" />
</form:form>
