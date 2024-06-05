window.addEventListener('load', function() {
    const workingOnProjectCheckbox = document.querySelector('input[name="volunteer"]');
    const selectOng = document.getElementById('registerOng');
    const inputBoxes = document.querySelectorAll('.inputBoxes');

    if (workingOnProjectCheckbox.checked) {
        selectOng.parentElement.style.display = 'flex';
        inputBoxes.forEach(box => {
            if (box !== selectOng.parentElement) {
                box.style.display = 'none';
            }
        });
    } else {
        selectOng.parentElement.style.display = 'none';
        inputBoxes.forEach(box => {
            box.style.display = 'flex';
        });
    }

    workingOnProjectCheckbox.addEventListener('change', function() {
        if (this.checked) {
            selectOng.parentElement.style.display = 'flex';
            inputBoxes.forEach(box => {
                if (box !== selectOng.parentElement) {
                    box.style.display = 'none';
                }
            });
        } else {
            selectOng.parentElement.style.display = 'none';
            inputBoxes.forEach(box => {
                box.style.display = 'flex';
            });
        }
    });
});

document.getElementById('registerForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const formData = new FormData(this);
    const data = {};

    formData.forEach((value, key) => {
        if (key === "term" || key === "volunteer") {
            data[key] = value === 'on';
        } else {
            data[key] = value;
        }
    });

    const requestBody = {
        registerName: data.registerName,
        registerMail: data.registerMail,
        registerMailConfirm: data.registerMailConfirm,
        registerPwd: data.registerPwd,
        registerPwdConfirm: data.registerPwdConfirm,
        volunteer: data.volunteer,
        registerOng: data.registerOng ? [data.registerOng] : [], 
        term: data.term
    };

    fetch('/registers', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(requestBody)
    })
    .then(response => {
        console.log('Response Status:', response.status);
        if (!response.ok) {
            return response.text().then(text => { throw new Error(text) });
        }
        return response.json();
    })
    .then(data => {
        console.log('Success:', data);
        alert('Cadastro Realizado com Sucesso');
        window.location.href = '/home';
    })
    .catch((error) => {
        alert(error);
    });
});

window.addEventListener('load', function() {
    const token = localStorage.getItem('authToken');

    fetch('/projects', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        }
    })
    .then(response => {
        if (!response.ok) {
            return response.text().then(text => { throw new Error(text) });
        }
        return response.json();
    })
    .then(data => {
        const selectOng = document.getElementById('registerOng');
        selectOng.innerHTML = '';

        const emptyOption = document.createElement('option');
        emptyOption.value = '';
        emptyOption.textContent = 'Selecione uma ONG';
        selectOng.appendChild(emptyOption);

        data.forEach(project => {
            const option = document.createElement('option');
            option.value = project.projectName;
            option.textContent = project.projectName;
            selectOng.appendChild(option);
        });
    })
    .catch(error => {
        console.error('Error fetching ONGs:', error);
        alert('Ocorreu um erro ao buscar as ONGs. Por favor, tente novamente mais tarde.');
    });
});
