(function () {
	initializeDefaultTemplate();
	loadTemplates();
})();

function initializeDefaultTemplate() {
	setTodayDate();
	emptyCalendarFields();

	document.getElementById("delete-template").style.display = 'none';
}

function loadTemplates() {
	$api.get("events/templates")
		.done(res => {
			document.getElementById("custom-templates").innerHTML = '';
			res.forEach(v => drawCustomTemplate(v))
		})
		.fail(err => $alert.show("event-alert", "event-alert-msg", "Something went wrong while loading templates."));
}

function drawCustomTemplate(template) {
	let spanTemplate = document.getElementById("custom-templates");
	let buttontoCreate = document.createElement("button");
	buttontoCreate.innerHTML = template.eventName;
	buttontoCreate.classList.add("btn");
	buttontoCreate.classList.add("btn-light");
	buttontoCreate.classList.add("btn-template");
	buttontoCreate.setAttribute("template-id", template.id);

	buttontoCreate.addEventListener("click", () => {
		document.getElementById("delete-template").style.display = 'block';

		setEventName(template.eventName);
		setEventDescription(template.eventDescription);

		let dateFrom = moment(template.fromDate).toDate();
		setDate(dateFrom, 'eventDate');
		setEventHours(dateFrom.getHours());
		setEventMinutes(dateFrom.getMinutes());

		let dateTo = moment(template.toDate).toDate();
		setDate(dateTo, 'eventTODate');
		setEventHours(dateTo.getHours());
		setEventMinutes(dateTo.getMinutes());

		for (let elementsByClassNameElement of document.getElementsByClassName("template-id")) {
			elementsByClassNameElement.classList.remove("im-active");
		}

		buttontoCreate.classList.add("im-active");
	});

	spanTemplate.appendChild(buttontoCreate);
}

function saveAsTemplate() {
	const templateName = document.getElementById("eventName").value;

	if (!templateName) {
		$alert.show("event-alert", "event-alert-msg", "Please select a valid template name.");

		return;
	}

	const data = {
		eventName: templateName,
		eventDescription: document.getElementById("eventDescription").value,
		fromDate: getFromDate(),
		toDate: getToDate()
	}

	$api.post("events/templates", data)
		.done(() => loadTemplates())
		.fail(() => $alert.show("event-alert", "event-alert-msg", "Something went wrong while saving template."));
}

function deleteTemplate() {
	const activeElements = document.getElementsByClassName("im-active");
	const id = activeElements.item(0).getAttribute("template-id");

	$api.delete(`events/templates/${id}`)
		.done(() => {
			document.getElementById("default-template").click();

			loadTemplates();
		})
		.fail(() => $alert.show("event-alert", "event-alert-msg", "Something went wrong while removing template."));
}

function setEventName(name) {
	document.getElementById("eventName").value = name;
}

function setEventDescription(description) {
	document.getElementById("eventDescription").value = description;
}

function setEventHours(hours) {
	document.getElementById("eventHours").value = hours;
	document.getElementById("eventTOHours").value = hours;
}

function setEventMinutes(minutes) {
	document.getElementById("eventMinutes").value = minutes;
	document.getElementById("eventTOMinutes").value = minutes;
}

function setDate(date, fieldId) {
	document.getElementById(fieldId).value = date.toLocaleDateString('en-CA', {
		year: 'numeric',
		month: '2-digit',
		day: '2-digit'
	});
}

function setTodayDate() {
	setDate(new Date(), 'eventDate');
	setDate(new Date(), 'eventTODate');
}

function getFromDate() {
	return getDate("eventDate", "eventHours", "eventMinutes");
}

function getToDate() {
	return getDate("eventTODate", "eventTOHours", "eventTOMinutes");
}

function getDate(fieldDateId, fieldHoursId, fieldMinutesId) {
	const dateString = document.getElementById(fieldDateId).value;
	const date = new Date(dateString);
	const fromHours = document.getElementById(fieldHoursId).value;
	const fromMinutes = document.getElementById(fieldMinutesId).value;
	date.setHours(fromHours);
	date.setMinutes(fromMinutes);

	return date;
}

function emptyCalendarFields() {
	document.getElementById("eventName").value = '';
	document.getElementById("eventHours").value = '';
	document.getElementById("eventTOHours").value = '';
	document.getElementById("eventMinutes").value = '';
	document.getElementById("eventTOMinutes").value = '';
	document.getElementById("eventDescription").value = '';
}

function downloadICSCalendar() {
	const cal = ics();
	cal.addEvent(document.getElementById("eventName").value, document.getElementById("eventDescription").value, '', getFromDate(), getToDate());
	cal.download(document.getElementById("eventName").value);
}