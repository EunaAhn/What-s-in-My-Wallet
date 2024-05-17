import * as auth from "./api/auth.js"

const checkboxes = document.querySelectorAll('input[type="checkbox"]');
const categoryList = document.querySelectorAll('.form-input-date-picker-default3');
let selectedCategories = []

// 체크박스를 체크할 때마다 호출될 함수를 정의합니다.
const handleCheckboxChange = () => {
    let checkedCheckboxes = [];

    categoryList.forEach(list => {
        const checked = list.querySelectorAll('input:checked');
        checkedCheckboxes = checkedCheckboxes.concat(Array.from(checked));
    });

    // 최대 3개까지만 선택 가능하도록 합니다.
    if (checkedCheckboxes.length > 3) {
        event.target.checked = false;
        console.log("최대 3개까지만 선택할 수 있습니다.");

    } else {
        const categoryMap = {
            '대형마트': 'MT1',
            '편의점': 'CS2',
            '학원': 'AC5',
            '주유': 'OL7',
            '문화': 'CT1',
            '외식': 'FD6',
            '카페/베이커리': 'CE7',
            '의료': 'HP8'
        };
        selectedCategories = checkedCheckboxes.map(checkbox =>
            categoryMap[checkbox.nextElementSibling.textContent.trim()])
    }
}

checkboxes.forEach(checkbox => {
    checkbox.addEventListener('change', handleCheckboxChange);
});

//회원 가입
const signUpButton = document.querySelector("#signup")
const userID = document.querySelector("#userid")
const userName = document.querySelector("#username")
const password = document.querySelector("#signin-pw")
const passwordCheck = document.querySelector("#signin-pw-again")
const moneyGoal = document.querySelector("#signin-moneygoal")
let emailAuthentication = false;
let passwordAuthentication = false;

moneyGoal.addEventListener("input", function(event) {
    // 입력된 값을 쉼표로 천 단위 구분하여 표시
    let input = event.target;
    let value = input.value.replace(/\D/g, "");
    input.value = Number(value).toLocaleString();
    console.log(input.value)
});
const signUp = async () => {
    if (!userID.value) {alert("이메일을 입력해주세요.") ; return false}
    if (!userName.value) {alert("이름을 입력해주세요.") ; return false}
    if (!password.value) {alert("비밀번호를 입력해주세요.") ; return false}
    if (!passwordCheck.value) {alert("확인비밀번호를 입력해주세요.") ; return false}
    if (selectedCategories.length === 0 ) {alert("관심 카테고리를 1개이상 선택해주세요") ; return false}
    if (!moneyGoal.value) {alert("목표 지출액을 입력해주세요.") ; return false}
    if(!emailAuthentication) {alert("이메일 인증을 진행해주세요.") ; return false}
    if(!passwordAuthentication) {alert("비밀번호를 확인해주세요.") ; return false}

    const memberConsCategoryDtoList= selectedCategories.map((category) => ({
        "memberId":userID.value,
        "expenditureCategoryId": category
    }))
    const moneyGoalValue = moneyGoal.value.replace(/,/g, '')
    const tmp = await auth.postSignIn(userID.value, password.value, userName.value,memberConsCategoryDtoList ,moneyGoalValue)
    if(tmp === true) {
        console.log("회원가입 성공")
        location.href = "login";
    }
}

passwordCheck.addEventListener("input", function() {
    const formInputDatePickerDefault1 = document.querySelectorAll(".form-input-date-picker-default1")[2]
    const passwordValue = password.value;
    const confirmPasswordValue = passwordCheck.value;
    const messageDiv = document.createElement("div");

    if (passwordValue === confirmPasswordValue) {
        const nonValidationMessage = formInputDatePickerDefault1.querySelector(".non-validation");
        if (nonValidationMessage) {nonValidationMessage.remove();}
        messageDiv.textContent = "비밀번호가 일치합니다.";
        messageDiv.classList.add("validation");
        passwordAuthentication = true
    }
    else {
        const validationMessage = formInputDatePickerDefault1.querySelector(".validation");
        if (validationMessage) {validationMessage.remove();}
        const nonValidationMessage = formInputDatePickerDefault1.querySelector(".non-validation");
        if (nonValidationMessage) {return}
        messageDiv.textContent = "비밀번호가 일치하지 않습니다.";
        messageDiv.classList.add("non-validation");
        passwordAuthentication = false
    }
    formInputDatePickerDefault1.appendChild(messageDiv);
});

signUpButton.addEventListener('click',  signUp);

document.addEventListener('keydown', (event) => {
    if (event.key === "Enter") {signUp()}
});

// email 인증 요청
const checknumButton = document.querySelector(".checknum")
const authenticationModal = document.querySelector(".authentication_modal")

checknumButton.addEventListener("click", async () => {
    if (!userID.value) {alert("이메일을 올바르게 입력해주세요.") ; return false}
    const display = document.querySelector('.authentication_modal_timer');
    startTimer(60 * 5, display);
    authenticationModal.showModal();
    const emailRequest = await auth.postEmailRequest(userID.value)
    console.log("인증번호 요청 성공 : ",emailRequest)
})

let timer, timerInterval ;
const startTimer = (duration, display) => {
    timer = duration;
    let minutes;
    let seconds;
    timerInterval = setInterval(() => {
        minutes = parseInt(timer / 60, 10);
        seconds = parseInt(timer % 60, 10);
        minutes = minutes < 10 ? "0" + minutes : minutes;
        seconds = seconds < 10 ? "0" + seconds : seconds;
        display.textContent = minutes + " : " + seconds;
        if (--timer < 0) {
            clearInterval(timer);
            authenticationModal.close(); // 타이머가 종료되면 모달을 닫습니다.
        }
    }, 1000);
};

// email 인증 번호 확인
const authenticationModalButton = document.querySelector(".authentication_modal_button")
const authenticationModalInput = document.querySelector(".authentication_modal_input")

const closeButton = document.querySelector('.close_btn')
closeButton.addEventListener('click', () => {
    authenticationModalInput.value = ""
    clearInterval(timerInterval);
    authenticationModal.close();
})

authenticationModalButton.addEventListener("click", async() => {
    emailAuthentication = await auth.postEamilAuthentication(userID.value,authenticationModalInput.value)
    console.log("인증 번호 확인 : ",emailAuthentication)
    if(emailAuthentication === true) {
        authenticationModalInput.value = ""
        clearInterval(timerInterval);
        authenticationModal.close();
    }
})

document.querySelector(".close_btns").addEventListener('click', () => {
    window.location.href = "login"; // 로그인 페이지로 이동
});
