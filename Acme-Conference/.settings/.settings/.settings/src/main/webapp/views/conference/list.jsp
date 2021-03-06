
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<!-- Listing grid -->
<security:authorize access="hasRole('ADMIN')">
	
		
		<button type="button"
			onclick="javascript: relativeRedir('conference/administrator/listsub.do')">
			<spring:message code="conference.submissionDeadlineList" />
		</button>
	
</security:authorize>
<security:authorize access="hasRole('ADMIN')">
	
		
		<button type="button"
			onclick="javascript: relativeRedir('conference/administrator/listnot.do')">
			<spring:message code="conference.notificationDeadlineList" />
		</button>
	
</security:authorize>
<security:authorize access="hasRole('ADMIN')">
		
		<button type="button"
			onclick="javascript: relativeRedir('conference/administrator/listcam.do')">
			<spring:message code="conference.cameraDeadlineList" />
		</button>
</security:authorize>
<security:authorize access="hasRole('ADMIN')">
		
		<button type="button"
			onclick="javascript: relativeRedir('conference/administrator/listst.do')">
			<spring:message code="conference.startList" />
		</button>
</security:authorize>




<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="conferences" requestURI="${requestURI}" id="row">



	<!-- Action links -->
	<security:authorize access="hasRole('ADMIN')">
		<display:column title="Show">
			<a href="conference/administrator/show.do?conferenceId=${row.id}">
				<spring:message code="conference.show" />
			</a>
		</display:column>
	</security:authorize>
	<security:authorize access="hasRole('ADMIN')">
		<display:column>
					<jstl:if test="${row.draftMode=='true'}">
		

			<a href="conference/administrator/edit.do?conferenceId=${row.id}">
				<spring:message code="conference.edit" />
			</a>
	</jstl:if>
		</display:column>
	</security:authorize>
	<!-- Attributes -->


	<display:column property="title" titleKey="conference.title"
		sortable="false" />

	<display:column property="acronym" titleKey="conference.acronym"
		sortable="false" />
	<display:column property="venue" titleKey="conference.venue"
		sortable="false" />
	<display:column property="submissionDeadline"
		titleKey="conference.submissionDeadline" sortable="false" format="{0,date,dd/MM/yyyy HH:mm}" />
	<display:column property="notificationDeadline"
		titleKey="conference.notificationDeadline" sortable="false" format="{0,date,dd/MM/yyyy HH:mm}" />
	<display:column property="cameraDeadline"
		titleKey="conference.cameraDeadline" sortable="false" format="{0,date,dd/MM/yyyy HH:mm}" />
	<display:column property="startDate" titleKey="conference.startDate"
		sortable="false" format="{0,date,dd/MM/yyyy HH:mm}"/>
	<display:column property="endDate" titleKey="conference.endDate"
		sortable="false" format="{0,date,dd/MM/yyyy HH:mm}"/>
	<display:column property="fee" titleKey="conference.fee"
		sortable="false" />
	<security:authorize access="hasRole('ADMIN')">

		<display:column property="draftMode" titleKey="conference.draftMode"
			sortable="false" />

	</security:authorize>









</display:table>
<security:authorize access="hasRole('ADMIN')">
	<div>
		
		<button type="button"
			onclick="javascript: relativeRedir('conference/administrator/create.do')">
			<spring:message code="conference.create" />
		</button>
	</div>
</security:authorize>