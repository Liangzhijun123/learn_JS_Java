document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById('dynamicForm');
    const output = document.getElementById('output');
    const resetBtn = document.getElementById('resetBtn');

    resetBtn.addEventListener('click', function () {
        clearForm();
        createGenderDropdown(); 
    });

 
    createGenderDropdown();
    createUserInfoForm();

    function createGenderDropdown() {
        const genderSelect = createSelect(['Select Gender', 'Men', 'Women'], 'gender');
        form.appendChild(genderSelect);
        animateElement(genderSelect); 

        genderSelect.addEventListener('change', function () {
          
            clearNextOptions('gender');
            if (this.value === 'Men') {
                createMenOptions();
            } else if (this.value === 'Women') {
                createWomenOptions();
            }
        });
    }

    function createMenOptions() {
        const menSelect = createSelect(['Select Facial Hair', 'Hair', 'No Hair'], 'facialHair');
        form.appendChild(menSelect);
        animateElement(menSelect);  

        menSelect.addEventListener('change', function () {
            clearNextOptions('facialHair');  
            if (this.value === 'Hair') {
                createComplexionOptions();
            } else if (this.value === 'No Hair') {
                createBuildOptions();
            }
        });
    }

    function createComplexionOptions() {
        const complexionSelect = createSelect(['Select Complexion', 'Hard', 'Not Hard'], 'complexion');
        form.appendChild(complexionSelect);
        animateElement(complexionSelect);

        complexionSelect.addEventListener('change', displayFinalChoice);
    }

    function createBuildOptions() {
        const buildSelect = createSelect(['Select Build', 'Build', 'No Build'], 'build');
        form.appendChild(buildSelect);
        animateElement(buildSelect);

        buildSelect.addEventListener('change', displayFinalChoice);
    }

    function createWomenOptions() {
        const hairSelect = createSelect(['Select Hair Color', 'Blonde', 'Brunette'], 'hairColor');
        form.appendChild(hairSelect);
        animateElement(hairSelect);

        hairSelect.addEventListener('change', function () {
            clearNextOptions('hairColor');  
            if (this.value === 'Blonde') {
                createHeightOptions();
            } else if (this.value === 'Brunette') {
                createEyeColorOptions();
            }
        });
    }

    function createHeightOptions() {
        const heightSelect = createSelect(['Select Height', 'Tall', 'Short'], 'height');
        form.appendChild(heightSelect);
        animateElement(heightSelect);

        heightSelect.addEventListener('change', displayFinalChoice);
    }

    function createEyeColorOptions() {
        const eyeSelect = createSelect(['Select Eye Color', 'Brown', 'Blue'], 'eyeColor');
        form.appendChild(eyeSelect);
        animateElement(eyeSelect);

        eyeSelect.addEventListener('change', displayFinalChoice);
    }

    function createSelect(optionsArray, id) {
        const select = document.createElement('select');
        select.setAttribute('id', id);

        optionsArray.forEach(optionText => {
            const option = document.createElement('option');
            option.value = optionText;
            option.text = optionText;
            select.appendChild(option);
        });

        return select;
    }

    function animateElement(element) {
        let opacity = 0;
        element.style.opacity = opacity;
        function fadeIn() {
            opacity += 0.05;
            element.style.opacity = opacity;
            if (opacity < 1) {
                requestAnimationFrame(fadeIn);
            }
        }
        requestAnimationFrame(fadeIn);  
    }

    function displayFinalChoice() {
        const gender = document.getElementById('gender').value;
        let finalChoice = `You selected ${gender}`;

        if (gender === 'Men') {
            const facialHair = document.getElementById('facialHair').value;
            finalChoice += ` with ${facialHair}.`;
            if (facialHair === 'Hair') {
                const complexion = document.getElementById('complexion').value;
                finalChoice += ` Complexion: ${complexion}.`;
            } else {
                const build = document.getElementById('build').value;
                finalChoice += ` Build: ${build}.`;
            }
        } else if (gender === 'Women') {
            const hairColor = document.getElementById('hairColor').value;
            finalChoice += ` with ${hairColor} hair.`;
            if (hairColor === 'Blonde') {
                const height = document.getElementById('height').value;
                finalChoice += ` Height: ${height}.`;
            } else {
                const eyeColor = document.getElementById('eyeColor').value;
                finalChoice += ` Eye Color: ${eyeColor}.`;
            }
        }

        const finalTextNode = document.createTextNode(finalChoice);
        output.appendChild(finalTextNode);
    }


    function clearForm() {
        while (form.firstChild) {
            form.removeChild(form.firstChild);
        }

        while (output.firstChild) {
            output.removeChild(output.firstChild);
        }
    }

    
    function clearNextOptions(id) {
        const allSelects = Array.from(form.getElementsByTagName('select'));
        let removeFlag = false;

        allSelects.forEach(select => {
            if (removeFlag) {
                form.removeChild(select);
            }
            if (select.getAttribute('id') === id) {
                removeFlag = true;
            }
        });
    }


    function createUserInfoForm() {
        const userInfoForm = document.createElement('form');
        userInfoForm.setAttribute('id', 'userInfoForm');
        
        const nameField = document.createElement('input');
        nameField.setAttribute('type', 'text');
        nameField.setAttribute('placeholder', 'Enter your name');
        nameField.setAttribute('required', true);
        
        const emailField = document.createElement('input');
        emailField.setAttribute('type', 'email');
        emailField.setAttribute('placeholder', 'Enter your email');
        emailField.setAttribute('required', true);
        
        const submitButton = document.createElement('button');
        submitButton.textContent = 'Submit';
        
        submitButton.addEventListener('click', function (event) {
            event.preventDefault();
            if (nameField.value && emailField.value) {
                storeUserInfo(nameField.value, emailField.value);
                alert('Form submitted successfully!');
            } else {
                alert('Please fill out all fields!');
            }
        });

        userInfoForm.appendChild(nameField);
        userInfoForm.appendChild(emailField);
        userInfoForm.appendChild(submitButton);
        form.appendChild(userInfoForm);
    }

    
    function storeUserInfo(name, email) {
        if (navigator.cookieEnabled) {
            document.cookie = `name=${name}; email=${email}; path=/`;
        } else {
            localStorage.setItem('name', name);
            localStorage.setItem('email', email);
        }
    }

   
    if (!('localStorage' in window && 'querySelector' in document)) {
        window.location.href = 'index.html';  
    }
});
