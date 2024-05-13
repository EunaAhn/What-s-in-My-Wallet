import * as auth from "./api/auth.js"

const username = document.querySelector("#signin-id")
const password = document.querySelector("#signin-pw")
const signinButton = document.querySelector("#signin")

// 아이디, 비밀번호 길이 제한
const MAX_LENGTH = 20;
username.addEventListener('input', ()=> {
    if (username.value.length > MAX_LENGTH) {
        username.value = username.value.slice(0, MAX_LENGTH);
    }
});

password.addEventListener('input', ()=> {
    if (password.value.length > MAX_LENGTH) {
        password.value = password.value.slice(0, MAX_LENGTH);
    }
});

// 사용자 login
const login = async () => {
    if (!username.value) {alert("아이디를 입력해주세요.") ; return false}
    if (!password.value) {alert("비밃번호를 입력해주세요.") ; return false}
    if (await auth.postLogin(username.value,password.value)) {location.href = "cardlist";}
    else {alert("정보를 잘못 입력하셨습니다.")}
}

document.addEventListener('keydown', (event) =>  {
    if (event.key === "Enter") {login();}
});

signinButton.addEventListener('click', login);