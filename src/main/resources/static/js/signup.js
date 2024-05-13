import * as auth from "./api/auth.js"
import {postEamilAuthentication} from "./api/auth.js";

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
        selectedCategories = checkedCheckboxes.map(checkbox => checkbox.nextElementSibling.textContent);
        console.log("선택된 카테고리:", selectedCategories);
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
let emailauthentication = false;

const signUp = () => {
    if (!userID.value) {alert("이메일을 입력해주세요.") ; return false}
    if (!userName.value) {alert("이름을 입력해주세요.") ; return false}
    if (!password.value) {alert("비밀번호를 입력해주세요.") ; return false}
    if (!passwordCheck.value) {alert("확인비밀번호를 입력해주세요.") ; return false}
    if (!moneyGoal.value) {alert("목표 지출액을 입력해주세요.") ; return false}
    if (selectedCategories.length === 0 ) {alert("관심 카테고리를 1개이상 선택해주세요") ; return false}
    if (selectedCategories.length > 3 ) {alert("3개를 초과하실 수 없습니다.") ; return false}

    // location.href = "/login";
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
    }
    else {
        const validationMessage = formInputDatePickerDefault1.querySelector(".validation");
        if (validationMessage) {validationMessage.remove();}
        const nonValidationMessage = formInputDatePickerDefault1.querySelector(".non-validation");
        if (nonValidationMessage) {return}
        messageDiv.textContent = "비밀번호가 일치하지 않습니다.";
        messageDiv.classList.add("non-validation");
    }
    formInputDatePickerDefault1.appendChild(messageDiv);
});

document.addEventListener('keydown', (event) => {
    if (event.key === "Enter") {signUp()}
});

signUpButton.addEventListener('click',  signUp);

// email 인증 요청
const checknumButton = document.querySelector(".checknum")
const authenticationModal = document.querySelector(".authentication_modal")

checknumButton.addEventListener("click", async () => {
    if (!userID.value) {alert("이메일을 올바르게 입력해주세요.") ; return false}
    const display = document.querySelector('.authentication_modal_timer');
    startTimer(60 * 5, display);
    authenticationModal.showModal();
    const emailRequest = await auth.postEmailRequest(userID.value)
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

const closeButton = document.querySelector('.close_btn')
closeButton.addEventListener('click', () => {
    clearInterval(timerInterval);
    authenticationModal.close();
})

// email 인증 번호 확인
const authenticationModalButton = document.querySelector(".authentication_modal_button")
const authenticationModalInput = document.querySelector(".authentication_modal_input")

authenticationModalButton.addEventListener("click", async() => {
    emailauthentication = await auth.postEamilAuthentication(userID.value,authenticationModalInput.value)
    console.log(emailauthentication)
    if(emailauthentication === true) {
        clearInterval(timerInterval);
        authenticationModal.close();
    }
})

