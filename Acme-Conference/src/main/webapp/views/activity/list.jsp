
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

<script type="text/javascript">
	function confirmNotify() {

		return confirm("<spring:message code="submission.confirm"/>");

	}
</script>


<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="panels" requestURI="${requestURI}" id="row">



	<!-- Action links -->
	
	
	<security:authorize access="hasRole('ADMIN')">
		<display:column titleKey="panel.show">
			<a href="activity/administrator/panel/edit.do?panelId=${row.id}"> <spring:message
					code="panel.show" />
			</a>
		</display:column>
	</security:authorize>

	<!-- Attributes -->


	<display:column property="title"
		titleKey="activity.title" sortable="false" />
	<display:column property="startMoment" titleKey="activity.startMoment"
		sortable="false" />
	<display:column property="endMoment" titleKey="activity.endMoment"
		sortable="false" />
	<display:column property="room" titleKey="activity.room"
		sortable="true" />
	<display:column property="summary" titleKey="activity.summary"
		sortable="false" />
	<display:column>
		<a href="activity/administrator/panel/delete.do?panelId=${row.id}"> <spring:message
				code="panel.delete" />
		</a>
	</display:column>


</display:table>

<!--CREAR PANEL  -->
<security:authorize access="hasRole('ADMIN')">
	<div>
		
		<button type="button"
			onclick="javascript: relativeRedir('activity/administrator/panel/create.do?conferenceId=${conferenceId}')">
			<spring:message code="panel.create" />
		</button>
	</div>
</security:authorize>

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="tutorials" requestURI="${requestURI}" id="row">



	<!-- Action links -->
	
	
	<security:authorize access="hasRole('ADMIN')">
		<display:column titleKey="tutorial.show">
			<a href="activity/administrator/tutorial/edit.do?tutorialId=${row.id}"> <spring:message
					code="tutorial.show" />
			</a>
		</display:column>
	</security:authorize>

	<!-- Attributes -->


	<display:column property="title"
		titleKey="activity.title" sortable="false" />
	<display:column property="startMoment" titleKey="activity.startMoment"
		sortable="false" />
	<display:column property="endMoment" titleKey="activity.endMoment"
		sortable="false" />
	<display:column property="room" titleKey="activity.room"
		sortable="true" />
	<display:column property="summary" titleKey="activity.summary"
		sortable="false" />
	<display:column titleKey="tutorial.sections">
		<jstl:if test="${tutorial.section != null }">
			<a href="section/administrator/list.do?tutorialId=${row.id}"> <spring:message
				code="tutorial.sections" />
			</a>
		</jstl:if>
		<jstl:if test="${tutorial.section == null }">
			<a href="section/administrator/create.do?tutorialId=${row.id}"> <spring:message
				code="tutorial.section" />
			</a>
		</jstl:if>
	
	</display:column>
		
	<display:column>
		<a href="section/administrator/list.do?tutorialId=${row.id}"> <spring:message
				code="tutorial.sections" />
		</a>
	</display:column>
	<display:column>
		<a href="activity/administrator/tutorial/delete.do?tutorialId=${row.id}"> <spring:message
				code="tutorial.delete" />
		</a>
	</display:column>

</display:table>

<!--CREAR TUTORIAL  -->
<security:authorize access="hasRole('ADMIN')">
	<div>
		
		<button type="button"
			onclick="javascript: relativeRedir('activity/administrator/tutorial/create.do?conferenceId=${conferenceId}')">
			<spring:message code="tutorial.create" />
		</button>
	</div>
</security:authorize>

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="presentations" requestURI="${requestURI}" id="row">



	<!-- Action links -->
	
	
	<security:authorize access="hasRole('ADMIN')">
		<display:column titleKey="presentation.show">
			<a href="activity/administrator/presentation/edit.do?presentationId=${row.id}"> <spring:message
					code="presentation.show" />
			</a>
		</display:column>
	</security:authorize>

	<!-- Attributes -->


	<display:column property="title"
		titleKey="activity.title" sortable="false" />
	<display:column property="startMoment" titleKey="activity.startMoment"
		sortable="false" />
	<display:column property="endMoment" titleKey="activity.endMoment"
		sortable="false" />
	<display:column property="room" titleKey="activity.room"
		sortable="true" />
	<display:column property="summary" titleKey="activity.summary"
		sortable="false" />
	
	<display:column titleKey="presentation.submission">
			<a href="submission/administrator/show.do?submissionId=${row.submission.id}"> <spring:message
					code="presentation.subm" />
			</a>
	</display:column>
	<display:column>
		<a href="activity/administrator/presentation/delete.do?presentationId=${row.id}"> <spring:message
				code="presentation.delete" />
		</a>
	</display:column>
</display:table>

<!--CREAR PRESENTATION  -->
<security:authorize access="hasRole('ADMIN')">
	<div>
		
		<button type="button"
			onclick="javascript: relativeRedir('activity/administrator/presentation/create.do?conferenceId=${conferenceId}')">
			<spring:message code="presentation.create" />
		</button>
	</div>
</security:authorize>