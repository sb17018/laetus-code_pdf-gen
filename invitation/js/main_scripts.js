const checkboxes = [...document.querySelectorAll(".check-frame")];

checkboxes.forEach((elem) =>
	elem.addEventListener("click", function () {
		changeCheckBox(this);
	})
);

function changeCheckBox(box) {
	const checkBox = box.firstElementChild;
	if (checkBox.style.backgroundColor == "white") {
		checkBox.style.backgroundColor = "rgb(103, 103, 155)";
	} else {
		checkBox.style.backgroundColor = "white";
	}
}
