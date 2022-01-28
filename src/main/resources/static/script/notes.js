const editorSettings = {
	btns: [
		['formatting'],
		['strong', 'em', 'del'],
		['superscript', 'subscript'],
		['link'],
		['justifyLeft', 'justifyCenter', 'justifyRight', 'justifyFull'],
		['unorderedList', 'orderedList'],
		['removeformat']
	]
};


(function () {
	loadNotes();

	document.getElementById("delete-note").style.display = 'none';
})();

function loadNotes(clickThisId) {
	$api.get("notes/all")
		.done(res => {
			document.getElementById("custom-notes").innerHTML = '';

			res.forEach(v => drawCustomNote(v, clickThisId === v.id));
		})
		.fail(err => $alert.show("notes-alert", "notes-alert-msg", "Something went wrong while retrieving notes."))
}

function initializeNewNote() {
	document.getElementById("editor").style.display = 'block';
	document.getElementById("delete-note").style.display = 'none';

	$('#editor').trumbowyg(editorSettings);
	$('#editor').trumbowyg('empty');

	removeActiveClass();

	document.getElementById("new-note").classList.add("im-active");
}

function drawCustomNote(note, clickMe) {
	let spanTemplate = document.getElementById("custom-notes");
	let buttontoCreate = document.createElement("button");

	const noteText = jQuery.parseHTML(note.note)[0].textContent;

	buttontoCreate.innerHTML = noteText.substring(0, 5);
	buttontoCreate.classList.add("btn");
	buttontoCreate.classList.add("btn-primary");
	buttontoCreate.classList.add("btn-template-note");
	buttontoCreate.setAttribute("note-id", note.id);

	buttontoCreate.addEventListener("click", () => {
		document.getElementById("delete-note").style.display = 'block';

		removeActiveClass();

		buttontoCreate.classList.add("im-active");

		document.getElementById("editor").style.display = 'block';
		$('#editor').trumbowyg(editorSettings);
		$('#editor').trumbowyg('html', note.note);
	});

	spanTemplate.appendChild(buttontoCreate);

	buttontoCreate.click();
}

function removeActiveClass() {
	for (let elementsByClassNameElement of document.getElementsByClassName("btn-template-note")) {
		elementsByClassNameElement.classList.remove("im-active");
	}

	document.getElementById("new-note").classList.remove("im-active");
}

function saveNewNote() {
	const data = {
		note: $('#editor').trumbowyg('html')
	};

	$api.post("notes", data)
		.done(res => loadNotes(res))
		.fail(err => $alert.show("notes-alert", "notes-alert-msg", "Something went wrong while saving note."))
}

function deleteNote() {
	const activeElements = document.getElementsByClassName("btn-template-note im-active");
	const id = activeElements.item(0).getAttribute("note-id");

	$api.delete(`notes/${id}`)
		.done(() => {
			document.getElementById("delete-note").style.display = 'none';

			removeActiveClass();
			$('#editor').trumbowyg('empty');
			$('#editor').trumbowyg('destroy');

			loadNotes();
		})
		.fail(() => $alert.show("notes-alert", "notes-alert-msg", "Something went wrong while removing note."));

}