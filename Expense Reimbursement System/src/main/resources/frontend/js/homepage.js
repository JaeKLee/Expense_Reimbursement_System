console.log("This is a test and beginning of an end");

let empRole = '';

window.onload = function() {
	mainDriver();
}

async function mainDriver() {
	const responsePayload = await fetch(`http://localhost:9009/api/user/login`);
	const payloadJSON = await responsePayload.json();


	document.getElementById("welcomeId").innerText =
		"Welcome, " + payloadJSON.firstName + " " + payloadJSON.lastName;

	empRole = payloadJSON.userRoleName;

	domManiputlation(payloadJSON.assocReimbList);
	displayModalData(payloadJSON);

	if (payloadJSON.userRoleName == "Employee") {
		document.getElementById("createModalBtn").style.visibility = "visible";
	};

	//filtering
	document
		.getElementById("filterDropdown")
		.addEventListener('change', function() {
			let filterMenu = document.getElementById('filterDropdown');
			let option = filterMenu.options[filterMenu.selectedIndex];
			filterBy(payloadJSON.assocReimbList, option.value)
		});

	document
		.getElementById("createSubmit")
		.addEventListener('click', createReimbursement);

	document.getElementById("logout").addEventListener('click', logout);
	
}

function displayModalData(payloadJSON, clickedVal) {
	document.getElementById("fname").setAttribute("placeholder", payloadJSON.firstName);
	document.getElementById("lname").setAttribute("placeholder", payloadJSON.lastName);
}

function displayEditModal(id) {
	document.getElementById("editModalLabel").innerHTML = "Reimbursement " + document.getElementById("editReimbId"+id).innerHTML;
	document.getElementById("editReimbId").setAttribute("placeholder", document.getElementById("editReimbId"+id).innerHTML);
	document.getElementById("editAuthName").setAttribute("placeholder", document.getElementById("editReimbAuthName"+id).innerHTML);
	document.getElementById("editAmount").setAttribute("placeholder", document.getElementById("editReimbAmount"+id).innerHTML);
	document.getElementById("editType").setAttribute("placeholder", document.getElementById("editReimbType"+id).innerHTML);
	document.getElementById("editdesc").setAttribute("placeholder", document.getElementById("editReimbDesc"+id).innerHTML);
	document.getElementById("updateStatus").addEventListener('click', updateStatus);
	
}


function filterBy(jsonObject, filterStatus) {
	console.log(jsonObject);
	let tempArr = [];
	for (let reimbElem of jsonObject) {
		if (reimbElem.reimbStatus.indexOf(filterStatus) !== -1) {
			tempArr.push(reimbElem);
		} else if (filterStatus == "All") {
			tempArr.push(reimbElem);
		}
	}

	

	document.getElementById("reimbBody").remove();

	let newtableBody = document.createElement("tbody");
	newtableBody.setAttribute("id", "reimbBody");
	let newSelection = document.querySelector("#reimbTable");
	newSelection.appendChild(newtableBody);
	console.log(tempArr);

	if (tempArr.length==0) {
		
	}

	domManiputlation(tempArr);


}
function createReimbursement(eve) {
	if (document.getElementById("amount").value !=='' && document.getElementById("amount").value !== 0.0 && document.getElementById("createSelectType").options[document.getElementById("createSelectType").selectedIndex].value !== "") {
		jsonObject = {
			reimbAmount: document.getElementById("amount").value,
			reimbTypeId: document.getElementById("createSelectType").options[document.getElementById("createSelectType").selectedIndex].value,
			description: document.getElementById("description").value
		}

		let xhttp = new XMLHttpRequest();

		xhttp.open('POST', 'http://localhost:9009/api/createreimb', true);

		xhttp.setRequestHeader("Content-Type", "application/json");

		console.log(document.getElementById('createSelectType').options[document.getElementById('createSelectType').selectedIndex].value)
		console.log(document.getElementById("amount").value);
		console.log(document.getElementById("description").value);
		console.log(jsonObject)

		xhttp.send(JSON.stringify(jsonObject));
	}

}

function updateStatus(event) {
	console.log("placeholder: " + document.getElementById("editReimbId").placeholder);
	if (true) {
		jsonObject = {
			reimbId: document.getElementById("editReimbId").placeholder,
			reimbStatusId: document.getElementById("editStatus").options[document.getElementById("editStatus").selectedIndex].value,
		}

		let xhttp = new XMLHttpRequest();

		xhttp.open('POST', 'http://localhost:9009/api/updatereimb', true);

		xhttp.setRequestHeader("Content-Type", "application/json");

		console.log(document.getElementById('editStatus').options[document.getElementById('editStatus').selectedIndex].value)

		xhttp.send(JSON.stringify(jsonObject));
	}
}
function logout() {
	let xhttp = new XMLHttpRequest();
	xhttp.open('POST', 'http://localhost:9009/api/user/logout', true);

	xhttp.setRequestHeader("Content-Type", "application/json");

	xhttp.send();
}

function domManiputlation(objectFromJSON) {
	let divRefer = document.getElementById("hideDiv");
	divRefer.style.visibility = "visible";

	for (let reimbElem of objectFromJSON) {
		let nullVal = "Pending";

		let submitTime = new Date(reimbElem.submittedTime);
		let resolveTime = 0;
		if (reimbElem.resolvedTime !== null) {
			resolveTime = new Date(reimbElem.resolvedTime);
		}
		let tr = document.createElement("tr");
		let th = document.createElement("th"); //reimbId
		th.setAttribute("scope", "row");
		th.setAttribute("id", "editReimbId"+reimbElem.reimbId);

		let td1 = document.createElement("td"); //reimbAmount
		td1.setAttribute("id", "editReimbAmount"+reimbElem.reimbId)
		let td2 = document.createElement("td"); //description
		td2.setAttribute("id", "editReimbDesc"+reimbElem.reimbId)
		let td3 = document.createElement("td"); //reimbAuthorFirstName+reimbAuthorLastName
		td3.setAttribute("id", "editReimbAuthName"+reimbElem.reimbId)
		let td4 = document.createElement("td"); //submittedTime
		td4.setAttribute("id", "editRubmitTime"+reimbElem.reimbId)
		let td5 = document.createElement("td"); //reimbResolverFirstName + reimbResolverLastName
		td5.setAttribute("id", "editReimbResolve"+reimbElem.reimbId)
		let td6 = document.createElement("td"); //resolvedTime
		td6.setAttribute("id", "editReimbResolveTime"+reimbElem.reimbId)
		let td7 = document.createElement("td"); //reimbType
		td7.setAttribute("id", "editReimbType"+reimbElem.reimbId)
		let td8 = document.createElement("td"); //reimbStatus
		td8.setAttribute("id", "editReimbStatus"+reimbElem.reimbId)

		let hTxt = document.createTextNode(reimbElem.reimbId);
		let d1Txt = document.createTextNode(reimbElem.reimbAmount);
		let d2Txt = document.createTextNode(reimbElem.description);
		let d3Txt = document.createTextNode(reimbElem.reimbAuthorFirstName + ", " + reimbElem.reimbAuthorLastName);
		let d4Txt = document.createTextNode(submitTime.toLocaleString());

		let d5Txt = document.createTextNode(nullVal);
		if (reimbElem.reimbResolverFirstName !== null) {
			d5Txt = document.createTextNode(reimbElem.reimbResolverFirstName + ", " + reimbElem.reimbResolverLastName);
		}
		let d6Txt = document.createTextNode(nullVal);
		if (resolveTime !== 0) {
			d6Txt = document.createTextNode(resolveTime.toLocaleString());
		}

		let d7Txt = document.createTextNode(reimbElem.reimbType);
		
		let d8Txt = document.createElement("button");
		if (empRole == "Manager") {
			console.log("this guy's a manager ");
			d8Txt.setAttribute("id", reimbElem.reimbId);
			d8Txt.setAttribute("type", "button");
			d8Txt.setAttribute("data-bs-toggle", "modal");
			d8Txt.setAttribute("data-bs-target", "#editModal");
			d8Txt.setAttribute("value", reimbElem.reimbId);
			d8Txt.setAttribute("onclick", "displayEditModal(this.id)");
			d8Txt.innerHTML = reimbElem.reimbStatus;
			if (reimbElem.reimbStatus == "Approved") {
				d8Txt.setAttribute("class", "btn btn-success")
			} else if (reimbElem.reimbStatus == "Denied") {
				d8Txt.setAttribute("class", "btn btn-danger")
			} else if (reimbElem.reimbStatus == "Pending") {
				d8Txt.setAttribute("class", "btn btn-outline-dark");
			} 
		} else if (empRole=="Employee") {
			console.log("this guy's an employee ");
			d8Txt.setAttribute("type", "button");
			d8Txt.setAttribute("disabled", "");
			d8Txt.innerHTML = reimbElem.reimbStatus;
			if (reimbElem.reimbStatus == "Approved") {
				d8Txt.setAttribute("class", "btn btn-success")
			} else if (reimbElem.reimbStatus == "Denied") {
				d8Txt.setAttribute("class", "btn btn-danger")
			} else if (reimbElem.reimbStatus == "Pending") {
				d8Txt.setAttribute("class", "btn btn-outline-dark");
			} 
		}

		th.appendChild(hTxt);
		td1.appendChild(d1Txt);
		td2.appendChild(d2Txt);
		td3.appendChild(d3Txt);
		td4.appendChild(d4Txt);
		td5.appendChild(d5Txt);
		td6.appendChild(d6Txt); //resolvedTime
		td7.appendChild(d7Txt);
		td8.appendChild(d8Txt);



		tr.appendChild(th);
		tr.appendChild(td1);
		tr.appendChild(td2);
		tr.appendChild(td3);
		tr.appendChild(td4);
		tr.appendChild(td5);
		tr.appendChild(td6);
		tr.appendChild(td7);
		tr.appendChild(td8);

		let newSelection = document.querySelector("#reimbBody");
		newSelection.appendChild(tr);
	}

}

