var $api = new function () {

	return {
		"post": (url, data) => $.ajax({
			url: url,
			data: JSON.stringify(data),
			type: "POST",
			contentType: "application/json; charset=utf-8",
			async: true
		}),
		"get": (url, data) => $.ajax({
			url: url,
			data: JSON.stringify(data),
			type: "GET",
			contentType: "application/json; charset=utf-8",
			async: true
		}),
		"delete": (url, data) => $.ajax({
			url: url,
			data: JSON.stringify(data),
			type: "DELETE",
			contentType: "application/json; charset=utf-8",
			async: true
		})
	}
}