window.addEventListener('load', async function(event) {
    event.preventDefault();

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

        let data = await response.json();
        console.log('Success:', data);

        const divUser = document.getElementById("divUser");
        divUser.innerHTML = '';

        const nameAndEmail = document.createElement('div');
        nameAndEmail.setAttribute('class', 'nameAndEmail');

        const userName = document.createElement('h3');
        userName.innerText = data.userName;

        const userMailBox = document.createElement('div');
        userMailBox.setAttribute('class', 'email');

        const userIcon = document.createElement('img');
        userIcon.setAttribute('src', '/icon/email white.svg');
        userIcon.setAttribute('alt', 'ícone de email');

        const userMail = document.createElement('h4');
        userMail.innerText = data.userMail;

        const leftAccount = document.createElement('button');
        leftAccount.setAttribute('type', 'button');
        leftAccount.innerText = 'Sair desta conta';
        leftAccount.addEventListener('click', function() {
            localStorage.removeItem('authToken');
            window.location.href = '/home';
        });

        const usersActionsBox = document.createElement('div');
        usersActionsBox.setAttribute('class', 'userActions');

        const ongs = document.createElement('h3');
        ongs.innerText = 'ONGs que participo';

        const ongsParticipating = document.createElement('div');
        ongsParticipating.setAttribute('class', 'ongsParticipating');

        if (data.ongs != null && data.ongs.length > 0) {
            for (let i = 0; i < data.ongs.length; i++) {
                const ong = data.ongs[i];

                const divOng = document.createElement('div');
                divOng.setAttribute('class', 'ong');

                const ongTitle = document.createElement('h4');
                ongTitle.innerText = ong;

                const buttonOng = document.createElement('button');
                buttonOng.setAttribute('type', 'button');
                buttonOng.innerText = 'Sair';
                buttonOng.addEventListener('click', function() {
                    sair(ong);
                });

                divOng.appendChild(ongTitle);
                divOng.appendChild(buttonOng);

                ongsParticipating.appendChild(divOng);
            }
        } else {
            const noOngText = document.createElement('p');
            noOngText.innerText = 'Nenhuma ong encontrada';
            ongsParticipating.appendChild(noOngText);
        }

        divUser.appendChild(nameAndEmail);
        nameAndEmail.appendChild(userName);
        userMailBox.appendChild(userIcon);
        userMailBox.appendChild(userMail);
        nameAndEmail.appendChild(userMailBox);
        nameAndEmail.appendChild(leftAccount);
        usersActionsBox.appendChild(ongs);
        usersActionsBox.appendChild(ongsParticipating);
        divUser.appendChild(usersActionsBox);

    } catch (error) {
        console.error('Error:', error);
        alert(error);
    }
});

async function sair(projectName) {
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

        let data = await response.json();
        console.log('Success:', data);

        data.ongs = data.ongs.filter(ong => ong !== projectName);

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
                ongs: data.ongs
            })
        });

        if (!responsePut.ok) {
            const errorText = await responsePut.text();
            throw new Error(errorText);
        }

        const dataPut = await responsePut.json();
        console.log('Success:', dataPut);

        window.location.reload();

    } catch (error) {
        console.error('Error:', error);
        alert(error);
    }
}
