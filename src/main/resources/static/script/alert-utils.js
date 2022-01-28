var $alert = new function () {

	return {
		"show": (alertId, spanMsgId, message) => {
			let alertElement = document.getElementById(alertId);
			let alertMsgElement = document.getElementById(spanMsgId);

			alertMsgElement.innerHTML = message;

			alertElement.style.display = 'block';
		}
	}
}