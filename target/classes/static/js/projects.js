const createPost = async function() {
    let divFeed = document.getElementById('divFeed');

    try {
        const response = await fetch('/projects');
        const data = await response.json();

        if (data.length > 0) {
            divFeed.innerHTML = '';

            data.forEach(project => {
                let divPost = document.createElement('div');

                let OngNameBox = document.createElement('h2');
                let OngLocationBox = document.createElement('div');
                let OngLocationIcon = document.createElement('img');
                let OngLocationAddressBox = document.createElement('h3');
                let ProjectDescriptionBox = document.createElement('p');
                let VolunteerButton = document.createElement('button');

                let OngName = document.createTextNode(project.projectName);
                let OngLocationAddress = document.createTextNode(project.projectLocation);
                let ProjectDescription = document.createTextNode(project.projectDescription);
                let ButtonText = document.createTextNode('Voluntariar-se');

                divPost.setAttribute('class', 'ongPost');
                OngLocationBox.setAttribute('class', 'ongLocation');
                OngLocationIcon.setAttribute('src', '/icon/location.svg');
                OngLocationIcon.setAttribute('alt', 'ícone de localização');
                VolunteerButton.setAttribute('type', 'submit');

                divFeed.appendChild(divPost);
                divPost.appendChild(OngNameBox);
                divPost.appendChild(OngLocationBox);
                divPost.appendChild(ProjectDescriptionBox);
                divPost.appendChild(VolunteerButton);
                OngNameBox.appendChild(OngName);
                OngLocationBox.appendChild(OngLocationIcon);
                OngLocationBox.appendChild(OngLocationAddressBox);
                OngLocationAddressBox.appendChild(OngLocationAddress);
                ProjectDescriptionBox.appendChild(ProjectDescription);
                VolunteerButton.appendChild(ButtonText);

                VolunteerButton.addEventListener('click', function() {
                    voluntariar(project.projectName);
                });

            });
        } else {
            alert('Nenhum projeto encontrado.');
        }
    } catch (error) {
        console.error('Erro ao obter dados dos projetos:', error);
        alert('Ocorreu um erro ao buscar dados dos projetos. Por favor, tente novamente mais tarde.');
    }
};

let ongs = [];

async function voluntariar(projectName) {
    const token = localStorage.getItem('authToken');
    if (!token) {
        alert("No token found, please login first.");
        window.location.href = '/home';
        return;
    }

    try {
        const response = await fetch('/login/profile', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
            }
        });

        if (!response.ok) {
            const errorText = await response.text();
            throw new Error(errorText);
        }

        const data = await response.json();
        console.log('Success:', data);

        const userResponse = await fetch(`/users/${data.userId}`);
        const userData = await userResponse.json();

        if (!userData.ongs) {
            userData.ongs = [];
        }

        if (!userData.ongs.includes(projectName)) {
            userData.ongs.push(projectName);
        } else {
            alert('Projeto já cadastrado');
            return;
        }

        const responsePut = await fetch(`/users/${data.userId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
            },
            body: JSON.stringify({
                userName: data.userName,
                userMail: data.userMail,
                userPwd: data.userPwd,
                volunteer: data.volunteer,
                active: true,
                ongs: userData.ongs
            })
        });

        if (!responsePut.ok) {
            const errorText = await responsePut.text();
            throw new Error(errorText);
        }

        const dataPut = await responsePut.json();
        console.log('Success:', dataPut);

        await createPost();
        alert(`Usuário se voluntariou para o projeto: ${projectName}`);
    } catch (error) {
        console.error('Error:', error);
        alert(error);
    }
}

window.addEventListener('load', async function() {
    await createPost();
});
