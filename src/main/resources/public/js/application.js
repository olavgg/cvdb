const renderTable = data => {

	let tableBodyHtml = "";
	data.forEach( person => {
		tableBodyHtml += `
		<tr>
			<td>${person.id}</td>
			<td>${person.name}</td>
			<td>${person.title}</td>
			<td>${person.buzzWords.map(buzzWord => `
				${buzzWord}
			`).join(', ')}
			</td>
		</tr>
		`
	});
	document.querySelector('table tbody').innerHTML = tableBodyHtml;
};

const listOfPeopleUrl = "/list";
fetch(listOfPeopleUrl)
	.then( response => {
		return response.json();
	})
	.then( renderTable );