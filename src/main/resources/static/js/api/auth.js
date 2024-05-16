const myHeaders = new Headers();

// 일반 사용자 로그인
export const postLogin = async (username, password) => {
    const formdata = new FormData();
    formdata.append("username", username);
    formdata.append("password", password);

    const requestOptions = {
        method: "POST",
        headers: myHeaders,
        body: formdata,
    };
    try {
        const response = await fetch(`/login`, requestOptions);
        if(response && response.status === 200) {
            const accessToken = await response.headers.get('Authorization').split(' ')[1];
            const data = await response.json()
            localStorage.setItem('access_token', accessToken)
            localStorage.setItem('memberName', data.memberName)
            localStorage.setItem('memberId', data.memberId)
            console.log("일반 로그인 성공")
            return true
        }
    } catch (error) {
        console.log("postLogin error : ",error)
        return null
    }
}

// 일반 사용자 회원가입
export const postSignIn = async (memberId, memberPassword, memberName, memberConsCategoryDtoList, targetExpenditure) => {
    const raw = JSON.stringify({
        "memberId": memberId,
        "memberPassword": memberPassword,
        "memberName": memberName,
        "memberConsCategoryDtoList": memberConsCategoryDtoList,
        "targetExpenditure": targetExpenditure
    });

    const requestOptions = {
        method: "POST",
        headers: myHeaders,
        body: raw,
    };
    try {
        console.log(raw)
        const response = await fetch(`/signUp`, requestOptions);
        if(response && response.status === 200) {
            const result = await response.ok
            console.log("일반 사용자 회원가입 완료 : ", result)
            return result
        }
    } catch (error) {
        console.log("postLogin error : ",error)
        return null
    }
}

// 이메일 인증번호 요청
export const postEmailRequest = async (email) => {
    myHeaders.append("Content-Type", "application/json");
    const raw = JSON.stringify({
        memberId: email
    });

    const requestOptions = {
        method: "POST",
        headers: myHeaders,
        body: raw,
    };
    try {
        const response = await fetch(`/email`, requestOptions);
        console.log("이메일 요청중")
        if(response && response.status === 200) {
            const result = await response.json()
            return result.result.existMember
        }
    } catch (error) {
        console.log("postLogin error : ",error)
        return null
    }
}

// 이메일 인증번호 확인
export const postEamilAuthentication = async (email, checkNumber) => {
    const raw = JSON.stringify({
        memberId: email,
        authenticationNumber : checkNumber
    });

    const requestOptions = {
        method: "POST",
        headers: myHeaders,
        body: raw,
    };
    try {
        const response = await fetch(`/email/authentication`, requestOptions);
        if(response && response.status === 200) {
            const result = await response.json()
            return result.result.sameNumber
        }
    } catch (error) {
        console.log("postLogin error : ",error)
        return null
    }
}

